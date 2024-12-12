package com.metacoding.projectwc.comment;

import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.Worldcup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(CommentRepository.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void saveComment_test() {
        // given
        User user = User.builder().id(1).build();
        Worldcup worldcup = Worldcup.builder().id(1).build();
        Comment comment = Comment.builder()
                .nickname("nickname")
                .content("content")
                .user(user)
                .worldcup(worldcup)
                .build();

        // when
        commentRepository.saveComment(comment);

        // then
        Comment savedComment = commentRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        System.out.println(savedComment.getNickname());
        System.out.println(savedComment.getContent());
        System.out.println(savedComment.getUser().getId());
        System.out.println(savedComment.getWorldcup().getId());

    }
}
