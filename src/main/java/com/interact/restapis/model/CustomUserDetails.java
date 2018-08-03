package com.interact.restapis.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * This class is UserDetailsService of OAuth2 security it maps users with OAuth
 * userDetails object and enriches them with authentication and authorization
 * features
 *
 * @author Nalin Kamboj
 * @version 1.0
 * @since 02-08-2018
 */

public class CustomUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    String ROLE_PREFIX = "ROLE_";  //Temporary -> all users, in future -> multiple roles

    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails (final User user){
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword(){
        return super.getPassword();
    }

    @Override
    public String getEmail(){
        return super.getEmail();
    }

    @Override
    public String getUsername(){
        return super.getEmail();
    }

    @Override
    public String getFirstName(){
        return super.getFirstName();
    }

    @Override
    public String getLastName(){
        return super.getLastName();
    }

    @Override
    public Long getId(){
        return super.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
