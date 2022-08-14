package nc.apps.smartadder.repository;

import nc.apps.smartadder.domain.Author;
import nc.apps.smartadder.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
    Optional<Language> findByLanguageName(String languageName);

}
