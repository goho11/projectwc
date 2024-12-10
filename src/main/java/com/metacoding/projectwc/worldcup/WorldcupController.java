package com.metacoding.projectwc.worldcup;

import com.metacoding.projectwc.user.User;
import jakarta.servlet.http.HttpSession;
import com.metacoding.projectwc.worldcup.item.WorldcupItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class WorldcupController {
    private final WorldcupItemService worldcupItemService;
    private final WorldcupService worldcupService;
    private final HttpSession session;

    @GetMapping("/wc-form")
    public String wcForm() {
        User user = User.builder().id(1).build();
//        User seesionUser = (User) session.getAttribute("sessionUser");
        int id = worldcupService.saveWorldcup(user);
//        int id = worldcupService.saveWorldcup(sessionUser);
        return "redirect:/wc-form/" + id;
    }

    @GetMapping("/wc-form/{id}")
    public String wcFormById(@PathVariable int id) {
        return "wc-form";
    }

    @GetMapping("/worldcups/start-form/{id}")
    public String startForm(@PathVariable("id") int id, Model model) {
        List<Integer> round = worldcupItemService.countAll(id);
        WorldcupResponse.findByIDDTO worldcup = worldcupService.findById(id);
        model.addAttribute("allItem",round.get(0));
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
}
