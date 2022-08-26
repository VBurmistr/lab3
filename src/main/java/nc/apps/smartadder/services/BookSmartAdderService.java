package nc.apps.smartadder.services;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.dao.interfaces.*;
import nc.apps.smartadder.domain.Book;
import nc.apps.smartadder.dto.bookdtos.BookDTO;
import nc.apps.smartadder.mappers.DomainToDTOMapper;
import nc.apps.smartadder.restfacade.exceptions.RestFacadeException;
import nc.apps.smartadder.restfacade.interfaces.BookApiFacade;
import nc.apps.smartadder.services.interfaces.BookSmartAdder;
import org.springframework.stereotype.Service;

public class BookSmartAdderService implements BookSmartAdder {

    private final AuthorDAO authorDAO;
    private final CategoryDAO categoryDAO;
    private final PublisherDAO publisherDAO;
    private final LanguageDAO languageDAO;
    private final BookApiFacade bookApiFacade;
    private final BookDAO bookDAO;

    public BookSmartAdderService(BookApiFacade bookApiFacade, AuthorDAO authorDAO, CategoryDAO categoryDAO, PublisherDAO publisherDAO, LanguageDAO languageDAO, BookDAO bookDAO) {
        this.bookApiFacade = bookApiFacade;
        this.authorDAO = authorDAO;
        this.categoryDAO = categoryDAO;
        this.publisherDAO = publisherDAO;
        this.languageDAO = languageDAO;
        this.bookDAO = bookDAO;
    }

    public BookDTO addNewBook(String title, String author) throws  DAOException, RestFacadeException {

        Book book = bookApiFacade.getBookByTitleAndAuthor(title, author);

        Integer authorId = authorDAO.getIdByValues(book.getAuthor());
        if (authorId == null) {
            authorId = authorDAO.save(book.getAuthor());
        }
        book.getAuthor().setId(authorId);

        Integer categoryId = categoryDAO.getIdByValues(book.getCategory());
        if (categoryId == null) {
            categoryId = categoryDAO.save(book.getCategory());
        }
        book.getCategory().setId(categoryId);

        Integer languageId = languageDAO.getIdByValues(book.getLanguage());
        if (languageId == null) {
            languageId = languageDAO.save(book.getLanguage());
        }
        book.getLanguage().setId(languageId);

        Integer publisherId = publisherDAO.getIdByValues(book.getPublisher());
        if (publisherId == null) {
            publisherId = publisherDAO.save(book.getPublisher());
        }
        book.getPublisher().setId(publisherId);
        bookDAO.save(book);

        return DomainToDTOMapper.mapBook(book);
    }
}
