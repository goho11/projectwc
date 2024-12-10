package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc._core.error.ex.Exception400;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Worldcup worldcup = worldcupRepository.findById(id).orElseThrow(() -> new Exception400("Worldcup not found"));
        return new WorldcupResponse.findByIDDTO(worldcup);

    }
}
