package nc.apps.smartadder.repository;

import lombok.RequiredArgsConstructor;
import nc.apps.smartadder.domain.Author;
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
class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void testAdding(){
        Author authorExpected = Author.builder()
                .firstName("testName")
                .lastName("testLastname")
                .build();
        authorRepository.save(authorExpected);
        Optional<Author> actualAuthor = authorRepository.findByFirstNameAndLastName(authorExpected.getFirstName(), authorExpected.getLastName());
        actualAuthor.ifPresentOrElse(a->{
            assertEquals(authorExpected.getFirstName()+authorExpected.getLastName(),a.getFirstName()+a.getLastName());
        },() -> {throw new RuntimeException("No authors by given name and last name");});
    }
}