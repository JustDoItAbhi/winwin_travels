package com.data_api.data_api.service;

import com.data_api.data_api.dto.TransformRequestDto;
import com.data_api.data_api.dto.TransformResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class TransformServiceImpl implements TransformService{
    @Value("${app.internal-token}")
    private String validInternalToken;
    @Override
    public TransformResponseDto transforms(TransformRequestDto dto, String internalToken) {

        if(!validInternalToken.equals(internalToken)){
            throw new SecurityException("INTERNAL TOKEN IS NOT VALID");
        }
        String text = dto.getText();
        String transformed = new StringBuilder(text)
                .reverse()
                .toString()
                .toUpperCase();

        return new TransformResponseDto(transformed);
    }


}
