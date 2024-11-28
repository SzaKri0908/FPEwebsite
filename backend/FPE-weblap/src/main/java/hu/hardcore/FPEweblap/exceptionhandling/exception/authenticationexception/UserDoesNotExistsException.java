package hu.hardcore.FPEweblap.exceptionhandling.exception.authenticationexception;

import javax.naming.AuthenticationException;

public class UserDoesNotExistsException extends AuthenticationException {

    public UserDoesNotExistsException(String message){
        super(message);
    }

}
