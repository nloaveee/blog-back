package study.blogback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import study.blogback.dto.request.board.PostBoardRequestDto;
import study.blogback.dto.response.board.GetBoardResponseDto;
import study.blogback.dto.response.board.PostBoardResponseDto;
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
}
