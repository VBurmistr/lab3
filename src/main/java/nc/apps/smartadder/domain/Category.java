package nc.apps.smartadder.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {
    private Long id;
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
