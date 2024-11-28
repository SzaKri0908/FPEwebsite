package hu.hardcore.FPEweblap.util;

import hu.hardcore.FPEweblap.exceptionhandling.exception.authenticationexception.IncorrectPasswordException;
import hu.hardcore.FPEweblap.exceptionhandling.exception.authenticationexception.UserDoesNotExistsException;
import hu.hardcore.FPEweblap.model.User;
import hu.hardcore.FPEweblap.model.dto.CredentialsDTO;
import hu.hardcore.FPEweblap.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class SecurityUtil {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public static Authentication getAuthentication() {
        if (SecurityContextHolder.getContext() != null) {
            return SecurityContextHolder.getContext().getAuthentication();
        }
        return null;
    }

    public static String getLoggedInUserName() {
        if (getAuthentication() != null) {
            return getAuthentication().getName();
        }
        return null;
    }

    public User findByCredentialsDTO(CredentialsDTO credentialsDTO) throws UserDoesNotExistsException, IncorrectPasswordException {
            StringBuilder exceptionBuilder = null;
            User user = userService.findUserByUsername(credentialsDTO.getUsername());
            if(user == null){
                exceptionBuilder = new StringBuilder("Nem létezik ilyen nevű felhasználó: ");
                exceptionBuilder.append(credentialsDTO.getUsername());
                throw new UserDoesNotExistsException(exceptionBuilder.toString());
            }
            if(passwordEncoder.matches(credentialsDTO.getPassword(), user.getPassword())){
                return user;
            }else{
                exceptionBuilder = new StringBuilder("Hibás jelszó megadva a felhasználóhoz: ");
                exceptionBuilder.append(user.getUsername());
                throw new IncorrectPasswordException(exceptionBuilder.toString());
            }

    }

}
