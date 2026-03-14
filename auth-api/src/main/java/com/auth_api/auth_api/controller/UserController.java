package com.auth_api.auth_api.controller;

import com.auth_api.auth_api.dtos.UserResponseDto;
import com.auth_api.auth_api.dtos.request_dto.UserLoginRequestDto;
import com.auth_api.auth_api.dtos.request_dto.UserRegistrationRequestDto;
import com.auth_api.auth_api.services.user_service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRegistrationRequestDto dto){
        return new ResponseEntity<>(userService.registerUser(dto), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginRequestDto dto){
        return ResponseEntity.ok(userService.login(dto));
    }

}