package nc.apps.smartadder.dto;

import lombok.Data;

@Data
public class VolumeInfo {
    String title;
    String[] authors;
    String[] categories;
    String language;
    String publisher;
}
