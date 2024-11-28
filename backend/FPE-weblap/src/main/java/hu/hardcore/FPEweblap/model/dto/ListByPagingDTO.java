package hu.hardcore.FPEweblap.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListByPagingDTO {
    private Long total;

    private List rows;

    public ListByPagingDTO(Long total, List rows)
    {
        this.total = total;
        this.rows = rows;
    }
}
