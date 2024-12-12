package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc._core.util.Resp;
import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.item.WorldcupItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class WorldcupController {
    private final WorldcupService worldcupService;
    private final WorldcupItemService worldcupItemService;
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
        WorldcupResponse.FindByIDDTO worldcup = worldcupService.findById(id);
        model.addAttribute("allItem", round.get(0));
        model.addAttribute("round", round);
        model.addAttribute("id", id);
        model.addAttribute("worldcup", worldcup);
        return "start-form";
    }

    @PostMapping("/worldcups/start-form/{id}")
    public String startGame(@PathVariable("id") int id, @RequestParam int round) {
        System.out.println(round);

        return "redirect:/game";
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
