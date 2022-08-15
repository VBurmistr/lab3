package nc.apps.smartadder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lab3_publisher_table")
@Check(constraints = "ALTER TABLE lab3_publisher_table ADD CONSTRAINT lab3_publisher_table_check_publisher_name_not_empty CHECK (publisher_name <> '')")
public class Publisher {
    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="lab3_book_table_id_seq",allocationSize = 1)
    private Integer id;
    @JsonIgnore
    @OneToMany(mappedBy = "publisher",fetch =FetchType.LAZY)
    private List<Book> books;
    @Column(unique = true)
    private String publisherName;
}
