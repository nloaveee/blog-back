package study.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import study.blogback.entity.ImageEntity;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {

    List<ImageEntity> findByBoardId(Integer boardId);

    @Transactional
    void deleteByBoardId(Integer boardId);
}
