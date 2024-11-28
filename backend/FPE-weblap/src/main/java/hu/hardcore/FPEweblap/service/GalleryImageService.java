package hu.hardcore.FPEweblap.service;

import hu.hardcore.FPEweblap.model.GalleryImage;
import hu.hardcore.FPEweblap.model.GalleryView;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface GalleryImageService extends BaseServiceInterface<GalleryImage> {
    void saveWithImg(MultipartHttpServletRequest request) throws Exception;

    void softDelete(Long id);

    List<GalleryView> listByPaging(int pageCount, int pageSize);
}
