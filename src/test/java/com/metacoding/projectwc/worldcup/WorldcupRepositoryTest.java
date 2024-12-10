package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(WorldcupRepository.class)
@DataJpaTest
public class WorldcupRepositoryTest {
    @Autowired
    private WorldcupRepository worldcupRepository;

    @Test
    public void save_test() {
        // given
        User user=User.builder().id(1).build();
        // when
        Worldcup worldcupPS = worldcupRepository.save(user);
        // then
        System.out.println(worldcupPS.getId());
        System.out.println(worldcupPS.getTitle());
    }
    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Worldcup worldcup = worldcupRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find id: " + id));

        // then
        System.out.println(worldcup.getId());
        System.out.println(worldcup.getUser());
        System.out.println(worldcup.getTitle());
        System.out.println(worldcup.getVisibility());
    }
}
