package com.metacoding.projectwc.worldcup.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WorldcupItemController {
    private final WorldcupItemService worldcupItemService;
}
