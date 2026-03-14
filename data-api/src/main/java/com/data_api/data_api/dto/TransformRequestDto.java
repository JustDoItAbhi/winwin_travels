package com.data_api.data_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransformRequestDto {
    @NotBlank(message = "input should not be blank")
    private String text;
}
