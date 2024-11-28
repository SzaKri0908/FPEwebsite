package hu.hardcore.FPEweblap.model.dto;

import hu.hardcore.FPEweblap.model.NewsPage;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsPageDTO {

    private Long id;

    private String title;

    private String text;

    private String imageUrl;

    private String facebookLink;

    public NewsPageDTO(NewsPage newsPage){
        this.text = newsPage.getText();
        this.title = newsPage.getTitle();
        this.imageUrl = newsPage.getImageUrl();
        this.facebookLink = newsPage.getFacebookLink();
        this.id = newsPage.getId();
    }


}
