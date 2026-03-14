package com.auth_api.auth_api.user_customisations;

import com.auth_api.auth_api.entity.User;
import com.auth_api.auth_api.exceptions.WinUserNotFountException;
import com.auth_api.auth_api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User>user=userRepository.findByEmail(username);
        if(user.isEmpty()){
            throw new WinUserNotFountException("PLEASE ADD TOKEN OR SIGNUP "+username);
        }

        return new CustomUserDetail(user.get());
    }
}
