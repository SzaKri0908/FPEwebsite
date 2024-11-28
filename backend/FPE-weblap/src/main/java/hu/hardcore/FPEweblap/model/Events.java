package hu.hardcore.FPEweblap.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Table(name = "events")
@Entity
@Getter
@Setter
public class Events extends BaseEntity implements CreatedInterface, UpdatedInterface{

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "event_date")
    private Timestamp eventDate;

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

    @Column(name = "facebook_event_link")
    private String facebookEventLink;
}
