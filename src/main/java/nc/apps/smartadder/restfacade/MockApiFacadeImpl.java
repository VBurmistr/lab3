package nc.apps.smartadder.restfacade;

import lombok.extern.slf4j.Slf4j;
import nc.apps.smartadder.domain.Book;
import nc.apps.smartadder.dto.Item;
import nc.apps.smartadder.dto.Response;
import nc.apps.smartadder.dto.VolumeInfo;
import nc.apps.smartadder.mappers.VolumeInfoToBookDomain;
import nc.apps.smartadder.restfacade.exceptions.RestFacadeException;
import nc.apps.smartadder.restfacade.interfaces.BookApiFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@Repository
@Profile({"dev","default"})
@Primary
@Slf4j
public class MockApiFacadeImpl implements BookApiFacade {
    private final String mockApiUrl;
    public MockApiFacadeImpl(@Value("${mockapi.url}") String mockApiUrl) {
        this.mockApiUrl = mockApiUrl;
    }
    @Override
    @Cacheable("books")
    public Book getBookByTitleAndAuthor(String title, String author) throws RestFacadeException {
        log.info("Preparing request too mockapi.");
        String url = mockApiUrl+"books/"+Math.round(Math.random()*100);
        log.info("Link: "+url);
        RestTemplate restTemplate = new RestTemplate();
        Book book = restTemplate.getForObject(url, Book.class);
        if (book == null) {
            throw new RestFacadeException("Cant find your book on google");
        }
        return book;
    }
}
