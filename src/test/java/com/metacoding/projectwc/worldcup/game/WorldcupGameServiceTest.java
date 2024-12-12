package com.metacoding.projectwc.worldcup.game;

import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.WorldcupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({WorldcupGameService.class, WorldcupGameRepositoryTest.class, WorldcupRepository.class})
@DataJpaTest
public class WorldcupGameServiceTest {

    @Autowired
    private WorldcupGameService worldcupGameService;

    @Test
    public void saveWorldcupGame_test() {
        // given (int id, User user, int round)
        int id = 1;
        User user = User.builder().id(1).build();
        int round = 16;

        // when
        WorldcupGame worldcupGame = worldcupGameService.saveWorldcupGame(id, user, round);

        // eye
        System.out.println(worldcupGame.getId());
        System.out.println(worldcupGame.getCreatedAt().toString());

    }@Test
    public void findById_test() {
        // given (int id, User user, int round)
        int id = 1;
        User user = User.builder().id(1).build();
        int round = 16;
        WorldcupGame worldcupGame = worldcupGameService.saveWorldcupGame(id, user, round);

        // when
        WorldcupGame byId = worldcupGameService.findById(id);

        // eye
        System.out.println(byId.getWorldcup().getTitle());

    }

}
