package com.santander.banco811.security;

import com.santander.banco811.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var byLogin = userRepository.findByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username " + username + " not found")
        );
        return new UserDetailsData(byLogin);
    }
}
