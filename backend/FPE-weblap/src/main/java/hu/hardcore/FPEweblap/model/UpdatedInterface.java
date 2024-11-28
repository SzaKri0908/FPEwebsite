package hu.hardcore.FPEweblap.model;

import java.sql.Timestamp;

public interface UpdatedInterface {

    String getUpdatedBy();
    Timestamp getUpdatedOn();

    void setUpdatedBy(String updatedBy);
    void setUpdatedOn(Timestamp updatedOn);

}
