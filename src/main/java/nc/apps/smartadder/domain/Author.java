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
@Builder
@Table(name = "lab3_author_table",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"firstName", "lastName"},
                name = "lab3_author_table_first_name_last_name_key")})
@Check(constraints = "first_name <> '' AND last_name <> ''")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;

    @Column
    private String firstName;

    private String lastName;
}
