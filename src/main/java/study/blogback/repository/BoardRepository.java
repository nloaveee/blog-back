package study.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.blogback.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {

}
