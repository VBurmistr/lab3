package nc.apps.smartadder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {
    @JsonProperty("volumeInfo")
    VolumeInfo volumeInfo;
}
