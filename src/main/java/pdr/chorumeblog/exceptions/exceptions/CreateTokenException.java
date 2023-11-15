package pdr.chorumeblog.exceptions.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreateTokenException extends RuntimeException {

    public CreateTokenException(){
        super("Error creating token");
    }
}
