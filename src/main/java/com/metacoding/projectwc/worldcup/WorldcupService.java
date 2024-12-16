package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc._core.error.ex.Exception404;
import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.item.WorldcupItem;
import com.metacoding.projectwc.worldcup.item.WorldcupItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

    public WorldcupResponse.FindByIdDTO findById(int id) {
        Worldcup worldcupPS = worldcupRepository.findById(id).orElseThrow(() -> new Exception404("월드컵을 찾을 수 없습니다."));
        return new WorldcupResponse.FindByIdDTO(worldcupPS);
    }

    public List<WorldcupResponse.FindAllDTO> findAllByTiltle(WorldcupRequest.FindAllDTO findAllDTO) {
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
                .findAllByTitle(findAllDTO.getSearchKeyword(), sortBy, offset, findAllDTO.getSize());

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
                    return new WorldcupResponse.FindAllDTO(worldcup, worldcupItem1, worldcupItem2);
                })
                .collect(Collectors.toList());
    }

    // main 페이지 pagination을 위한 pageDTO 생성
    public WorldcupResponse.PageDTO createPageDTO(WorldcupRequest.FindAllDTO findAllDTO) {
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
        WorldcupResponse.PageDTO pageDTO = WorldcupResponse.PageDTO.builder()
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

    public List<WorldcupResponse.FindAllDTO> findAllByTiltleAndUser(WorldcupRequest.FindAllDTO findAllDTO, User user) {
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
                .findAllByTitleAndUser(findAllDTO.getSearchKeyword(), sortBy, offset, findAllDTO.getSize(), user.getId());

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
                    return new WorldcupResponse.FindAllDTO(worldcup, worldcupItem1, worldcupItem2);
                })
                .collect(Collectors.toList());
    }

    // mine 페이지 pagination을 위한 pageDTO 생성
    public WorldcupResponse.PageDTO createPageDTOForMine(WorldcupRequest.FindAllDTO findAllDTO, User user) {
        Integer userId = user.getId();
        // 해당 유저가 만든 총 월드컵 수
        int totalItems = worldcupRepository.countAllWorldcupByUser(findAllDTO.getSearchKeyword(), userId); // 전체 월드컵 수
        // 해당 유저가 만든총 페이지 수
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
        WorldcupResponse.PageDTO pageDTO = WorldcupResponse.PageDTO.builder()
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

    @Transactional
    public void update(int id, WorldcupRequest.UpdateDTO updateDTO) {
        Worldcup worldcupPS = worldcupRepository.findById(id).orElseThrow(() -> new Exception404("월드컵을 찾을 수 없습니다."));
        worldcupPS.update(updateDTO.getTitle(), updateDTO.getDescription(), updateDTO.getVisibility());
    }

    public WorldcupResponse.FindByIDForWcFormDTO findByIdForWcForm(int id) {
        Worldcup worldcupPS = worldcupRepository.findById(id).orElseThrow(() -> new Exception404("월드컵을 찾을 수 없습니다."));
        return new WorldcupResponse.FindByIDForWcFormDTO(worldcupPS);
    }

    @Transactional
    public void delete(int id) {
        Worldcup worldcupPS = worldcupRepository.findById(id).orElseThrow(() -> new Exception404("월드컵을 찾을 수 없습니다."));
        worldcupPS.softDelete();
    }

    public boolean isDeleted(int id) {
        Worldcup worldcupPS = worldcupRepository.findById(id).orElseThrow(() -> new Exception404("월드컵을 찾을 수 없습니다."));
        return worldcupPS.getIsDeleted();
    }
}
