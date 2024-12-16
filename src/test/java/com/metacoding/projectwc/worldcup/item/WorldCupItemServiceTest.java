package com.metacoding.projectwc.worldcup.item;

import com.metacoding.projectwc.worldcup.WorldcupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import({WorldcupItemService.class, WorldcupItemRepository.class, WorldcupRepository.class})
@DataJpaTest
public class WorldCupItemServiceTest {
    @Autowired
    private WorldcupItemService worldcupItemService;

    @Test
    public void countAll_test() {
        // given
        int id = 1;

        // when
        List<Integer> integers = worldcupItemService.getRoundList(id);

        //eye
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    @Test
    public void getShuffledByRounds_test() {
        // given
        int round = 15;
        int worldcupId = 1;

        // when
        List<WorldcupItem> shuffledByRounds = worldcupItemService.getShuffledByRounds(worldcupId, round);

        // eye
        System.out.println(shuffledByRounds.size());
        for (WorldcupItem shuffledByRound : shuffledByRounds) {
            System.out.println(shuffledByRound.getItemname());
            System.out.println(shuffledByRound.getImgUrl());
            System.out.println("=========");
        }
    }

    @Test
    public void findByWorldcupIdAndNameOrderByOption_test() {
        // given
        int id = 1;
        WorldcupItemRequest.FindOptionsDTO findOptionsDTO = new WorldcupItemRequest.FindOptionsDTO();
        findOptionsDTO.setPage(1);
        findOptionsDTO.setSize(10);
        findOptionsDTO.setItemname("");
        //when
        WorldcupItemResponse.RenderingDTO byWorldcupIdAndNameOrderByOption = worldcupItemService.findByWorldcupIdAndNameOrderByOption(1, findOptionsDTO);
        System.out.println(byWorldcupIdAndNameOrderByOption);
    }
}
