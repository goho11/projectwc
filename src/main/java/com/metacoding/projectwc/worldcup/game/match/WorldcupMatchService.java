package com.metacoding.projectwc.worldcup.game.match;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorldcupMatchService {
    private final WorldcupMatchRepository worldcupMatchRepository;
}
