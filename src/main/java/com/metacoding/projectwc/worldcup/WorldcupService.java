package com.metacoding.projectwc.worldcup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorldcupService {
    private final WorldcupRepository worldcupRepository;
}
