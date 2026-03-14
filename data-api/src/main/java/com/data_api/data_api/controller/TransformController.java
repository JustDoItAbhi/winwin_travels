package com.data_api.data_api.controller;

import com.data_api.data_api.dto.TransformRequestDto;
import com.data_api.data_api.dto.TransformResponseDto;
import com.data_api.data_api.service.TransformService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/transform")
public class TransformController {
    @Autowired
    private TransformService transformService;

    @PostMapping
    public ResponseEntity<TransformResponseDto> transform(
            @Valid @RequestBody TransformRequestDto request,
            @RequestHeader("X-Internal-Token") String internalToken
    ) {

        TransformResponseDto response = transformService.transforms(request, internalToken);
        return ResponseEntity.ok(response);
    }
}
