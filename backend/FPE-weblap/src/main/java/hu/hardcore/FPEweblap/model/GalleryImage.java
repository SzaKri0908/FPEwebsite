package hu.hardcore.FPEweblap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;

import java.sql.Timestamp;

@Table(name = "gallery_images")
@Entity
@Getter
@Setter
public class GalleryImage extends BaseEntity implements CreatedInterface, UpdatedInterface {

    @Column(name = "facebook_link")
    private String facebookLink;

    @Column(name = "imgurl")
    private String imgUrl;

    private Integer deleted = 0;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "updated_by")
    private String updatedBy;

}
