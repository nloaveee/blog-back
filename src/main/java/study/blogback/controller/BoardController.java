package study.blogback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import study.blogback.dto.request.board.PostBoardRequestDto;
import study.blogback.dto.request.board.PostCommentRequestDto;
import study.blogback.dto.response.board.*;
import study.blogback.service.BoardService;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{boardId}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(@PathVariable Integer boardId) {
        ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardId);
        return response;
    }

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
            @RequestBody @Valid PostBoardRequestDto requestBody,
            @AuthenticationPrincipal String email) {
        ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(requestBody, email);
        return response;
    }

    @PostMapping("/{boardId}/comment")
    public ResponseEntity<? super PostCommentResponseDto> postComment(
            @RequestBody @Valid PostCommentRequestDto requestBody,
            @PathVariable("boardId") Integer boardId,
            @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PostCommentResponseDto> response = boardService.postComment(requestBody, boardId, email);
        return response;
    }

    /**
     * 게시물 좋아요 리스트
     */
    @GetMapping("/{boardId}/favorite-list")
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(
            @PathVariable("boardId") Integer boardId) {
        ResponseEntity<? super GetFavoriteListResponseDto> response = boardService.getFavoriteList(boardId);
        return response;
    }

    /**
     * 게시물 댓글 리스트
     */
    @GetMapping("/{boardId}/comment-list")
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(
            @PathVariable("boardId") Integer boardId
    ) {
        ResponseEntity<? super GetCommentListResponseDto> response = boardService.getCommentList(boardId);
        return response;
    }

    @GetMapping ("/{boardId}/increase-view-count")
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(
            @PathVariable("boardId") Integer boardId
    ) {
        ResponseEntity<? super IncreaseViewCountResponseDto> response = boardService.increaseViewCount(boardId);
        return response;
    }

    @PutMapping("/{boardId}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
        @PathVariable("boardId") Integer boardId,
        @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PutFavoriteResponseDto> response = boardService.putFavorite(boardId, email);
        return response;
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(
            @PathVariable("boardId") Integer boardId,
            @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super DeleteBoardResponseDto> response = boardService.deleteBoard(boardId, email);
        return response;
    }

}
