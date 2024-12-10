package com.metacoding.projectwc.worldcup.game.match;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WorldcupMatchRepository {
    private final EntityManager entityManager;
}
