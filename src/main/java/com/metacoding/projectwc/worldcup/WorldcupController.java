package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc._core.util.Resp;
import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.game.WorldcupGame;
import com.metacoding.projectwc.worldcup.game.WorldcupGameService;
import com.metacoding.projectwc.worldcup.game.match.WorldcupMatchResponse;
import com.metacoding.projectwc.worldcup.game.match.WorldcupMatchService;
import com.metacoding.projectwc.worldcup.item.WorldcupItem;
import com.metacoding.projectwc.worldcup.item.WorldcupItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class WorldcupController {
    private final WorldcupItemService worldcupItemService;
    private final WorldcupService worldcupService;
    private final WorldcupGameService worldcupGameService;
    private final WorldcupMatchService worldcupMatchService;
    private final HttpSession session;

    @GetMapping("/s/worldcups/new-worldcup")
    public String saveWorldcup(@AuthenticationPrincipal User user) {
        int id = worldcupService.save(user);
        return "redirect:/s/worldcups/" + id + "/wc-form";
    }

    @GetMapping("/s/worldcups/{id}/wc-form")
    public String wcFormById(@PathVariable int id, Model model) {
        WorldcupResponse.FindByIDForWcFormDTO findByIDForWcFormDTO = worldcupService.findByIdForWcForm(id);
        model.addAttribute("model", findByIDForWcFormDTO);
        return "wc-form";
    }

    @GetMapping("/worldcups/{id}/start-form")
    public String startForm(@PathVariable("id") int id, Model model) {
        List<Integer> roundList = worldcupItemService.getRoundList(id);
        int allItems = worldcupItemService.countAll(id);
        WorldcupResponse.FindByIdDTO worldcup = worldcupService.findById(id);
        model.addAttribute("allItem", allItems);
        model.addAttribute("fight", roundList.get(0));
        model.addAttribute("round", roundList);
        model.addAttribute("id", id);
        model.addAttribute("worldcup", worldcup);
        return "start-form";
    }

    // 주소에서 받는 id는 월드컵아이디임 >> Worldcup 클래스 id
    @PostMapping("/worldcups/{id}/start-form")
    public String startGame(@PathVariable("id") int worldcupId, @RequestParam int round, Model model) {
        User user = User.builder().id(1).build(); // 더미유저 >> 나중에 지워야 함
        WorldcupGame saveWorldcupGame = worldcupGameService.saveWorldcupGame(worldcupId, user, round); // 게임 생성
        List<WorldcupItem> shuffledByRoundsList = worldcupItemService.getShuffledByRounds(round); // 경기 진행할 아이템 담을 리스트
        List<WorldcupItem> winnerList = new ArrayList<>(); // 승리자들을 담아 둘 리스트

        session.setAttribute("sessionTotalMatchNum", shuffledByRoundsList.size() / 2);
        session.setAttribute("sessionShuffledByRoundsList", shuffledByRoundsList); // 아이템들 세션저장
        session.setAttribute("sessionWinnerList", winnerList);
        int worldcupGameId = saveWorldcupGame.getId();

        return "redirect:/worldcups/" + worldcupId + "/games/" + worldcupGameId;
    }

    // 이제 주소 id >> 게임 id
    @GetMapping("/worldcups/{worldcupId}/games/{worldcupGameId}")
    public String game(@PathVariable("worldcupId") int worldcupId, @PathVariable("worldcupGameId") int worldcupGameId, Model model) {
        WorldcupGame byId = worldcupGameService.findById(worldcupGameId);
        List<WorldcupItem> shuffledByRoundsList = (List<WorldcupItem>) session.getAttribute("sessionShuffledByRoundsList");
        int totalMatchNum = (int) session.getAttribute("sessionTotalMatchNum");
        int matchNum = 1;
        if (session.getAttribute("sessionMatchNum") == null) {
            session.setAttribute("sessionMatchNum", matchNum);
        } else {
            matchNum = (Integer) session.getAttribute("sessionMatchNum");
        }

        WorldcupMatchResponse.SaveWorldcupMatchDTO saveWorldcupMatchDTO = worldcupMatchService.saveWorldcupMatch(byId, totalMatchNum * 2, matchNum, shuffledByRoundsList);
        int sessionWorldcupMatchId = saveWorldcupMatchDTO.getId();
        session.setAttribute("sessionWorldcupMatchId", sessionWorldcupMatchId);

        model.addAttribute("modelMatchNum", matchNum);
        model.addAttribute("match", saveWorldcupMatchDTO);
        model.addAttribute("modelTotalMatchNum", totalMatchNum);

        return "game";
    }

    @GetMapping({"/main", "/"})
    public String main(Model model, WorldcupRequest.FindAllDTO findAllDTO) {

        // findAllDTO 디폴트 값은 1페이지, 사이즈는 10, 최신순
        List<WorldcupResponse.FindAllDTO> worldcupList = worldcupService.findAllByTiltle(findAllDTO);
        model.addAttribute("worldcupList", worldcupList);

        // 페이지 정보
        WorldcupResponse.PageDTO pageDTO = worldcupService.createPageDTO(findAllDTO);
        model.addAttribute("pageDTO", pageDTO);

        return "main";
    }

    @GetMapping("/s/worldcups/mine")
    public String mine(Model model, WorldcupRequest.FindAllDTO findAllDTO) {
        // TODO 로그인 기능 완성될 경우 적용
//        User seesionUser = (User) session.getAttribute("sessionUser");

        User user = User.builder().id(1).build();

        // 로그인 한 유저가 생성한 월드컵 정보만 가져온다.
        List<WorldcupResponse.FindAllDTO> worldcupList = worldcupService.findAllByTiltleAndUser(findAllDTO, user);
        model.addAttribute("worldcupList", worldcupList);

        // 페이지 정보
        WorldcupResponse.PageDTO pageDTO = worldcupService.createPageDTOForMine(findAllDTO, user);
        model.addAttribute("pageDTO", pageDTO);

        return "mine";
    }

    @PutMapping("/s/worldcups/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody WorldcupRequest.UpdateDTO updateDTO) {
        worldcupService.update(id, updateDTO);
        return ResponseEntity.ok(Resp.ok("갱신됨"));
    }

    // 주소의 아이디는 월드컵자체(원피스 최강자전) id, 세션에 들어있는 것 >> 승자리스트, 경기리스트, 월드컵 게임 id(원피스 최강자전을 플레이 중의 id), matchNum
    @PostMapping("/worldcups/{worldcupId}/games/{worldcupGameId}")
    public String playGame(@PathVariable("worldcupId") int worldcupId, @RequestParam("winner") int winner, @PathVariable("worldcupGameId") int worldcupGameId) {
        List<WorldcupItem> shuffledByRoundsList = (List<WorldcupItem>) session.getAttribute("sessionShuffledByRoundsList");
        List<WorldcupItem> winnerList = (List<WorldcupItem>) session.getAttribute("sessionWinnerList");
        int matchNum = (int) session.getAttribute("sessionMatchNum");
        WorldcupItem winnerItem = shuffledByRoundsList.get(winner);

        winnerList.add(winnerItem); // 이긴놈 승자 리스트에 담기
        int worldcupMatchId = (int) session.getAttribute("sessionWorldcupMatchId");
        worldcupMatchService.winnerUpdate(worldcupMatchId, winnerItem.getId()); // 승자 데이터 업데이트
        shuffledByRoundsList.remove(1); // 경기 진행한 아이템 2개 제거
        shuffledByRoundsList.remove(0);
        matchNum++;

        // 다음 강으로 진행하는 코드
        if (shuffledByRoundsList.isEmpty()) { // 경기 리스트가 비면
            shuffledByRoundsList.addAll(winnerList); // 승자 리스트의 값을 모두 불러오고
            winnerList.clear(); // 승자 리스트 비우기
            session.setAttribute("sessionTotalMatchNum", shuffledByRoundsList.size() / 2); // 최대 경기수 재설정
            matchNum = 1; // 현재 경기수 1로
        }

        if (shuffledByRoundsList.size() == 1) { // 경기 리스트가 1개면 >> 부전승이 없어서 1개만 남으면 무조건 끝난거임
            WorldcupItem worldcupItem = shuffledByRoundsList.get(0);
            worldcupGameService.completeGame(worldcupGameId);
            session.removeAttribute("sessionShuffledByRoundsList");
            session.removeAttribute("sessionWinnerList");
            session.removeAttribute("sessionMatchNum");
            session.removeAttribute("sessionWorldcupMatchId");
            session.removeAttribute("sessionTotalMatchNum");

            session.setAttribute("winnerItem", winnerItem);
            return "redirect:/worldcups/" + worldcupId + "/result/" + worldcupGameId;
        }

        session.setAttribute("sessionShuffledByRoundsList", shuffledByRoundsList);
        session.setAttribute("sessionWinnerList", winnerList);
        session.setAttribute("sessionMatchNum", matchNum);

        return "redirect:/worldcups/" + worldcupId + "/games/" + worldcupGameId;
    }
}
