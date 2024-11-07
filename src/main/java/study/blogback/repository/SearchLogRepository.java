package study.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.blogback.entity.SearchLogEntity;
import study.blogback.repository.resultSet.GetPopularListResultSet;
import study.blogback.repository.resultSet.GetRelationListResultSet;

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

    @Query (
            value =
            "SELECT relationWord as searchWord, count(relationWord) as count " +
            "from search_log " +
            "where searchWord = ?1 " +
            "AND relationWord IS NOT NULL " +
            "group by relationWord " +
            "order by count desc " +
            "limit 15; ",
            nativeQuery = true
    )
    List<GetRelationListResultSet> getRelationList(String searchWord);

}
