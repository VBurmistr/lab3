package nc.apps.smartadder.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ResponseObject<T> {
    private boolean success;
    private T responseBody;
}
