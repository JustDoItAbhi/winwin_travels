package com.auth_api.auth_api.services.process;

import com.auth_api.auth_api.dtos.ProcessingLogResponseDto;
import com.auth_api.auth_api.dtos.request_dto.ProcessingLogRequestDto;

public interface ProcessingLogService {
    ProcessingLogResponseDto processText(ProcessingLogRequestDto request, String userEmail);
}
