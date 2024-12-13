package com.metacoding.projectwc.worldcup.game.match;

import com.metacoding.projectwc._core.error.ex.Exception404;
import com.metacoding.projectwc.worldcup.game.WorldcupGame;
import com.metacoding.projectwc.worldcup.item.WorldcupItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WorldcupMatchService {
    private final WorldcupMatchRepository worldcupMatchRepository;

    @Transactional
    public WorldcupMatchResponse.SaveWorldcupMatchDTO saveWorldcupMatch(WorldcupGame worldcupGame, int round, int matchNum, List<WorldcupItem> shuffledByRoundsList) {
        WorldcupMatch build = WorldcupMatch
                .builder()
                .worldcupGame(worldcupGame)
                .round(round)
                .matchNum(matchNum)
                .worldcupItem1(shuffledByRoundsList.get(0))
                .worldcupItem2(shuffledByRoundsList.get(1))
                .build();

        WorldcupMatch save = worldcupMatchRepository.save(build);
        return new WorldcupMatchResponse.SaveWorldcupMatchDTO(save);
    }

    @Transactional
    public void winnerUpdate(Integer id, int worldcupMatchId) {
        WorldcupMatch worldcupMatch = worldcupMatchRepository.findById(id).orElseThrow(() -> new Exception404("없는 매치 입니다."));
        worldcupMatch.update(worldcupMatchId);
    }
}
