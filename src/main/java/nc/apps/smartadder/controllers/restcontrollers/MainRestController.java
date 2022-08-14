package nc.apps.smartadder.controllers.restcontrollers;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.Book;
import nc.apps.smartadder.restfacade.exceptions.RestFacadeException;
import nc.apps.smartadder.services.BookSmartAdderService;
import nc.apps.smartadder.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/book/")
@Slf4j
@CrossOrigin
public class MainRestController {
    private final BookSmartAdderService bookSmartAdderService;
    private final Bucket bucket;

    @Autowired
    public MainRestController(BookSmartAdderService bookSmartAdderService) {
        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofSeconds(5)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
        this.bookSmartAdderService = bookSmartAdderService;
    }

    @PostMapping("/addsmart")
    public ResponseEntity<Book> getBookInfoByTitle(@RequestParam(required = false) String title,
                                             @RequestParam(required = false) String author) throws DAOException, ServiceException, RestFacadeException {
        if (bucket.tryConsume(1)) {
            return new ResponseEntity<>(bookSmartAdderService.addNewBook(title,author),
                    HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();


    }
}
