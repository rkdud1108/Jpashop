package jpabook.jpashop.repository;

import jpabook.jpashop.domain.File.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files, Integer> {
    Files findByFno(int fno);
}
