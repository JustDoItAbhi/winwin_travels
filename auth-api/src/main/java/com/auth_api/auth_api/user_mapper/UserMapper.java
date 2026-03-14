package com.auth_api.auth_api.user_mapper;


import com.auth_api.auth_api.dtos.UserResponseDto;
import com.auth_api.auth_api.entity.User;

public class UserMapper {
    public static UserResponseDto fromEntity(User user){
        UserResponseDto dto=new UserResponseDto();
        dto.setCreatedAt(user.getCreated_at());
        dto.setUserId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreated_at());
        return dto;
    }
}
