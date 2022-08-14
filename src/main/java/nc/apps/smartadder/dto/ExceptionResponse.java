package nc.apps.smartadder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private final int code;
    private final long timeMilis;
    private final String msg;
}
