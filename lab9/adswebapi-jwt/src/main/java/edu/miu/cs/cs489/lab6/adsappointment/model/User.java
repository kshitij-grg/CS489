package edu.miu.cs.cs489.lab6.adsappointment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "roleId")}
    )
    private List<Role> roles;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public User(String username, String password, String firstName, String lastName, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles == null ? new ArrayList<>() : new ArrayList<>(roles);
    }

    public User(String username, String password, String firstName, String lastName, Role role) {
        this(username, password, firstName, lastName, role == null ? new ArrayList<>() : new ArrayList<>(List.of(role)));
    }

    public User(String username, String password) {
        this(username, password, null, null, new ArrayList<>());
    }

    @Override
    public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
        String[] userRoles = roles == null ? new String[0] : roles.stream()
                .map(role -> {
                    String roleName = role.getRoleName().toUpperCase().replace(" ", "_");
                    return roleName.startsWith("ROLE_") ? roleName : "ROLE_" + roleName;
                })
                .toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(userRoles);
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
