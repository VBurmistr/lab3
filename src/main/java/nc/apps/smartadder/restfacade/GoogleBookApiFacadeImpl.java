package nc.apps.smartadder.restfacade;

import lombok.extern.slf4j.Slf4j;
import nc.apps.smartadder.domain.Book;
import nc.apps.smartadder.dto.Item;
import nc.apps.smartadder.dto.Response;
import nc.apps.smartadder.dto.VolumeInfo;
import nc.apps.smartadder.mappers.VolumeInfoToBookDomain;
import nc.apps.smartadder.restfacade.exceptions.RestFacadeException;
import nc.apps.smartadder.restfacade.interfaces.BookApiFacade;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@Repository
@Slf4j
public class GoogleBookApiFacadeImpl implements BookApiFacade {
    @Override
    @Cacheable("books")
    public Book getBookByTitleAndAuthor(String title, String author) throws RestFacadeException {
        log.info("Preparing request too google.");
        String url = String.format("https://www.googleapis.com/books/v1/volumes?q=intitle:%s+inauthor:%s",title, author);
        log.info("Link: "+url);
        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.getForObject(url, Response.class);
        if (response == null || response.getItems() == null) {
            throw new RestFacadeException("Cant find your book on google");
        }
        Optional<VolumeInfo> bookInfoOptional = Arrays.stream(response.getItems())
                .map(Item::getVolumeInfo)
                .filter((b) ->
                    b.getAuthors() != null &&
                            b.getLanguage() != null &&
                            b.getCategories() != null &&
                            b.getPublisher() != null &&
                            b.getTitle() != null
                ).findFirst();
        return bookInfoOptional.map(VolumeInfoToBookDomain::map)
                .orElseThrow(()->new RestFacadeException("Cant find book that satisfying all fields."));
    }
}
