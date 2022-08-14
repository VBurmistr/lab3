package nc.apps.smartadder.repository;

import nc.apps.smartadder.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
