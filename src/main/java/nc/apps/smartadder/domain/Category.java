package nc.apps.smartadder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lab3_category_table",
        uniqueConstraints = {
                @UniqueConstraint(name = "lab3_category_table_category_name_unique",
                        columnNames = "categoryName")
        })
@Check(constraints = "category_name <> ''")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Book> books;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
