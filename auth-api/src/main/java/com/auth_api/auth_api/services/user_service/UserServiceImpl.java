package com.auth_api.auth_api.services.user_service;

import com.auth_api.auth_api.dtos.UserResponseDto;
import com.auth_api.auth_api.dtos.request_dto.UserLoginRequestDto;
import com.auth_api.auth_api.dtos.request_dto.UserRegistrationRequestDto;
import com.auth_api.auth_api.entity.User;
import com.auth_api.auth_api.exceptions.WinUserNotFountException;
import com.auth_api.auth_api.jwt_utils.JwtService;
import com.auth_api.auth_api.repositories.ProcessingLogRepository;
import com.auth_api.auth_api.repositories.UserRepository;
import com.auth_api.auth_api.user_customisations.CustomUserDetail;
import com.auth_api.auth_api.user_mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ProcessingLogRepository processingLogRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository,
                           ProcessingLogRepository processingLogRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.processingLogRepository = processingLogRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponseDto registerUser(UserRegistrationRequestDto dto) {
        Optional<User> exsistingUser = userRepository.findByEmail(dto.getEmail());
        if (exsistingUser.isPresent()) {
            throw new WinUserNotFountException("USER ALREADY EXISTS PLEASE LOGIN " + dto.getEmail());
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword_hash (passwordEncoder.encode(dto.getPassword()));
        User savedUser = userRepository.saveAndFlush(user);
        log.info("USER REGISTER SUCESSFULLY:{} ", dto.getEmail());
        return UserMapper.fromEntity(savedUser);
    }

    @Override
    public String login(UserLoginRequestDto dto) {
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getEmail(),
                dto.getPassword()
        ));
        CustomUserDetail userDetail=(CustomUserDetail) authentication.getPrincipal();
        String jwt=jwtService.generateToken(userDetail);

        return jwt;
    }
}
