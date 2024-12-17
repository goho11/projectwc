package com.metacoding.projectwc.comment;

import com.metacoding.projectwc._core.error.ex.Exception404;
import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.Worldcup;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

@Import(CommentRepository.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void findAll_test() {
        // 테스트 안 된 상태
        List<Comment> commentList = commentRepository.findAll(1, 0, 5);

//        for (Comment comment : commentList) {
//            System.out.println("코멘트 내용은: " + comment.getContent());
//        }

        Comment savedComment = commentRepository.findById(1)
                .orElseThrow(() -> new Exception404("코멘트를 찾을 수 없습니다."));
        Comment savedComment2 = commentRepository.findById(2)
                .orElseThrow(() -> new Exception404("코멘트를 찾을 수 없습니다."));
        Comment savedComment3 = commentRepository.findById(3)
                .orElseThrow(() -> new Exception404("코멘트를 찾을 수 없습니다."));
        Comment savedComment4 = commentRepository.findById(4)
                .orElseThrow(() -> new Exception404("코멘트를 찾을 수 없습니다."));
        Comment savedComment5 = commentRepository.findById(5)
                .orElseThrow(() -> new Exception404("코멘트를 찾을 수 없습니다."));

        List<Comment> list = new ArrayList<>();
        list.add(savedComment);
        list.add(savedComment2);
        list.add(savedComment3);
        list.add(savedComment4);
        list.add(savedComment5);
        System.out.println("리스트 크기는 : " + list.size());
    }

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
                .orElseThrow(() -> new Exception404("코멘트를 찾을 수 없습니다."));

        System.out.println(savedComment.getNickname());
        System.out.println(savedComment.getContent());
        System.out.println(savedComment.getUser().getId());
        System.out.println(savedComment.getWorldcup().getId());
    }

    @Test
    public void deleteComment_test() {
        // given
        User user = User.builder().id(1).build();
        Worldcup worldcup = Worldcup.builder().id(1).build();
        Comment comment = Comment.builder()
                .nickname("nickname")
                .content("content")
                .user(user)
                .worldcup(worldcup)
                .build();

        commentRepository.saveComment(comment);
        System.out.println("1. insert 쿼리 발동함");
        //em.clear(); // PC 초기화

        Comment commentPS = commentRepository.findById(1)
                .orElseThrow(() -> new Exception404("코멘트를 찾을 수 없습니다."));
        System.out.println("2. select 쿼리 발동 안함 (캐싱됨)");
        System.out.println("코멘트 아이디는 : " + commentPS.getId());

        commentRepository.deleteComment(1); // delete
        entityManager.clear();
        System.out.println("3. 강제 clear 함 - db에 쿼리는 날라갔고, PC에 남아있는 찌거기 날려버리기");

        Comment comment2 = commentRepository.findById(1)
                .orElseThrow(() -> new Exception404("코멘트를 찾을 수 없습니다."));
        System.out.println("4. 재조회 쿼리 실행");

        System.out.println("코멘트 삭제 여부: " + comment2.getIsDeleted());

    }

    @Test
    public void countAllComment_test() {
        Integer i = commentRepository.countAllComment(1);
        System.out.println("코멘트 숫자는: " + i);
    }

    @Test
    public void findAll2_test() {
        List<Comment> commentList = commentRepository.findAll(2, 0, 5);

        System.out.println("코멘트 숫자는: " + commentList.size());

    }
}
