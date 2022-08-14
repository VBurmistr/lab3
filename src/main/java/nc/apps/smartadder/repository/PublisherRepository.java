package nc.apps.smartadder.repository;

import nc.apps.smartadder.domain.Author;
import nc.apps.smartadder.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher,Integer> {
    Optional<Publisher> findByPublisherName(String publisherName);
}
