package com.gabeWebTest.webTest.services;

import com.gabeWebTest.webTest.data.users.CustomUserDetails;
import com.gabeWebTest.webTest.data.users.User;
import com.gabeWebTest.webTest.data.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user ==null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        //CustomUserDetails interprets our user object used in data jpa to the UserDetails needed for UserDetailsService.
        //UserDetailsService is needed for our spring security to autowire how to read from our repository

        return new CustomUserDetails(user);
    }
}
