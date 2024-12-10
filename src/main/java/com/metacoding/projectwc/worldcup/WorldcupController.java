package com.metacoding.projectwc.worldcup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class WorldcupController {
    private final WorldcupService worldcupService;
}
