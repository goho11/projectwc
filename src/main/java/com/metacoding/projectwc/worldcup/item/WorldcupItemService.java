package com.metacoding.projectwc.worldcup.item;

import com.metacoding.projectwc._core.error.ex.APIException404;
import com.metacoding.projectwc._core.util.FileUtil;
import com.metacoding.projectwc.worldcup.Worldcup;
import com.metacoding.projectwc.worldcup.WorldcupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WorldcupItemService {
    private final WorldcupItemRepository worldcupItemRepository;
    private final WorldcupRepository worldcupRepository;

    @Transactional
    public void save(WorldcupItemRequest.SaveDTO saveDTO, Worldcup worldcup) {
        String imgUrl = FileUtil.fileSave(saveDTO.getFile());
        worldcupItemRepository.save(saveDTO.toEntity(imgUrl, worldcup));
    }

    // TODO 2를 제외한 2의 제곱만 받도록 >>  4 8 16 32 64 128 ...
    public List<Integer> getRoundList(int id) {
        int countAll = worldcupItemRepository.countAll(id); // item 갯수만 받아오기
        List<Integer> roundList = new ArrayList<Integer>(); // '강'수를 담을 리스트
        // 3개 이하면 빈 상태로 반환
        if (countAll <= 3) {
            return roundList;
        }

        // 4개 초과면 강수 계산 해서 담아서 반환
        for (int i = 4; i <= countAll; ) {
            roundList.add(i);
            i = i * 2;
        }

        Collections.reverse(roundList); // 배열 뒤집기
        return roundList;
    }

    public List<WorldcupItem> getShuffledByRounds(int round) {
        List<WorldcupItem> findAll = worldcupItemRepository.findAll();
        Collections.shuffle(findAll);
        List<WorldcupItem> shuffled = findAll.subList(0, round);
        return shuffled;
    }

    public int countAll(int id) {
        return worldcupItemRepository.countAll(id);
    }

    public WorldcupItemResponse.RenderingDTO findByWorldcupIdAndNameOrderByOption(int id, WorldcupItemRequest.FindOptionsDTO findOptionsDTO) {
        int gamesCompleted = worldcupRepository.findGamesCompletedById(id);
        int itemSize = worldcupItemRepository.countByWorldcupIdAndNameOrderByOption(id, findOptionsDTO.getItemname());
        List<WorldcupItem> worldcupItemList = worldcupItemRepository.findByWorldcupIdAndNameOrderByOption(id, findOptionsDTO.getItemname(), findOptionsDTO.getOrderOption(), findOptionsDTO.getOffset(), findOptionsDTO.getLimit());
        return new WorldcupItemResponse.RenderingDTO(itemSize, findOptionsDTO.getSize(), gamesCompleted, worldcupItemList);
    }

    @Transactional
    public void updateName(int itemId, WorldcupItemRequest.UpdateNameDTO updateNameDTO) {
        WorldcupItem worldcupItemPS = worldcupItemRepository.findById(itemId).orElseThrow(() -> new APIException404("없는 월드컵 아이템입니다."));
        worldcupItemPS.updateItemname(updateNameDTO.getItemname());
    }

    @Transactional
    public void updateImg(int itemId, WorldcupItemRequest.UpdateImgDTO updateImgDTO) {
        WorldcupItem worldcupItemPS = worldcupItemRepository.findById(itemId).orElseThrow(() -> new APIException404("없는 월드컵 아이템입니다."));
        String imgUrl = FileUtil.fileSave(updateImgDTO.getFile());
        worldcupItemPS.updateImgUrl(imgUrl);
    }

    @Transactional
    public void delete(int itemId) {
        WorldcupItem worldcupItemPS = worldcupItemRepository.findById(itemId).orElseThrow(() -> new APIException404("없는 월드컵 아이템입니다."));
        worldcupItemPS.softDelete();
    }
}
