package study.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.blogback.entity.ImageEntity;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {
}
