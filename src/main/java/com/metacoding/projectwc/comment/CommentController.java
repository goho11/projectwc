package com.metacoding.projectwc.comment;

import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.Worldcup;
import com.metacoding.projectwc.worldcup.WorldcupRequest;
import com.metacoding.projectwc.worldcup.WorldcupResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;
    private final HttpSession httpSession;

    @GetMapping("/result/{worldcupId}")
    public String result2(@PathVariable Integer worldcupId, Model model, CommentRequest.PageDTO requestPageDTO) {
        List<Comment> commentList = commentService.findAll(worldcupId, requestPageDTO);
        model.addAttribute("commentList", commentList);

        CommentResponse.ResponsePageDTO responsePageDTO = commentService.createPageDTO(worldcupId, requestPageDTO);
        model.addAttribute("responsePageDTO", responsePageDTO);

        model.addAttribute("worldcupId", worldcupId);

        //TODO 위너 네임 일단 반영 안함

        return "result";
    }

    // TODO: ResponseEntity<T> 써야하나?
    @PostMapping("/result/{worldcupId}/save")
    public String saveComment(@PathVariable Integer worldcupId, CommentRequest.SaveDTO saveDTO) {
        User sessionUser = (User) httpSession.getAttribute("sessionUser");
        commentService.saveComment(saveDTO, sessionUser, worldcupId);

        // TODO 무슨 화면으로?
        return "redirect:/result/" + worldcupId;
    }

    @PostMapping("/rank/{worldcupId}/delete")
    public String deleteComment() {
        // 논리 삭제 구현
        // TODO 로그인 기능 완성될 경우 적용
//        User seesionUser = (User) session.getAttribute("sessionUser");
        User user = User.builder().id(1).build();

        // TODO 월드컵 정보는 어디서 받나?
        Worldcup worldcup = Worldcup.builder().id(1).build();

        commentService.deleteComment(user, worldcup);

        // TODO 무슨 화면으로?
        return "redirect:/";
    }
}
