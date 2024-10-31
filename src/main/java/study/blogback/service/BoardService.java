package study.blogback.service;

import org.springframework.http.ResponseEntity;
import study.blogback.dto.request.board.PostBoardRequestDto;
import study.blogback.dto.response.board.GetBoardResponseDto;
import study.blogback.dto.response.board.PostBoardResponseDto;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardId);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
}
