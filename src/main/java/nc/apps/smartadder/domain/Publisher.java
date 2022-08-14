package nc.apps.smartadder.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Publisher {
    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }

    private Long id;
    private String publisherName;
}
