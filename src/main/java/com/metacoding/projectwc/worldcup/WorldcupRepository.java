package com.metacoding.projectwc.worldcup;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WorldcupRepository {
    private final EntityManager entityManager;
}
