package com.interact.restapis;

import com.interact.restapis.model.CustomUserDetails;
import com.interact.restapis.model.Role;
import com.interact.restapis.model.User;
import com.interact.restapis.repository.RoleRepository;
import com.interact.restapis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class RestApisApplication {

	@Autowired
    private RoleRepository roleRepository;

	@Autowired
    private PasswordEncoder userPasswordEncoder;

	@Autowired
    private void authenticationManager(AuthenticationManagerBuilder builder, UserRepository userRepository) throws Exception{
	    if(roleRepository.count() == 0){
	        roleRepository.save((new Role("USER")));
	        roleRepository.save((new Role("MENTOR")));
        }

        if(userRepository.count() == 0){
            User user = new User();
            Set<Role> userRole = new HashSet<>();
            userRole.add(new Role("ADMIN"));
            user.setEmail("interactadmin");
            user.setPassword(userPasswordEncoder.encode("interact-admin-biduboi"));
            userRepository.save(user);
        }

        builder.userDetailsService(s ->{
            Optional<User> u = userRepository.findByEmail(s);
            User us = u.get();
            return new CustomUserDetails(us);
        });
    }

	public static void main(String[] args) {
		SpringApplication.run(RestApisApplication.class, args);
	}
}
