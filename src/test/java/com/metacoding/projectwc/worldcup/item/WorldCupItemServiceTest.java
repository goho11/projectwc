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
        List<Integer> integers = worldcupItemService.countAll(id);

        //eye
        for (Integer integer : integers) {
            System.out.println(integer);
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
