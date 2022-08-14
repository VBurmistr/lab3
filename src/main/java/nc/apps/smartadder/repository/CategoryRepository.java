package nc.apps.smartadder.repository;

import nc.apps.smartadder.domain.Author;
import nc.apps.smartadder.domain.Book;
import nc.apps.smartadder.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByCategoryName(String categoryName);
}
