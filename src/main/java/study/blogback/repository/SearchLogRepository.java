package study.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.blogback.entity.SearchLogEntity;
import study.blogback.repository.resultSet.GetPopularListResultSet;

import java.util.List;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer> {

    @Query(
            value =
            "SELECT " +
            "searchWord as searchWord, count(searchWord) as count " +
            "FROM search_log " +
            "WHERE relation IS FALSE " +
            "GROUP BY searchWord " +
            "ORDER BY count DESC " +
            "LIMIT 15 ",
            nativeQuery = true
    )
    List<GetPopularListResultSet> getPopularList();

}
