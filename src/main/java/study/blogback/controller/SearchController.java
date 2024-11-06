package study.blogback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.blogback.dto.response.search.GetPopularListResponseDto;
import study.blogback.service.SearchService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    /**
     * 인기 검색어 불러오기
     */
    @GetMapping("/popular-list")
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {
        ResponseEntity<? super GetPopularListResponseDto> response = searchService.getPopularList();
        return response;
    }
}
