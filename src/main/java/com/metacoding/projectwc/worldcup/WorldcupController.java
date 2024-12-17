package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc._core.error.ex.Exception400;
import com.metacoding.projectwc._core.error.ex.Exception404;
import com.metacoding.projectwc._core.util.Resp;
import com.metacoding.projectwc.comment.CommentRequest;
import com.metacoding.projectwc.comment.CommentResponse;
import com.metacoding.projectwc.comment.CommentService;
import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.game.WorldcupGame;
import com.metacoding.projectwc.worldcup.game.WorldcupGameService;
import com.metacoding.projectwc.worldcup.game.match.WorldcupMatchResponse;
import com.metacoding.projectwc.worldcup.game.match.WorldcupMatchService;
import com.metacoding.projectwc.worldcup.item.WorldcupItem;
import com.metacoding.projectwc.worldcup.item.WorldcupItemResponse;
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
    private final CommentService commentService;
    private final HttpSession httpSession;

    @GetMapping("/s/worldcups/new-worldcup")
    public String saveWorldcup(@AuthenticationPrincipal User user) {
        int id = worldcupService.save(user);
        return "redirect:/s/worldcups/" + id + "/wc-form";
    }

    @GetMapping("/s/worldcups/{worldcupId}/wc-form")
    public String wcFormById(@PathVariable int worldcupId, Model model) {
        if (worldcupService.isDeleted(worldcupId))
            throw new Exception404("월드컵을 찾을 수 없습니다.");
        WorldcupResponse.FindByIDForWcFormDTO findByIDForWcFormDTO = worldcupService.findByIdForWcForm(worldcupId);
        model.addAttribute("model", findByIDForWcFormDTO);
        return "wc-form";
    }

    @GetMapping("/worldcups/{worldcupId}/start-form")
    public String startForm(@PathVariable int worldcupId, Model model) {
        if (worldcupService.isDeleted(worldcupId))
            throw new Exception404("월드컵을 찾을 수 없습니다.");
        List<Integer> roundList = worldcupItemService.getRoundList(worldcupId);
        int allItems = worldcupItemService.countAll(worldcupId);
        WorldcupResponse.FindByIdDTO worldcup = worldcupService.findById(worldcupId);
        model.addAttribute("allItem", allItems);
        model.addAttribute("fight", roundList.get(0));
        model.addAttribute("round", roundList);
        model.addAttribute("id", worldcupId);
        model.addAttribute("worldcup", worldcup);
        return "start-form";
    }

    // 주소에서 받는 id는 월드컵아이디임 >> Worldcup 클래스 id
    @PostMapping("/worldcups/{worldcupId}/start-form")
    public String startGame(@PathVariable int worldcupId, @RequestParam int round, Model model) {
        User sessionUser = (User) httpSession.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new Exception400("현재 비로그인 플레이 기능은 구현되어있지 않습니다.");
        }
        WorldcupGame saveWorldcupGame = worldcupGameService.saveWorldcupGame(worldcupId, sessionUser, round); // 게임 생성
        List<WorldcupItem> shuffledByRoundsList = worldcupItemService.getShuffledByRounds(worldcupId, round); // 경기 진행할 아이템 담을 리스트
        List<WorldcupItem> winnerList = new ArrayList<>(); // 승리자들을 담아 둘 리스트

        removeSessionGame();

        httpSession.setAttribute("sessionTotalMatchNum", shuffledByRoundsList.size() / 2);
        httpSession.setAttribute("sessionShuffledByRoundsList", shuffledByRoundsList); // 아이템들 세션저장
        httpSession.setAttribute("sessionWinnerList", winnerList);
        int worldcupGameId = saveWorldcupGame.getId();

        return "redirect:/worldcups/" + worldcupId + "/games/" + worldcupGameId;
    }

    private void removeSessionGame() {
        httpSession.removeAttribute("sessionShuffledByRoundsList");
        httpSession.removeAttribute("sessionWinnerList");
        httpSession.removeAttribute("sessionMatchNum");
        httpSession.removeAttribute("sessionWorldcupMatchId");
        httpSession.removeAttribute("sessionTotalMatchNum");
    }

    // 이제 주소 id >> 게임 id
    @GetMapping("/worldcups/{worldcupId}/games/{worldcupGameId}")
    public String game(@PathVariable int worldcupId, @PathVariable int worldcupGameId, Model model) {
        if (worldcupService.isDeleted(worldcupId))
            throw new Exception404("월드컵을 찾을 수 없습니다.");
        WorldcupGame byId = worldcupGameService.findById(worldcupGameId);
        List<WorldcupItem> shuffledByRoundsList = (List<WorldcupItem>) httpSession.getAttribute("sessionShuffledByRoundsList");
        int totalMatchNum = (int) httpSession.getAttribute("sessionTotalMatchNum");
        Integer matchNum = (Integer) httpSession.getAttribute("sessionMatchNum");
        if (matchNum == null) {
            matchNum = 1;
            httpSession.setAttribute("sessionMatchNum", matchNum);
        }

        WorldcupMatchResponse.SaveWorldcupMatchDTO saveWorldcupMatchDTO = worldcupMatchService.saveWorldcupMatch(byId, totalMatchNum * 2, matchNum, shuffledByRoundsList);
        int sessionWorldcupMatchId = saveWorldcupMatchDTO.getId();
        httpSession.setAttribute("sessionWorldcupMatchId", sessionWorldcupMatchId);

        model.addAttribute("modelMatchNum", matchNum);
        model.addAttribute("match", saveWorldcupMatchDTO);
        model.addAttribute("modelTotalMatchNum", totalMatchNum);

        return "game";
    }

    // 주소의 아이디는 월드컵자체(원피스 최강자전) id, 세션에 들어있는 것 >> 승자리스트, 경기리스트, 월드컵 게임 id(원피스 최강자전을 플레이 중의 id), matchNum
    @PostMapping("/worldcups/{worldcupId}/games/{worldcupGameId}")
    public String playGame(@PathVariable int worldcupId, @RequestParam int winner, @RequestParam int loser, @PathVariable int worldcupGameId) {
        List<WorldcupItem> shuffledByRoundsList = (List<WorldcupItem>) httpSession.getAttribute("sessionShuffledByRoundsList");
        List<WorldcupItem> winnerList = (List<WorldcupItem>) httpSession.getAttribute("sessionWinnerList");
        int matchNum = (int) httpSession.getAttribute("sessionMatchNum");
        WorldcupItem winnerItem = shuffledByRoundsList.get(winner);

        winnerList.add(winnerItem); // 이긴놈 승자 리스트에 담기
        int worldcupMatchId = (int) httpSession.getAttribute("sessionWorldcupMatchId");
        worldcupMatchService.matchResultUpdate(worldcupMatchId, winnerItem); // 승자 데이터 업데이트

        shuffledByRoundsList.remove(1); // 경기 진행한 아이템 2개 제거
        shuffledByRoundsList.remove(0);
        matchNum++;

        // 다음 강으로 진행하는 코드
        if (shuffledByRoundsList.isEmpty()) { // 경기 리스트가 비면
            shuffledByRoundsList.addAll(winnerList); // 승자 리스트의 값을 모두 불러오고
            winnerList.clear(); // 승자 리스트 비우기
            httpSession.setAttribute("sessionTotalMatchNum", shuffledByRoundsList.size() / 2); // 최대 경기수 재설정
            matchNum = 1; // 현재 경기수 1로
        }

        if (shuffledByRoundsList.size() == 1) { // 경기 리스트가 1개면 >> 부전승이 없어서 1개만 남으면 무조건 끝난거임
            worldcupGameService.completeGame(worldcupGameId, worldcupId);
            removeSessionGame();

            httpSession.setAttribute("sessionWinnerItem", winnerItem);
            return "redirect:/worldcups/" + worldcupId + "/result/" + worldcupGameId;
        }

        httpSession.setAttribute("sessionShuffledByRoundsList", shuffledByRoundsList);
        httpSession.setAttribute("sessionWinnerList", winnerList);
        httpSession.setAttribute("sessionMatchNum", matchNum);

        return "redirect:/worldcups/" + worldcupId + "/games/" + worldcupGameId;
    }

    @GetMapping("/worldcups/{worldcupId}/result/{worldcupGameId}")
    public String result(@PathVariable("worldcupId") int worldcupId, @PathVariable("worldcupGameId") int worldcupGameId, Model model, CommentRequest.PageDTO requestPageDTO) {
        WorldcupItem winnerItem = (WorldcupItem) httpSession.getAttribute("sessionWinnerItem");
        model.addAttribute("winnerItem", winnerItem);
        model.addAttribute("worldcupId", worldcupId);
        model.addAttribute("worldcupGameId", worldcupGameId);

        User sessionUser = (User) httpSession.getAttribute("sessionUser");
        List<CommentResponse.FindAllDTO> commentList = commentService.findAll(worldcupId, requestPageDTO, sessionUser);
        model.addAttribute("commentList", commentList);

        CommentResponse.ResponsePageDTO responsePageDTO = commentService.createPageDTO(worldcupId, requestPageDTO);
        model.addAttribute("responsePageDTO", responsePageDTO);
        return "result";
    }

    @PostMapping("/worldcups/{worldcupId}/result/{worldcupGameId}/save")
    public String saveComment(@PathVariable Integer worldcupId, @PathVariable Integer worldcupGameId, CommentRequest.SaveDTO saveDTO) {
        User sessionUser = (User) httpSession.getAttribute("sessionUser");
        WorldcupItem winnerItem = (WorldcupItem) httpSession.getAttribute("sessionWinnerItem");
        commentService.saveComment(saveDTO, sessionUser, worldcupId, winnerItem.getItemname());

        return "redirect:/worldcups/" + worldcupId + "/result/" + worldcupGameId;
    }

    @PostMapping("/worldcups/{worldcupId}/result/{worldcupGameId}/delete/{id}")
    public String deleteComment(@PathVariable Integer worldcupId, @PathVariable Integer id, @PathVariable Integer worldcupGameId) {
        // 논리 삭제 구현
        User seesionUser = (User) httpSession.getAttribute("sessionUser");
        commentService.deleteComment(seesionUser, id);

        return "redirect:/worldcups/" + worldcupId + "/result/" + worldcupGameId;
    }

    @GetMapping("/worldcups/{worldcupId}/rank")
    public String rank(@PathVariable("worldcupId") int worldcupId, Model model, CommentRequest.PageDTO requestPageDTO) {
        User sessionUser = (User) httpSession.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new Exception400("현재 비로그인 플레이 기능은 구현되어있지 않습니다.");
        }

        List<WorldcupItem> allItem = worldcupItemService.getAllItem(worldcupId);
        int gamesCompleted = worldcupService.findById(worldcupId).getGamesCompleted();

        List<WorldcupItemResponse.RankDTO> rankList = worldcupItemService.getRankDTOList(allItem, gamesCompleted);
        model.addAttribute("rankList", rankList);
        
        List<CommentResponse.FindAllDTO> commentList = commentService.findAll(worldcupId, requestPageDTO, sessionUser);
        model.addAttribute("commentList", commentList);

        CommentResponse.ResponsePageDTO responsePageDTO = commentService.createPageDTO(worldcupId, requestPageDTO);
        model.addAttribute("responsePageDTO", responsePageDTO);

        return "rank";
    }

    @PostMapping("/worldcups/{worldcupId}/rank/save")
    public String saveComment2(@PathVariable Integer worldcupId, CommentRequest.SaveDTO saveDTO) {
        User sessionUser = (User) httpSession.getAttribute("sessionUser");
        WorldcupItem winnerItem = (WorldcupItem) httpSession.getAttribute("sessionWinnerItem");
        if (winnerItem == null)
            commentService.saveComment(saveDTO, sessionUser, worldcupId, null);
        else
            commentService.saveComment(saveDTO, sessionUser, worldcupId, winnerItem.getItemname());

        return "redirect:/worldcups/" + worldcupId+"/rank";
    }

    @PostMapping("/worldcups/{worldcupId}/rank/delete/{id}")
    public String deleteComment2(@PathVariable Integer worldcupId, @PathVariable Integer id) {
        // 논리 삭제 구현
        User seesionUser = (User) httpSession.getAttribute("sessionUser");
        commentService.deleteComment(seesionUser, id);

        return "redirect:/worldcups/" + worldcupId + "/rank";
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
        User seesionUser = (User) httpSession.getAttribute("sessionUser");

        // 로그인 한 유저가 생성한 월드컵 정보만 가져온다.
        List<WorldcupResponse.FindAllDTO> worldcupList = worldcupService.findAllByTiltleAndUser(findAllDTO, seesionUser);
        model.addAttribute("worldcupList", worldcupList);

        // 페이지 정보
        WorldcupResponse.PageDTO pageDTO = worldcupService.createPageDTOForMine(findAllDTO, seesionUser);
        model.addAttribute("pageDTO", pageDTO);

        return "mine";
    }

    @PutMapping("/s/worldcups/{worldcupId}")
    public ResponseEntity<?> update(@PathVariable int worldcupId, @RequestBody WorldcupRequest.UpdateDTO updateDTO) {
        worldcupService.update(worldcupId, updateDTO);
        return ResponseEntity.ok(Resp.ok("됨"));
    }

    @DeleteMapping("/s/worldcups/{worldcupId}")
    public ResponseEntity<?> delete(@PathVariable int worldcupId) {
        worldcupService.delete(worldcupId);
        return ResponseEntity.ok(Resp.ok("됨"));
    }

}
