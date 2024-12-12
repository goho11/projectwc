package com.metacoding.projectwc.worldcup.game.match;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WorldcupMatchRepository {
    private final EntityManager entityManager;

    public WorldcupMatch save(WorldcupMatch match) {
        entityManager.persist(match);
        return match;
    }
}
