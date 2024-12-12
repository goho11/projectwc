package com.metacoding.projectwc.worldcup;


import com.metacoding.projectwc.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;


@Import(WorldcupRepository.class)
@DataJpaTest
public class WorldcupRepositoryTest {

    @Autowired
    private WorldcupRepository worldcupRepository;

    @Test
    public void save_test() {
        // given
        User user=User.builder().id(1).build();
        Worldcup worldcup = Worldcup.builder().user(user).build();
        // when
        Worldcup worldcupPS = worldcupRepository.save(worldcup);
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

    @Test
    public void findAllByTiltle_test() {
        // given -> 더미데이터
        WorldcupRequest.findAllDTO findAllDTO = new WorldcupRequest.findAllDTO();
        Integer offset = (findAllDTO.getPage() - 1) * findAllDTO.getSize();
//        findAllDTO.setSize(20);

//        findAllDTO.setSortBy("gamesCompleted");
        String sortBy;
        // 최신순
        if (findAllDTO.getSortBy().equals("Latest")) {
            sortBy = "createdAt";
        } else { // Popularity 인기순
            sortBy = "gamesCompleted";
        }
        findAllDTO.setSearchKeyword("검색");

        // when
        List<Worldcup> worldcupList = worldcupRepository.findAllByTiltle(findAllDTO.getSearchKeyword(), sortBy, offset, findAllDTO.getSize());

        // then
        for (Worldcup wc : worldcupList) {
            System.out.println(wc);
        }
    }

    @Test
    public void countAllWorldcup_test() {
        String searchKeyword = "";
        int countAll = worldcupRepository.countAllWorldcup(searchKeyword);

        System.out.println("총 월드컵 숫자: " + countAll);
        Assertions.assertThat(countAll).isGreaterThan(0);
    }

    @Test
    public void findAllByTiltleAndUser_test() {
        // given -> 더미데이터
        WorldcupRequest.findAllDTO findAllDTO = new WorldcupRequest.findAllDTO();
        Integer offset = (findAllDTO.getPage() - 1) * findAllDTO.getSize();
        String sortBy = "createdAt";
        Integer userId = 1;

        // when
        List<Worldcup> worldcupList = worldcupRepository.findAllByTiltleAndUser(findAllDTO.getSearchKeyword(), sortBy, offset, findAllDTO.getSize(), userId);

        // then
        for (Worldcup wc : worldcupList) {
            System.out.println(wc);
        }
    }

    @Test
    public void countAllWorldcupByUser_test() {
        String searchKeyword = "";
        Integer userId = 1;
        int countAll = worldcupRepository.countAllWorldcupByUser(searchKeyword, userId);

        System.out.println("총 월드컵 숫자: " + countAll);
        Assertions.assertThat(countAll).isGreaterThan(0);
    }
}
