package hu.hardcore.FPEweblap.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class EventsIUDTO {

    private Long id;
    private String eventDate;
    private String facebookEventLink;
    private EventImage image;
    private String text;
    private String title;

    @Getter
    @Setter
    public static class EventImage{

        private String file;
        private String name;
        private String type;


    }

}
