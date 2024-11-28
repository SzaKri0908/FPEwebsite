package hu.hardcore.FPEweblap.model.dto;

import hu.hardcore.FPEweblap.model.Events;
import hu.hardcore.FPEweblap.model.NewsPage;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EventsDTO {

    private Long id;

    private String title;

    private String text;

    private String imageUrl;

    private Timestamp eventDate;

    private String facebookEventLink;

    public EventsDTO(Events event){
        this.text = event.getText();
        this.title = event.getTitle();
        this.imageUrl = event.getImageUrl();
        this.eventDate = event.getEventDate();
        this.facebookEventLink = event.getFacebookEventLink();
        this.id = event.getId();
    }

}
