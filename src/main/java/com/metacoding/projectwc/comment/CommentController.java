package com.metacoding.projectwc.comment;

import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.Worldcup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;

    // TODO: ResponseEntity<T> 써야하나?
    @PostMapping("comments")
    public String saveComment(CommentRequest.saveDTO saveDTO) {
        // TODO 로그인 기능 완성될 경우 적용
//        User seesionUser = (User) session.getAttribute("sessionUser");
        User user = User.builder().id(1).build();

        // TODO 월드컵 정보는 어디서 받나?
        Worldcup worldcup = Worldcup.builder().id(1).build();

        commentService.saveComment(saveDTO, user, worldcup);

        // TODO 무슨 화면으로?
        return null;
    }

    @PutMapping("comments")
    public String deleteComment() {
        // 논리 삭제
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
