package com.interact.restapis.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    String ROLE = "ROLE_USER";  //Temporary -> all users, in future -> multiple roles

    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails (final User user){
        super(user);
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; //WORK FROM HERE!!!!!!
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
