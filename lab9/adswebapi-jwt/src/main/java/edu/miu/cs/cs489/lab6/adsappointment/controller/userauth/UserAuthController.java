package edu.miu.cs.cs489.lab6.adsappointment.controller.userauth;

import edu.miu.cs.cs489.lab6.adsappointment.dto.userauth.UserAuthRequest;
import edu.miu.cs.cs489.lab6.adsappointment.service.util.JWTManagementUtilityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/adsweb/api/v1/service"})
public class UserAuthController {

    private final JWTManagementUtilityService jwtManagementUtilityService;
    private final AuthenticationManager authenticationManager;

    public UserAuthController(JWTManagementUtilityService jwtManagementUtilityService,
                              AuthenticationManager authenticationManager) {
        this.jwtManagementUtilityService = jwtManagementUtilityService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = {"/public/authenticate"})
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody UserAuthRequest userAuthRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthRequest.username(),
                        userAuthRequest.password()
                )
        );
        return ResponseEntity.ok(jwtManagementUtilityService.generateToken(userAuthRequest.username()));
    }
}