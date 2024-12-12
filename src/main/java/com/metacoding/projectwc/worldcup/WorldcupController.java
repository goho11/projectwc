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

    @GetMapping("/worldcups/new-worldcup")
    public String saveWorldcup() {
        User user = User.builder().id(1).build();
        // TODO 로그인 기능 구현 시 수정 필요
//        User seesionUser = (User) session.getAttribute("sessionUser");
        int id = worldcupService.save(user);
//        int id = worldcupService.saveWorldcup(sessionUser);
        return "redirect:/worldcups/wc-form/" + id;
    }

    @GetMapping("/worldcups/wc-form/{id}")
    public String wcFormById(@PathVariable int id, Model model) {
        // TODO 유저의 월드컵 id가 맞는지 체크
        // User seesionUser = (User) session.getAttribute("sessionUser");
        // worldcupService.findById(id).getUser() 같은지 확인
        WorldcupResponse.FindByIDForWcFormDTO findByIDForWcFormDTO = worldcupService.findByIdForWcForm(id);
        model.addAttribute("model", findByIDForWcFormDTO);
        return "wc-form";
    }

    @GetMapping("/worldcups/start-form/{id}")
    public String startForm(@PathVariable("id") int id, Model model) {
        List<Integer> round = worldcupItemService.countAll(id);
        WorldcupResponse.FindByIdDTO worldcup = worldcupService.findById(id);
        model.addAttribute("allItem", round.get(0));
        model.addAttribute("round", round);
        model.addAttribute("id", id);
        model.addAttribute("worldcup", worldcup);
        return "start-form";
    }

    // 주소에서 받는 id는 월드컵아이디임 >> Worldcup 클래스 id
    @PostMapping("/worldcups/start-form/{id}")
    public String startGame(@PathVariable("id") int id, @RequestParam int round, Model model) {
        User user = User.builder().id(1).build(); // 더미유저 >> 나중에 지워야 함
        WorldcupGame saveWorldcupGame = worldcupGameService.saveWorldcupGame(id, user, round); // 게임 생성
        List<WorldcupItem> shuffledByRoundsList = worldcupItemService.getShuffledByRounds(round); // 경기 진행할 아이템 담을 리스트
        List<WorldcupItem> winnerList = new ArrayList<>(); // 승리자들을 담아 둘 리스트

        session.setAttribute("shuffledByRoundsList", shuffledByRoundsList); // 아이템들 세션저장
        session.setAttribute("winnerList", winnerList);
        session.setAttribute("worldcupGameId", saveWorldcupGame.getId());

        return "redirect:/worldcups/game/" + id;
    }

    @GetMapping("/worldcups/game/{id}")
    public String game(@PathVariable("id") int id, Model model) {
        int worldcupGameId = (int) session.getAttribute("worldcupGameId");
        WorldcupGame byId = worldcupGameService.findById(worldcupGameId);
        List<WorldcupItem> shuffledByRoundsList = (List<WorldcupItem>) session.getAttribute("shuffledByRoundsList");
        int matchNum = 1; // 초기 경기번호 지정 >> 요놈을 어떻게 해야하는데
        worldcupService.findById(id); // 레이지로딩 방지
        WorldcupMatchResponse.SaveWorldcupMatchDTO saveWorldcupMatchDTO = worldcupMatchService.saveWorldcupMatch(byId, shuffledByRoundsList.size(), matchNum, shuffledByRoundsList);

        model.addAttribute("matchNum", matchNum);
        model.addAttribute("match", saveWorldcupMatchDTO);
        model.addAttribute("totalMatchNum",shuffledByRoundsList.size()/2);

        return "game";
    }

    @GetMapping("/main")
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

    @PutMapping("/worldcups/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody WorldcupRequest.UpdateDTO updateDTO) {
        worldcupService.update(id, updateDTO);
        return ResponseEntity.ok(Resp.ok("갱신됨"));
    }

}
