package com.auth_api.auth_api.repositories;

import com.auth_api.auth_api.entity.ProcessingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProcessingLogRepository extends JpaRepository<ProcessingLog, UUID> {
}
