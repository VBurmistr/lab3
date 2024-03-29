package nc.apps.smartadder.dto.bookdtos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookDTO {
    private Integer id;
    private String title;
    private AuthorDTO author;
    private CategoryDTO category;
    private PublisherDTO publisher;
    private LanguageDTO language;
    private BookBaseModelDTO prequel;
}
