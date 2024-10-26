package study.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.blogback.entity.SearchLogEntity;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer> {
}
