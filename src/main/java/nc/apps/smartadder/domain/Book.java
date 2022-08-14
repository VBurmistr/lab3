package nc.apps.smartadder.domain;

import lombok.*;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class Book extends BookBaseModel {
    private Author author;
    private Category category;
    private Language language;
    private Publisher publisher;
    private BookBaseModel prequel;

    public Book(Long id, String title, Author author, Category category, Language language, Publisher publisher, BookBaseModel prequel) {
        super(id, title);
        this.author = author;
        this.category = category;
        this.language = language;
        this.publisher = publisher;
        this.prequel = prequel;
    }

}
