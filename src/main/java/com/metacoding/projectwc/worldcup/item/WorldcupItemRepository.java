package com.metacoding.projectwc.worldcup.item;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WorldcupItemRepository {
    private final EntityManager entityManager;

    public int countAll(int id) {
        String sql = """
                select count(*) from worldcup_item_tb where worldcup_id = :id
                """.replace(":id",String.valueOf(id));
        Object singleResult = entityManager.createNativeQuery(sql)
                .getSingleResult();
        return Integer.parseInt(singleResult.toString());
    }
}
