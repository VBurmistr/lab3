package nc.apps.smartadder.restfacade.interfaces;

import nc.apps.smartadder.domain.Book;
import nc.apps.smartadder.restfacade.exceptions.RestFacadeException;

public interface BookApiFacade {
    Book getBookByTitleAndAuthor(String title, String author) throws RestFacadeException;
}
