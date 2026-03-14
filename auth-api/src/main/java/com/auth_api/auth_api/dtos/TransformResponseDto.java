package com.auth_api.auth_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransformResponseDto {
    @NotBlank(message = "text should not be blank")
    private String text;
}
