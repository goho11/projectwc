package com.metacoding.projectwc.worldcup.item;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WorldcupItemRepository {
    private final EntityManager entityManager;
}
