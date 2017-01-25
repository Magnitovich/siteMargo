package margo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FOUND, reason="userName not found")
public class UserNotFoundException extends RuntimeException  {

    @ExceptionHandler(BadCredentialsException.class)
    public String userNameNotFound(){
        return "userName not found";
    }
}
