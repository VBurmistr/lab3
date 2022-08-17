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
@Builder
@Table(name = "lab3_publisher_table",
        uniqueConstraints = {
                @UniqueConstraint(name = "lab3_publisher_table_publisher_name",
                        columnNames = "publisherName")
        })
@Check(constraints = "publisher_name <> ''")
public class Publisher {
    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name = "my_seq", sequenceName = "lab3_book_table_id_seq", allocationSize = 1)
    private Integer id;
    @JsonIgnore
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private List<Book> books;
    private String publisherName;
}
