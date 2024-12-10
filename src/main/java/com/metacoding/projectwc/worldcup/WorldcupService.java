package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc._core.error.ex.Exception400;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorldcupService {
    private final WorldcupRepository worldcupRepository;

    public WorldcupResponse.findByIDDTO findById(int id) {
        Worldcup worldcup = worldcupRepository.findById(id).orElseThrow(() -> new Exception400("Worldcup not found"));
        return new WorldcupResponse.findByIDDTO(worldcup);

    }
}
