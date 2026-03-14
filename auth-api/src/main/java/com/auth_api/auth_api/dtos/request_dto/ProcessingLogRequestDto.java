package com.auth_api.auth_api.dtos.request_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessingLogRequestDto {
    @NotBlank(message = "Text is required")
    private String text;
}
