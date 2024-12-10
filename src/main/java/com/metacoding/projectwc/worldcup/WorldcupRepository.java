package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc.user.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class WorldcupRepository {
    private final EntityManager entityManager;

    public Worldcup save(User user) {
        Worldcup worldcup = Worldcup.builder().user(user).build();
        entityManager.persist(worldcup);
        return worldcup;
    }

    public Optional<Worldcup> findById(int id) {
        return Optional.ofNullable(entityManager.find(Worldcup.class, id));
    }
}
