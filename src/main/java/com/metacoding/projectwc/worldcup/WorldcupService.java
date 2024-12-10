package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc._core.error.ex.Exception404;
import com.metacoding.projectwc.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WorldcupService {
    private final WorldcupRepository worldcupRepository;

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

    public List<WorldcupResponse.findAllDTO> findAll() {
        // TODO: WorldcupRequest.findAllDTO -> 리미트, 오프셋, 검색 타입, 검색 단어, 정렬 기준 적용하는 리포지토리 메서드 작성 필요

        return worldcupRepository.findAll().stream()
                .map(WorldcupResponse.findAllDTO::new)
                .toList();
    }
}
