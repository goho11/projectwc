package com.metacoding.projectwc.worldcup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(WorldcupRepository.class)
@DataJpaTest
public class WorldCupRepositoryTest {

    @Autowired
    private WorldcupRepository repository;

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Worldcup worldcup = repository.findById(id).orElseThrow(() -> new RuntimeException("Could not find id: " + id));
        System.out.println(worldcup.getId());
        System.out.println(worldcup.getUser());
        System.out.println(worldcup.getTitle());
        System.out.println(worldcup.getVisibility());
    }
}
