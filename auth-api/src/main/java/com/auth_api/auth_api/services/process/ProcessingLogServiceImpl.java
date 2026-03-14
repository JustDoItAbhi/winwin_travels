package com.auth_api.auth_api.services.process;

import com.auth_api.auth_api.dtos.ProcessingLogResponseDto;
import com.auth_api.auth_api.dtos.TransformResponseDto;
import com.auth_api.auth_api.dtos.request_dto.ProcessingLogRequestDto;
import com.auth_api.auth_api.entity.ProcessingLog;
import com.auth_api.auth_api.entity.User;
import com.auth_api.auth_api.exceptions.IncorrectPathForServiceB;
import com.auth_api.auth_api.exceptions.WinUserNotFountException;
import com.auth_api.auth_api.repositories.ProcessingLogRepository;
import com.auth_api.auth_api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Transactional
public class ProcessingLogServiceImpl implements ProcessingLogService {
    private ProcessingLogRepository processingLogRepository;
    private RestTemplate restTemplate;
    private UserRepository userRepository;

    public ProcessingLogServiceImpl(ProcessingLogRepository processingLogRepository, RestTemplate restTemplate, UserRepository userRepository) {
        this.processingLogRepository = processingLogRepository;
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }
    @Value("${app.internal-token}")
    private String personalTokan;

    @Value("${app.data-api.url}")
    private String dataApiUrl;

    @Override
    public ProcessingLogResponseDto processText(ProcessingLogRequestDto request, String userEmail) {
        if(request==null || request.getText()==null || request.getText().trim().isEmpty()) {
            throw new IncorrectPathForServiceB("TEXT CANNOT BE EMPTY ");
        }
        HttpHeaders headers=new HttpHeaders();
        headers.set("X-internal-Token", personalTokan);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String,String>>entityMap=new HttpEntity<>(
                Map.of("text",request.getText()),
                headers
        );
        ResponseEntity<TransformResponseDto> response;
     try {
         response = restTemplate.exchange(
                 dataApiUrl + "/api/transform",
                 HttpMethod.POST,
                 entityMap,
                 TransformResponseDto.class
         );
     }catch (Exception e){
         throw new RuntimeException(" unable to call Service B "+e.getMessage());
    }
     TransformResponseDto dto=response.getBody();
     if(dto==null|| dto.getText()==null){
         throw new IncorrectPathForServiceB("INPUT RESPONSE NOT VALID "+dto.getText());
     }
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new WinUserNotFountException("User not found: " + userEmail));

        ProcessingLog processingLog=new ProcessingLog();
        processingLog.setUser(user);
        processingLog.setInput_text(request.getText());
        processingLog.setOutput_text(dto.getText());
        processingLogRepository.save(processingLog);
        return fromProcessingEntity(processingLog);
    }

    private ProcessingLogResponseDto fromProcessingEntity(ProcessingLog log){
        ProcessingLogResponseDto dto=new ProcessingLogResponseDto();
        dto.setOutput_text(log.getOutput_text());
        return dto;
    }
}
