package study.blogback.service;

import org.springframework.http.ResponseEntity;
import study.blogback.dto.response.search.GetPopularListResponseDto;
import study.blogback.dto.response.search.GetRelationListResponseDto;

public interface SearchService {

    ResponseEntity<? super GetPopularListResponseDto> getPopularList();
    ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);
}
