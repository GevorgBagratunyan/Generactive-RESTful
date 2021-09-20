package com.generactive.security;

import com.generactive.model.User;
import com.generactive.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GeneractiveUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public GeneractiveUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        user.setActive(true);
        userRepository.save(user);
        GeneractiveUserDetails userDetails = new GeneractiveUserDetails(user);

        return userDetails;
    }
}
