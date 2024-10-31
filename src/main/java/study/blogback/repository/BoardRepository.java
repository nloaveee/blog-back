package study.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.blogback.entity.BoardEntity;
import study.blogback.repository.resultSet.GetBoardResultSet;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {

    BoardEntity findByBoardId(Integer boardId);

    @Query (
            value =
            "SELECT " +
            "B.boardId as boardId, " +
            "B.title as title, " +
            "B.content as content, " +
            "B.writeDateTime as writeDateTime, " +
            "B.writerEmail as writerEmail, " +
            "U.nickname as writerNickname, " +
            "U.profileImage as writerProfileImage " +
            "from board as B " +
            "inner join user AS U " +
            "on B.writerEmail = U.email " +
            "where boardId = ?1 ",
            nativeQuery = true
    )
    GetBoardResultSet getBoard(Integer boardId);
}
