package hu.hardcore.FPEweblap.model;

import java.sql.Timestamp;

public interface CreatedInterface {

    String getCreatedBy();
    Timestamp getCreatedOn();
    void setCreatedBy(String createdBy);
    void setCreatedOn(Timestamp createdOn);

}
