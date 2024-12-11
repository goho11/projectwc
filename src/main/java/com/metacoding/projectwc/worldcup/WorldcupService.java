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

    public List<WorldcupResponse.findAllDTO> findAll(WorldcupRequest.findAllDTO findAllDTO) {
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
                .map(WorldcupResponse.findAllDTO::new)
                .toList();
    }
}
