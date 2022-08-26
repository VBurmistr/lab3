package nc.apps.smartadder.mappers;

import nc.apps.smartadder.domain.Book;
import nc.apps.smartadder.dto.bookdtos.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class DomainToDTOMapper {
    private DomainToDTOMapper(){}
    public static BookDTO mapBook(Book book) {
        BookDTO bookDTO = BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .language(LanguageDTO.builder()
                        .languageName(book.getLanguage().getLanguageName())
                        .id(book.getLanguage().getId())
                        .build())
                .author(AuthorDTO.builder()
                        .firstName(book.getAuthor().getFirstName())
                        .lastName(book.getAuthor().getLastName())
                        .id(book.getAuthor().getId())
                        .build())
                .category(CategoryDTO.builder()
                        .id(book.getCategory().getId())
                        .categoryName(book.getCategory().getCategoryName())
                        .build())
                .publisher(PublisherDTO.builder()
                        .id(book.getPublisher().getId())
                        .publisherName(book.getPublisher().getPublisherName())
                        .build())
                .build();
        if (book.getPrequel() != null) {
            bookDTO.setPrequel(BookBaseModelDTO.builder()
                    .id(book.getPrequel().getId())
                    .title(book.getPrequel().getTitle())
                    .build());
        }
        return bookDTO;
    }
}
