package nc.apps.smartadder.repository;

import lombok.RequiredArgsConstructor;
import nc.apps.smartadder.domain.*;
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
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testAddingCascade(){

        Book book = Book.builder()
                .title("testTitle")
                .author(Author.builder()
                        .firstName("testName")
                        .lastName("testLastname")
                        .build())
                .category(Category.builder()
                        .categoryName("testName")
                        .build())
                .language(Language.builder()
                        .languageName("testName")
                        .build())
                .publisher(Publisher.builder()
                        .publisherName("testName")
                        .build())
                .build();

        bookRepository.save(book);
        Boolean exist = bookRepository.existsByTitleAndAuthorFirstNameAndAuthorLastName(book.getTitle(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName());
        assertTrue(exist);
    }
}