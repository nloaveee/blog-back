package study.blogback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import study.blogback.entity.CertificationEntity;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, String> {

    CertificationEntity findByUserId(String userId);

    @Transactional
    void deleteByUserId(String userId);
}
