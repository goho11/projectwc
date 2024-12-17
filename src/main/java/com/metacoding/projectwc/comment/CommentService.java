package com.metacoding.projectwc.comment;
import com.metacoding.projectwc._core.error.ex.Exception403;
import com.metacoding.projectwc._core.error.ex.Exception404;
import com.metacoding.projectwc.user.User;
import com.metacoding.projectwc.worldcup.Worldcup;
import com.metacoding.projectwc.worldcup.WorldcupRepository;
import com.metacoding.projectwc.worldcup.WorldcupRequest;
import com.metacoding.projectwc.worldcup.WorldcupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final WorldcupRepository worldcupRepository;

    @Transactional(readOnly = false)
    public void saveComment(CommentRequest.SaveDTO saveDTO, User user, Integer worldcupId) {
        Worldcup worldcup = worldcupRepository.findById(worldcupId)
                .orElseThrow(() -> new Exception404("월드컵을 찾을 수 없습니다."));
        commentRepository.saveComment(saveDTO.toEntity(user, worldcup));
    }

    @Transactional(readOnly = false)
    public void deleteComment(User sessionUser, Integer commentId) {
        Comment commentPS = commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception404("코멘트를 찾을 수 없습니다."));

        if (!sessionUser.getId().equals(commentPS.getUser().getId())) {
            throw new Exception403("권한이 없습니다.");
        }
        commentRepository.deleteComment(commentId);
    }

    public List<Comment> findAll(Integer worldcupId, CommentRequest.PageDTO pageDTO) {
        // 오프셋
        Integer offset = (pageDTO.getPage() - 1) * pageDTO.getSize();

        List<Comment> comments = commentRepository.findAll(worldcupId, offset, pageDTO.getSize());
        return comments;
    }

    public CommentResponse.ResponsePageDTO createPageDTO(Integer worldcupId, CommentRequest.PageDTO requestPageDTO) {
        // 총 코멘트 개수
        int totalItems = commentRepository.countAllComment(worldcupId);
        // 총 페이지 수
        int totalPages = (int) Math.ceil((double) totalItems / requestPageDTO.getSize());
        // 현재 페이지
        int currentPage = requestPageDTO.getPage();

        // 페이지 리스트 생성
        List<Map<String, Object>> pages = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            Map<String, Object> page = new HashMap<>();
            page.put("number", i);
            page.put("isCurrentPage", currentPage == i);
            pages.add(page);
        }

        // 페이지 DTO 생성 및 반환
        CommentResponse.ResponsePageDTO responsePageDTO = CommentResponse.ResponsePageDTO.builder()
                .currentPage(currentPage)
                .totalPages(totalItems)
                .size(requestPageDTO.getSize())
                .isFirstPage(currentPage == 1)
                .isLastPage(currentPage == totalPages)
                .prevPage(currentPage - 1)
                .nextPage(currentPage + 1)
                .pages(pages)
                .build();

        return responsePageDTO;
    }
}
