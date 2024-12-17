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


        return "result";
    }

}
