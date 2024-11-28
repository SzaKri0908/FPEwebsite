package hu.hardcore.FPEweblap.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Table(name = "news_page")
@Entity
@Getter
@Setter
public class NewsPage extends BaseEntity implements CreatedInterface, UpdatedInterface{

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "deleted")
    private Integer deleted = 0;

    @Column(name = "created_on", updatable = false)
    private Timestamp createdOn;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "facebook_link")
    private String facebookLink;

    @Column(name = "is_main_news")
    private Boolean isMainNews;




}
