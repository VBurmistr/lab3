package nc.apps.smartadder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lab3_category_table")
@Check(constraints = "ALTER TABLE lab3_category_table ADD CONSTRAINT lab3_category_table_check_category_name_not_empty CHECK (category_name <> '')")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="lab3_book_table_id_seq",allocationSize = 1)
    private Integer id;
    @Column(unique = true)
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category",fetch =FetchType.LAZY)
    private List<Book> books;
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
