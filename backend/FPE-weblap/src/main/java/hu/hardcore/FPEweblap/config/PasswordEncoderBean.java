package hu.hardcore.FPEweblap.config;

import hu.hardcore.FPEweblap.util.ConstantStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoderBean {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(ConstantStore.PASSWORD_ENCODER_STRENGTH);
    }


}
