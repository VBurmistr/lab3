package nc.apps.smartadder.repository;

import lombok.RequiredArgsConstructor;
import nc.apps.smartadder.domain.Author;
import nc.apps.smartadder.domain.Category;
import nc.apps.smartadder.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@RequiredArgsConstructor
@Rollback
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void testAdding(){
        Category categoryExpected = Category.builder()
                .categoryName("testName")
                .build();
        categoryRepository.save(categoryExpected);
        Optional<Category> actualCategory = categoryRepository.findByCategoryName(categoryExpected.getCategoryName());
        actualCategory.ifPresentOrElse(a->{
            assertEquals(categoryExpected.getCategoryName(),a.getCategoryName());
        },() -> {throw new RuntimeException("No category's by given name and last name");});
    }
}