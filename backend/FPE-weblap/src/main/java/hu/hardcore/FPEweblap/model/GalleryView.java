package hu.hardcore.FPEweblap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.hardcore.FPEweblap.model.pojo.GalleryArrayElement;
import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "gallery_view")
@Getter
@Setter
@Entity
public class GalleryView extends BaseEntity{

    private String title;

    @JsonIgnore
    private String images;

    @Transient
    private List<GalleryArrayElement> child;

    @PostLoad
    private void initChild() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        child = mapper.readValue(images, new TypeReference<List<GalleryArrayElement>>() {});
    }
}
