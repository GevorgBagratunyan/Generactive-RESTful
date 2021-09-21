package com.generactive;

import com.generactive.model.User;
import com.generactive.model.enums.Authority;
import com.generactive.model.enums.Role;
import com.generactive.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Use This class to pre-initialize the Database.
 * Since Jwt Filters are implemented and passwords are encrypted
 */
@Service
public class DbInit implements CommandLineRunner {

    private UserRepository userRepository;
    public PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        User user = new User();
        user.setUsername("Gevorg");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setActive(true);
        user.setRole(Role.ROLE_ADMIN);
        user.addAuthority(Authority.ACCESS_PENTAGON);
        userRepository.save(user);

    }
}
