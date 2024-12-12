package com.metacoding.projectwc.comment;

import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.Worldcup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional(readOnly = false)
    public void saveComment(CommentRequest.saveDTO saveDTO, User user, Worldcup worldcup) {
        commentRepository.saveComment(saveDTO.toEntity(user, worldcup));

    }

//    @Transactional(readOnly = false)
//    public void deleteComment(Integer id) {
//        commentRepository.deleteComment();
//    }

}
