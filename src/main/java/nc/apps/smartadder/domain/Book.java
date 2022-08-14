package nc.apps.smartadder.domain;

import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "lab3_book_table", uniqueConstraints = { @UniqueConstraint(columnNames = { "title", "author_id" }) })
@Check(constraints = "ALTER TABLE lab3_book_table ADD CONSTRAINT lab3_book_table_check_prequel_not_same_id CHECK (id <> prequel_id)," +
        "ALTER TABLE lab3_book_table ADD CONSTRAINT lab3_book_table_check_title_not_empty CHECK (title <> '')")
@Entity
public class Book extends BookBaseModel {
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id",nullable = false)
    private Author author;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id",nullable = false)
    private Language language;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id",nullable = false)
    private Publisher publisher;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prequel_id")
    private Book prequel;

    public Book(Integer id, String title, Author author, Category category, Language language, Publisher publisher, Book prequel) {
        super(id, title);
        this.author = author;
        this.category = category;
        this.language = language;
        this.publisher = publisher;
        this.prequel = prequel;
    }

}
