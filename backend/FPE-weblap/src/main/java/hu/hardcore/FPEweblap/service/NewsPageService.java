package hu.hardcore.FPEweblap.service;

import hu.hardcore.FPEweblap.model.NewsPage;
import hu.hardcore.FPEweblap.model.dto.ListByPagingDTO;
import hu.hardcore.FPEweblap.model.dto.NewsPageIUDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface NewsPageService extends BaseServiceInterface<NewsPage> {

    List<NewsPage> findByTitle(String title);

    ListByPagingDTO listByPaging(Integer pageIndex, Integer pageSize, String title);

    void softDelete(Long id);

    void softUndelete(Long id);

    void saveWithImg(NewsPageIUDTO newsPageIUDTO);
}
