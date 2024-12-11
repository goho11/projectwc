package com.metacoding.projectwc.worldcup.item;

import com.metacoding.projectwc.worldcup.Worldcup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import({Worldcup.class, WorldcupItemRepository.class})
@DataJpaTest
public class WorldcupItemRepositoryTest {

    @Autowired
    private WorldcupItemRepository worldcupItemRepository;

    @Test
    public void save_test() {
        // given
        WorldcupItem worldcupItem = WorldcupItem.builder().itemname("아이템이름").imgUrl("/img/test.png").worldcup(Worldcup.builder().id(1).build()).build();
        // when
        WorldcupItem worldcupItemPS = worldcupItemRepository.save(worldcupItem);
        // then
        System.out.println(worldcupItemPS.getId());
        System.out.println(worldcupItemPS.getItemname());
        System.out.println(worldcupItemPS.getImgUrl());
        System.out.println(worldcupItemPS.getTotalCount());
    }

    @Test
    public void countAll_test() {
        // given
        int id = 1;

        // when
        int i = worldcupItemRepository.countAll(id);

        // eye
        System.out.println("총 갯수: " + i);
    }

    @Test
    public void findTwoItems_test() {
        // given
        int id = 1;

        // when
        List<WorldcupItem> worldcupItems = worldcupItemRepository.findTwoItems(id);

        // eye
        for (WorldcupItem worldcupItem : worldcupItems) {
            System.out.println(worldcupItem.getItemname());
            System.out.println(worldcupItem.getChampionCount());
        }
    }
}
