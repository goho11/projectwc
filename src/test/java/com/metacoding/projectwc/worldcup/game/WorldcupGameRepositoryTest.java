package com.metacoding.projectwc.worldcup.game;

import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.WorldcupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({WorldcupGameRepository.class, WorldcupRepository.class})
@DataJpaTest
public class WorldcupGameRepositoryTest {

    @Autowired
    private WorldcupGameRepository worldcupGameRepository;
    @Autowired
    private WorldcupRepository worldcupRepository;

    @Test
    public void findById_test() {
        int id = 1;

        User user = User.builder().id(1).build();
        int round = 16;
        worldcupGameRepository.save(WorldcupGame.builder().worldcup(worldcupRepository.findById(id).get()).user(user).startRound(round).build());

        WorldcupGame worldcupGame = worldcupGameRepository.findById(id).get();

        System.out.println(worldcupGame.getId());
        System.out.println(worldcupGame.getWorldcup().getTitle());
    }
}
