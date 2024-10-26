package study.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.blogback.entity.CommentEntity;

@Repository
public interface CommentRepositiory extends JpaRepository<CommentEntity, Integer> {
}
