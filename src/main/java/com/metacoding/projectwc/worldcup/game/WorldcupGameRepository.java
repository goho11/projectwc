package com.metacoding.projectwc.worldcup.game;

import com.metacoding.projectwc.worldcup.Worldcup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class WorldcupGameRepository {
    private final EntityManager entityManager;

    public WorldcupGame save(WorldcupGame game) {
        entityManager.persist(game);
        return game;
    }

    public Optional<WorldcupGame> findById(int id) {
        String sql = """
                select wg from WorldcupGame wg
                join fetch wg.worldcup wc
                where wg.id = :id
                """;
        Query query = entityManager.createQuery(sql, WorldcupGame.class).setParameter("id", id);

        return Optional.ofNullable((WorldcupGame) query.getSingleResult());
    }
}
