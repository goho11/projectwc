package com.metacoding.projectwc.worldcup.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorldcupGameService {
    private final WorldcupGameRepository worldcupGameRepository;
}
