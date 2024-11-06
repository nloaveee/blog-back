package study.blogback.service;

import org.springframework.http.ResponseEntity;
import study.blogback.dto.response.search.GetPopularListResponseDto;

public interface SearchService {

    ResponseEntity<? super GetPopularListResponseDto> getPopularList();
}
