package study.blogback.service;

import org.springframework.http.ResponseEntity;
import study.blogback.dto.request.board.PostBoardRequestDto;
import study.blogback.dto.response.board.PostBoardResponseDto;

public interface BoardService {
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
}
