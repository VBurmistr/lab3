package nc.apps.smartadder.dto.bookdtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Integer id;
    private String firstName;
    private String lastName;
}
