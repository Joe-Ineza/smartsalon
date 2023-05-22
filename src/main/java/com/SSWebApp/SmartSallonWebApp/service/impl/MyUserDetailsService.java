package com.SSWebApp.SmartSallonWebApp.service.impl;

import com.SSWebApp.SmartSallonWebApp.Secured.UserPrincipal;
//import com.SSWebApp.SmartSallonWebApp.models.User;
import com.SSWebApp.SmartSallonWebApp.models.Users;
import com.SSWebApp.SmartSallonWebApp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserName(username);

        if(user == null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }
}
