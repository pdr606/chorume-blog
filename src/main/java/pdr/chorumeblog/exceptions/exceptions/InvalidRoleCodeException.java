package pdr.chorumeblog.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRoleCodeException extends RuntimeException {

    public InvalidRoleCodeException(int code){
        super("Invalid Role code " + code);
    }
}
