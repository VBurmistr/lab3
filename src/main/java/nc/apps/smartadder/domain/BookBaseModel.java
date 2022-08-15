package nc.apps.smartadder.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
public class BookBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="lab3_book_table_id_seq",allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String title;
}
