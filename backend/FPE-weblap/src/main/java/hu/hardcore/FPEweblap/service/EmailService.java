package hu.hardcore.FPEweblap.service;

import hu.hardcore.FPEweblap.model.dto.EmailDTO;

public interface EmailService {

    void sendEmail(EmailDTO emailDTO);

}
