package study.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import study.blogback.dto.response.board.GetFavoriteListResponseDto;
import study.blogback.entity.FavoriteEntity;
import study.blogback.entity.primaryKey.FavoritePk;
import study.blogback.repository.resultSet.GetFavoriteListResultSet;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {

    FavoriteEntity findByBoardIdAndUserEmail(Integer boardId, String userEmail);

    @Query(
        value =
        "SELECT " +
        "U.email as email, " +
        "U.nickname as nickname, " +
        "U.profileImage as profileImage " +
        "from favorite as F " +
        "inner join user as U " +
        "on  F.userEmail = U.email " +
        "where f.boardId = ?1;",
        nativeQuery = true
    )
    List<GetFavoriteListResultSet> getFavoriteList(Integer boardId);

    @Transactional
    void deleteByBoardId(Integer boardId);

}
