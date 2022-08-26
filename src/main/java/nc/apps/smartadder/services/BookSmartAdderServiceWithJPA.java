package nc.apps.smartadder.services;

import nc.apps.smartadder.dao.exception.DAOException;
import nc.apps.smartadder.domain.*;
import nc.apps.smartadder.dto.bookdtos.BookDTO;
import nc.apps.smartadder.mappers.DomainToDTOMapper;
import nc.apps.smartadder.repository.*;
import nc.apps.smartadder.restfacade.exceptions.RestFacadeException;
import nc.apps.smartadder.restfacade.interfaces.BookApiFacade;
import nc.apps.smartadder.services.exceptions.ServiceException;
import nc.apps.smartadder.services.interfaces.BookSmartAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Primary
public class BookSmartAdderServiceWithJPA implements BookSmartAdder {

    private final CategoryRepository categoryRepository;
    private final LanguageRepository languageRepository;
    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;
    private final BookApiFacade bookApiFacade;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookSmartAdderServiceWithJPA(CategoryRepository categoryRepository,
                                        LanguageRepository languageRepository,
                                        PublisherRepository publisherRepository,
                                        BookRepository bookRepository,
                                        BookApiFacade bookApiFacade,
                                        AuthorRepository authorRepository) {
        this.categoryRepository = categoryRepository;
        this.languageRepository = languageRepository;
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
        this.bookApiFacade = bookApiFacade;
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public BookDTO addNewBook(String title, String author) throws RestFacadeException, ServiceException {
        Book book = bookApiFacade.getBookByTitleAndAuthor(title, author);

        boolean bookExist = bookRepository.existsByTitleAndAuthorFirstNameAndAuthorLastName(
                book.getTitle(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName());

        if(bookExist){
            throw new ServiceException("Book already exist");
        }

        Optional<Author> authorOptional = authorRepository.findByFirstNameAndLastName(
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName());
        authorOptional.ifPresent(book::setAuthor);

        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(
                book.getCategory().getCategoryName());
        categoryOptional.ifPresent(book::setCategory);

        Optional<Language> languageOptional = languageRepository.findByLanguageName(
                book.getLanguage().getLanguageName());
        languageOptional.ifPresent(book::setLanguage);

        Optional<Publisher> publisherOptional = publisherRepository.findByPublisherName(
                book.getPublisher().getPublisherName());
        publisherOptional.ifPresent(book::setPublisher);

        bookRepository.save(book);

        return DomainToDTOMapper.mapBook(book);
    }
}
