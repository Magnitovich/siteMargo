package margo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FOUND, reason="You not authorized, pls. see email")
public class UserNotAuthorizedException extends RuntimeException {
    @ExceptionHandler(UsernameNotFoundException.class)
    public String UserNotAuthorizedException(){
        return "You not authorized, pls. see email";
    }
}
