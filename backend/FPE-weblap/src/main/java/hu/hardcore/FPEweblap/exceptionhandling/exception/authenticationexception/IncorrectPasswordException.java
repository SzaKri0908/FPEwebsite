package hu.hardcore.FPEweblap.exceptionhandling.exception.authenticationexception;

import javax.naming.AuthenticationException;

public class IncorrectPasswordException extends AuthenticationException {

    public IncorrectPasswordException(String message){
        super(message);
    }

}
