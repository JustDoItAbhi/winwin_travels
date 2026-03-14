package com.auth_api.auth_api.services.user_service;

import com.auth_api.auth_api.dtos.UserResponseDto;
import com.auth_api.auth_api.dtos.request_dto.UserLoginRequestDto;
import com.auth_api.auth_api.dtos.request_dto.UserRegistrationRequestDto;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationRequestDto dto);
    String login(UserLoginRequestDto dto);
}
