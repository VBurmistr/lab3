package nc.apps.smartadder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lab3_author_table", uniqueConstraints = { @UniqueConstraint(columnNames = { "firstName", "lastName" }) })
@Check(constraints = "ALTER TABLE lab3_author_table ADD CONSTRAINT lab3_author_table_check_first_name_not_empty CHECK (first_name <> '')," +
        "ALTER TABLE lab3_author_table ADD CONSTRAINT lab3_author_table_check_last_name_not_empty CHECK (last_name <> '')")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="lab3_author_table_id_seq",allocationSize = 1)
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy = "author",fetch =FetchType.LAZY)
    private List<Book> books;

    private String firstName;

    private String lastName;
}
