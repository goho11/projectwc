package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc._core.error.ex.Exception404;
import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.item.WorldcupItem;
import com.metacoding.projectwc.worldcup.item.WorldcupItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WorldcupService {
    private final WorldcupRepository worldcupRepository;
    private final WorldcupItemRepository worldcupItemRepository;

    @Transactional
    public int save(User user) {
        Worldcup worldcup = Worldcup.builder().user(user).build();
        Worldcup worldcupPS = worldcupRepository.save(worldcup);
        return worldcupPS.getId();
    }

    public WorldcupResponse.findByIDDTO findById(int id) {
        Worldcup worldcupPS = worldcupRepository.findById(id).orElseThrow(() -> new Exception404("Worldcup not found"));
        return new WorldcupResponse.findByIDDTO(worldcupPS);
    }

    public List<WorldcupResponse.findAllDTO> findAllByTiltle(WorldcupRequest.findAllDTO findAllDTO) {
        // 오프셋
        Integer offset = (findAllDTO.getPage() - 1) * findAllDTO.getSize();
        String sortBy;
        // Latest 최신순
        if (findAllDTO.getSortBy().equals("Latest")) {
            sortBy = "createdAt";
        // Popularity 인기순
        } else {
            sortBy = "gamesCompleted";
        }
        List<Worldcup> worldcupList = worldcupRepository
                .findAllByTiltle(findAllDTO.getSearchKeyword(), sortBy, offset, findAllDTO.getSize());

        return worldcupList.stream()
                .map(worldcup -> {
                    List<WorldcupItem> worldcupItems = worldcupItemRepository.findTwoItems(worldcup.getId());
                    WorldcupItem worldcupItem1;
                    WorldcupItem worldcupItem2;
                    if (worldcupItems.size() == 2) {
                        worldcupItem1 = worldcupItems.get(0);
                        worldcupItem2 = worldcupItems.get(1);

                    } else if (worldcupItems.size() == 1) {
                        worldcupItem1 = worldcupItems.get(0);
                        worldcupItem2 = WorldcupItem.builder()
                                .itemname("해당 이미지 없음")
                                .imgUrl("이미지 없음")
                                .build();
                    } else {
                        worldcupItem1 = WorldcupItem.builder()
                            .itemname("해당 이미지 없음")
                            .imgUrl("이미지 없음")
                            .build();
                        worldcupItem2 = WorldcupItem.builder()
                                .itemname("해당 이미지 없음")
                                .imgUrl("이미지 없음")
                                .build();
                    }
                    return new WorldcupResponse.findAllDTO(worldcup, worldcupItem1, worldcupItem2);
                })
                .collect(Collectors.toList());
    }



    public WorldcupResponse.pageDTO createPageDTO(WorldcupRequest.findAllDTO findAllDTO) {
        // 총 월드컵 개수
        int totalItems = worldcupRepository.countAllWorldcup(findAllDTO.getSearchKeyword()); // 전체 월드컵 수
        // 총 페이지 수
        int totalPages = (int) Math.ceil((double) totalItems / findAllDTO.getSize());
        // 현재 페이지
        int currentPage = findAllDTO.getPage();

        // 페이지 리스트 생성
        List<Map<String, Object>> pages = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            Map<String, Object> page = new HashMap<>();
            page.put("number", i);
            page.put("isCurrentPage", currentPage == i);
            pages.add(page);
        }
        WorldcupResponse.pageDTO pageDTO = WorldcupResponse.pageDTO.builder()
                .currentPage(currentPage)
                .totalPages(totalItems)
                .size(findAllDTO.getSize())
                .sortBy(findAllDTO.getSortBy())
                .searchKeyword(findAllDTO.getSearchKeyword())
                .isFirstPage(currentPage == 1)
                .isLastPage(currentPage == totalPages)
                .prevPage(currentPage - 1)
                .nextPage(currentPage + 1)
                .pages(pages)
                .build();

        return pageDTO;
    }
}
