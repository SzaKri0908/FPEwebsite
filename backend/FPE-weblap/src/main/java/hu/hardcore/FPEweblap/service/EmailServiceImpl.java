package hu.hardcore.FPEweblap.service;

import hu.hardcore.FPEweblap.model.dto.EmailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(EmailDTO emailDTO) {
        log.info("Sending email...");
        SimpleMailMessage message = new SimpleMailMessage();
        String subject = emailDTO.getSubject() != null ? emailDTO.getSubject() : "Nincs tárgy";
        message.setTo("kukacka5501@gmail.com");
        message.setSubject(subject);
        message.setText(emailDTO.getText());

        javaMailSender.send(message);
        log.info("Email sent!");
    }
}
