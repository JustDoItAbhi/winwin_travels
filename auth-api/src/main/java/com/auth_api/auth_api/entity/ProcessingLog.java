package com.auth_api.auth_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ProcessingLog extends BaseModel{
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String input_text;
    private String output_text;
    }
