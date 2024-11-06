package study.blogback.service.implement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import study.blogback.dto.response.ResponseDto;
import study.blogback.dto.response.search.GetPopularListResponseDto;
import study.blogback.repository.SearchLogRepository;
import study.blogback.repository.resultSet.GetPopularListResultSet;
import study.blogback.service.SearchService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService {

    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {

        List<GetPopularListResultSet> resultSet = new ArrayList<>();
        try {

            resultSet = searchLogRepository.getPopularList();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPopularListResponseDto.success(resultSet);
    }
}
