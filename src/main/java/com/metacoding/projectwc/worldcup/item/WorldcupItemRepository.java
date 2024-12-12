package com.metacoding.projectwc.worldcup.item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class WorldcupItemRepository {
    private final EntityManager entityManager;

    public WorldcupItem save(WorldcupItem worldcupItem) {
        entityManager.persist(worldcupItem);
        return worldcupItem;
    }

    public List<WorldcupItem> findByWorldcupIdAndNameOrderByOption(int id, String itemname, String orderOption, int offset, int limit) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from worldcup_item_tb where is_deleted = false and worldcup_id = :id".replace(":id", String.valueOf(id)));
        if (itemname != null && !itemname.isEmpty())
            stringBuilder.append(" and itemname like '%:itemname%'".replace(":itemname", itemname));
        if (orderOption != null) stringBuilder.append(" order by :orderOption".replace(":orderOption", orderOption));
        else stringBuilder.append(" order by id desc");
        stringBuilder.append(" limit :offset, :limit".replace(":offset", String.valueOf(offset)).replace(":limit", String.valueOf(limit)));
        String sql = stringBuilder.toString();
        return entityManager.createNativeQuery(sql, WorldcupItem.class).getResultList();
    }

    public int countByWorldcupIdAndNameOrderByOption(int id, String itemname) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from worldcup_item_tb where is_deleted = false and worldcup_id = :id".replace(":id", String.valueOf(id)));
        if (itemname != null && !itemname.isEmpty())
            stringBuilder.append(" and itemname like '%:itemname%'".replace(":itemname", itemname));
        String sql = stringBuilder.toString();
        Object singleResult = entityManager.createNativeQuery(sql).getSingleResult();
        return Integer.parseInt(singleResult.toString());
    }

    public int countAll(int id) {
        String sql = """
                select count(*) from worldcup_item_tb where worldcup_id = :id
                """.replace(":id", String.valueOf(id));
        Object singleResult = entityManager.createNativeQuery(sql).getSingleResult();
        return Integer.parseInt(singleResult.toString());
    }



    public List<WorldcupItem> findTwoItems(Integer id) {
        String jpql = "SELECT w FROM WorldcupItem w WHERE w.worldcup.id = :worldcupId AND w.isDeleted = false ORDER BY w.championCount DESC";
        TypedQuery<WorldcupItem> query = entityManager.createQuery(jpql, WorldcupItem.class);
        query.setParameter("worldcupId", id);
        query.setMaxResults(2); // 최대 결과 수를 2개로 제한

        return query.getResultList();
    }
}
