package edu.miu.cs.cs489.lab6.adsappointment.filter;

import edu.miu.cs.cs489.lab6.adsappointment.service.impl.AppUserDetailsService;
import edu.miu.cs.cs489.lab6.adsappointment.service.util.JWTManagementUtilityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private final AppUserDetailsService appUserDetailsService;
    private final JWTManagementUtilityService jwtManagementUtilityService;

    public JWTAuthFilter(AppUserDetailsService appUserDetailsService,
                         JWTManagementUtilityService jwtManagementUtilityService) {
        this.appUserDetailsService = appUserDetailsService;
        this.jwtManagementUtilityService = jwtManagementUtilityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            try {
                if (StringUtils.hasText(jwtToken)) {
                    String username = jwtManagementUtilityService.extractUsername(jwtToken);
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = appUserDetailsService.loadUserByUsername(username);
                        if (jwtManagementUtilityService.validateToken(jwtToken, userDetails)) {
                            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }
                    }
                }
            } catch (Exception ex) {
                // Ignore malformed/invalid JWT and continue unauthenticated.
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/adsweb/api/v1/service/public/");
    }
}