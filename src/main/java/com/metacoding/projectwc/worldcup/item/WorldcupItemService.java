package com.metacoding.projectwc.worldcup.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WorldcupItemService {
    private final WorldcupItemRepository worldcupItemRepository;

    public List<Integer> countAll(int id) {
        int countAll = worldcupItemRepository.countAll(id); // item 갯수만 받아오기
        List<Integer> roundList = new ArrayList<Integer>(); // '강'수를 담을 리스트
        // 아무것도 없으면 빈 상태로 반환
        if (countAll == 0) {
            return roundList;
        }
        // 2개이하면 바로 담아서 반환
        if (countAll <= 2) {
            roundList.add(countAll);
            return roundList;
        }
        // 2개 초과면 강수 계산 해서 담아서 반환
        for(int i = 2; i < countAll;) {
            roundList.add(i);
            i = i * 2;
        }
        roundList.add(countAll);
        Collections.reverse(roundList); // 배열 뒤집기
        return roundList;
    }
}
