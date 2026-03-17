package com.auth_api.auth_api.controller;

import com.auth_api.auth_api.dtos.ProcessingLogResponseDto;
import com.auth_api.auth_api.dtos.request_dto.ProcessingLogRequestDto;
import com.auth_api.auth_api.services.process.ProcessingLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/process")
public class ProcessController {

    private final ProcessingLogService processService;

    public ProcessController(ProcessingLogService processService) {
        this.processService = processService;
    }

    @PostMapping
    public ResponseEntity<ProcessingLogResponseDto> process(
            @Valid @RequestBody ProcessingLogRequestDto request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        ProcessingLogResponseDto response = processService.processText (request, userDetails.getUsername());
        return ResponseEntity.ok(response);
    }
}