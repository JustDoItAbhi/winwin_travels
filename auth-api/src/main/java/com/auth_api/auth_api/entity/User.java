package com.auth_api.auth_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseModel{
    @Column(unique = true)
    private String email;
    private String password_hash;
    @OneToMany(fetch = FetchType.LAZY)
    private List<ProcessingLog>processingLogList;
}
