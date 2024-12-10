package com.metacoding.projectwc.worldcup.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorldcupItemService {
    private final WorldcupItemRepository worldcupItemRepository;
}
