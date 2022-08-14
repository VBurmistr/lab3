package nc.apps.smartadder.dto;

import lombok.Data;

@Data
public class Response {
    private long totalItems;
    private Item[] items;
}
