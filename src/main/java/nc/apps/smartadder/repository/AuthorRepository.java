package nc.apps.smartadder.repository;

import nc.apps.smartadder.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepositoryImpl extends JpaRepository<Author,Integer> {
}
