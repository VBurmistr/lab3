package nc.apps.smartadder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@Table(name = "lab3_language_table")
@Check(constraints = "ALTER TABLE lab3_language_table ADD CONSTRAINT lab3_language_table_check_language_not_empty CHECK (language <> '')")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="lab3_book_table_id_seq",allocationSize = 1)
    private Integer id;
    @JsonIgnore
    @OneToMany(mappedBy = "language",fetch =FetchType.LAZY)
    private List<Book> books;
    public Language(String languageName) {
        this.languageName = languageName;
    }
    @Column(name ="language",unique = true)
    private String languageName;
}
