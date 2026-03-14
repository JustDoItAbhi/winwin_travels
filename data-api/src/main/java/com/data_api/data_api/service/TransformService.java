package com.data_api.data_api.service;

import com.data_api.data_api.dto.TransformRequestDto;
import com.data_api.data_api.dto.TransformResponseDto;

public interface TransformService {
    TransformResponseDto transforms(TransformRequestDto dto, String internalToken);
}
