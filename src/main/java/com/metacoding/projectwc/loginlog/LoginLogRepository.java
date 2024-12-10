package com.metacoding.projectwc.loginlog;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LoginLogRepository {
    private final EntityManager entityManager;
}
