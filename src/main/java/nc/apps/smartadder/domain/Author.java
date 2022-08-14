package nc.apps.smartadder.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Author {
    private Long id;
    public Author(Long id) {
        this.id = id;
    }

    private String firstName;
    private String lastName;
}
