package hu.hardcore.FPEweblap.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsPageIUDTO {

    private Long id;

    private String facebookLink;

    //private String image;

    private String title;

    private Boolean isMainNews = false;

    private String text;

    private ImageData image;

    @Getter
    @Setter
    public static class ImageData{
        private String file;
        private String name;
        private String type;
    }

}
