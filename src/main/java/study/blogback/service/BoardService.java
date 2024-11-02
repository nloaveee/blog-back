package study.blogback.service;

import org.springframework.http.ResponseEntity;
import study.blogback.dto.request.board.PostBoardRequestDto;
import study.blogback.dto.request.board.PostCommentRequestDto;
import study.blogback.dto.response.board.*;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardId);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardId);
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardId);

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardId, String email);

    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardId, String email);

    ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardId);
}
