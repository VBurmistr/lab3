package nc.apps.smartadder.repository;

import lombok.RequiredArgsConstructor;
import nc.apps.smartadder.domain.Author;
import nc.apps.smartadder.domain.Publisher;
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
class PublisherRepositoryTest {
    @Autowired
    PublisherRepository publisherRepository;

    @Test
    void testAdding(){
        Publisher publisherExpected = Publisher.builder()
                .publisherName("testName")
                .build();
        publisherRepository.save(publisherExpected);
        Optional<Publisher> actualPublisher = publisherRepository.findByPublisherName(publisherExpected.getPublisherName());

        if(actualPublisher.isPresent()){
            assertEquals(publisherExpected.getPublisherName(),actualPublisher.get().getPublisherName());
        }else{
            throw new RuntimeException("No languages by given name");
        }
    }
  
}