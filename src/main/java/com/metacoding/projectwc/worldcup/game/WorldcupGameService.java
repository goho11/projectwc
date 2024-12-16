package com.metacoding.projectwc.worldcup.game;

import com.metacoding.projectwc._core.error.ex.Exception404;
import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.Worldcup;
import com.metacoding.projectwc.worldcup.WorldcupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WorldcupGameService {
    private final WorldcupGameRepository worldcupGameRepository;
    private final WorldcupRepository worldcupRepository;

    @Transactional
    public WorldcupGame saveWorldcupGame(int id, User user, int round) {
        Worldcup worldcup = worldcupRepository.findById(id).orElseThrow(() -> new Exception404("없는 월드컵 입니다."));
        if (worldcup.getIsDeleted())
            throw new Exception404("없는 월드컵 입니다.");
        WorldcupGame build = WorldcupGame.builder().worldcup(worldcup).user(user).startRound(round).build();
        WorldcupGame worldcupGamePS = worldcupGameRepository.save(build);
        return worldcupGamePS;
    }

    public WorldcupGame findById(int worldcupGameId) {
        WorldcupGame worldcupGame = worldcupGameRepository.findById(worldcupGameId).orElseThrow(() -> new Exception404("없는 경기 입니다."));
        return worldcupGame;
    }

    @Transactional
    public void completeGame(int worldcupGameId, int worldcupId) {
        WorldcupGame worldcupGame = worldcupGameRepository.findById(worldcupGameId).orElseThrow(() -> new Exception404("없는 경기 입니다."));
        Worldcup worldcup = worldcupRepository.findById(worldcupGameId).orElseThrow(() -> new Exception404("없는 월드컵 입니다."));
        worldcup.completeGame();
        worldcupGame.completeUpadate();
    }
}
