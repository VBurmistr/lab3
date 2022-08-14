package nc.apps.smartadder.domain;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Language {
    private Long id;

    public Language(String languageName) {
        this.languageName = languageName;
    }

    private String languageName;
}
