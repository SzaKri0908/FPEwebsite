package hu.hardcore.FPEweblap.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {

    private String to;
    private String subject;
    private String from;
    private String text;

}
