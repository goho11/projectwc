package com.metacoding.projectwc.worldcup.game;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WorldcupGameRepository {
    private final EntityManager entityManager;
}
