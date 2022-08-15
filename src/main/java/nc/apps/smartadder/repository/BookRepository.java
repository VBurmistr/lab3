package nc.apps.smartadder.repository;

import nc.apps.smartadder.domain.Author;
import nc.apps.smartadder.domain.Book;
import nc.apps.smartadder.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {
    Boolean existsByTitleAndAuthorFirstNameAndAuthorLastName(String title,String authorFirstName,String authorLastName);
}
