package com.auth_api.auth_api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserResponseDto {
    private LocalDateTime createdAt;
    private UUID userId;
    private String email;
}
