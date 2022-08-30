package nc.apps.smartadder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObject {
    private int statusCode;
    private String msg;
    private long timestamp;
}
