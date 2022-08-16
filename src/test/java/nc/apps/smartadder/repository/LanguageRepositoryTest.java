package nc.apps.smartadder.repository;

import lombok.RequiredArgsConstructor;
import nc.apps.smartadder.domain.Author;
import nc.apps.smartadder.domain.Language;
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
class LanguageRepositoryTest {
    @Autowired
    LanguageRepository languageRepository;

    @Test
    public void testAdding(){
        Language languageExpected = Language.builder()
                .languageName("testName")
                .build();
        languageRepository.save(languageExpected);
        Optional<Language> actualLanguage = languageRepository.findByLanguageName(languageExpected.getLanguageName());
        actualLanguage.ifPresentOrElse(a->{
            assertEquals(languageExpected.getLanguageName(),a.getLanguageName());
        },() -> {throw new RuntimeException("No languages by given name");});
    }
}