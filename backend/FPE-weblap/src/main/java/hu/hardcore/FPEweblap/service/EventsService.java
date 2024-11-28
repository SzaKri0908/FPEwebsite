package hu.hardcore.FPEweblap.service;

import hu.hardcore.FPEweblap.model.Events;
import hu.hardcore.FPEweblap.model.dto.EventsIUDTO;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface EventsService extends BaseServiceInterface<Events> {

    List<Events> listByPaging(Integer pageIndex, Integer pageSize, Date date, Map<String, String> filterParams);

    Long eventListCount(Date date, Map<String, String> filterParams);

    void softDelete(Long id);

    void softUndelete(Long id);

    void saveWithImg(EventsIUDTO request);
}
