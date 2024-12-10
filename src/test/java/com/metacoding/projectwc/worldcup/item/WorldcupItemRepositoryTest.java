package com.metacoding.projectwc.worldcup.item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(WorldcupItemRepository.class)
@DataJpaTest
public class WorldcupItemRepositoryTest {

    @Autowired
    WorldcupItemRepository worldcupItemRepository;

    @Test
    public void countAll_test() {
        // given
        int id = 1;

        // when
        int i = worldcupItemRepository.countAll(id);

        // eye
        System.out.println("총 갯수: " + i);
    }
}
