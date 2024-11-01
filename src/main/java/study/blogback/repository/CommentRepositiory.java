package study.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.blogback.entity.CommentEntity;
import study.blogback.repository.resultSet.GetCommentListResultSet;

import java.util.List;

@Repository
public interface CommentRepositiory extends JpaRepository<CommentEntity, Integer> {

    @Query(
            value =
            "SELECT " +
            "U.nickname AS nickname, " +
            "U.profileImage AS profileImage, " +
            "C.writeDateTime AS writeDateTime, " +
            "C.content AS content " +
            "FROM comment AS C " +
            "INNER JOIN user AS U " +
            "ON  C.userEmail = U.email " +
            "WHERE C.boardId = ?1 " +
            "ORDER BY writeDateTime DESC",
            nativeQuery = true
    )
    List<GetCommentListResultSet> getCommentList(Integer boardId);
}
