package nc.apps.smartadder.controllers.restcontrollers;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.Book;
import nc.apps.smartadder.dto.ResponseObject;
import nc.apps.smartadder.dto.bookdtos.BookDTO;
import nc.apps.smartadder.restfacade.exceptions.RestFacadeException;
import nc.apps.smartadder.services.BookSmartAdderService;
import nc.apps.smartadder.services.exceptions.ServiceException;
import nc.apps.smartadder.services.interfaces.BookSmartAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/book")
@Slf4j
@CrossOrigin
public class MainRestController {
    private final BookSmartAdder bookSmartAdder;
    private final Bucket bucket;

    @Autowired
    public MainRestController(BookSmartAdder bookSmartAdder) {

        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofSeconds(5)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
        this.bookSmartAdder = bookSmartAdder;
    }

    @PostMapping("/addsmart")
    public ResponseEntity<ResponseObject> getBookInfoByTitle(@RequestParam(defaultValue = "") String title,
                                             @RequestParam(defaultValue = "") String author) throws DAOException, ServiceException, RestFacadeException {
        log.info("Searching for book by title:"+title+" and author:"+author);
        bookSmartAdder.addNewBook(title, author);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setSuccess(true);
        return new ResponseEntity(responseObject,HttpStatus.OK);
    }
}
