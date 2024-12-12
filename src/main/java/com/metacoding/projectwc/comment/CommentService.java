package com.metacoding.projectwc.comment;
import com.metacoding.projectwc._core.error.ex.Exception404;
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
    public void saveComment(CommentRequest.SaveDTO saveDTO, User user, Worldcup worldcup) {
        commentRepository.saveComment(saveDTO.toEntity(user, worldcup));
    }

    @Transactional(readOnly = false)
    public void deleteComment(User user, Worldcup worldcup) {
        Comment commentPS = commentRepository.findById(1)
                .orElseThrow(() -> new Exception404("코멘트를 찾을 수 없습니다."));

        // TODO 댓글 삭제한 유저 맞는지 검증
//        if (!sessionUser.getId().equals(commentPS.getUser().getId())) {
//            throw new Exception403("권한이 없습니다.");
//        }

        Integer commentId = commentPS.getId();
        commentRepository.deleteComment(commentId);
    }
}
