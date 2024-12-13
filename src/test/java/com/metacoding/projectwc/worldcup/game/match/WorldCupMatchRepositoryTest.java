package com.metacoding.projectwc.worldcup.game.match;

import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.Worldcup;
import com.metacoding.projectwc.worldcup.game.WorldcupGame;
import com.metacoding.projectwc.worldcup.item.WorldcupItem;
import com.metacoding.projectwc.worldcup.item.WorldcupItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import({WorldcupMatchRepository.class})
@DataJpaTest
public class WorldCupMatchRepositoryTest {

    @Autowired
    private WorldcupMatchRepository worldcupMatchRepository;


    @Test
    public void save_test() {
        // given
        User user = User.builder().id(1).build();
        Worldcup worldcup = Worldcup.builder().id(1).user(user).build();
        WorldcupItem worldcupItem1 = WorldcupItem.builder().id(1).build();
        WorldcupItem worldcupItem2 = WorldcupItem.builder().id(2).build();
        WorldcupGame worldcupGame = WorldcupGame.builder().id(1).user(user).worldcup(worldcup).build();
        int round = 8;
        int matchNum = 1;

        WorldcupMatch build = WorldcupMatch
                .builder()
                .worldcupGame(worldcupGame)
                .round(round)
                .matchNum(matchNum)
                .worldcupItem1(worldcupItem1)
                .worldcupItem2(worldcupItem2)
                .build();
        // when
        WorldcupMatch save = worldcupMatchRepository.save(build);

        // eye
        System.out.println(save.getId());
    }

    @Test
    public void findById_test() {
        int id = 1;
        WorldcupMatch worldcupMatch = worldcupMatchRepository.findById(id).get();

        System.out.println(worldcupMatch.getId() );

    }
}
