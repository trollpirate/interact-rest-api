package com.interact.restapis.service;

import com.interact.restapis.model.CustomUserDetails;
import com.interact.restapis.model.User;
import com.interact.restapis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        userOptional.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return userOptional.map(user -> new CustomUserDetails(user)).get();
    }

}
