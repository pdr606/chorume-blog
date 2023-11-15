package pdr.chorumeblog.exceptions.core;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pdr.chorumeblog.exceptions.exceptions.DuplicateException;
import pdr.chorumeblog.exceptions.exceptions.NotFoundException;
import pdr.chorumeblog.exceptions.exceptions.UnauthorizedException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public StandardError notFoundException(NotFoundException ex, HttpServletRequest request){
        String error = "Not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new StandardError(Instant.now(), status.value(), Collections.singletonList(error), ex.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateException.class)
    public StandardError duplicateException(DuplicateException ex, HttpServletRequest request){
        String error = "Duplicate entity";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new StandardError(Instant.now(), status.value(), Collections.singletonList(error), ex.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnauthorizedException.class)
    public StandardError duplicateException(UnauthorizedException ex, HttpServletRequest request){
        String error = "Request Unauthorized";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new StandardError(Instant.now(), status.value(), Collections.singletonList(error), ex.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public StandardError handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<String> messages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(err -> messages.add(err.getDefaultMessage()));
        String message = "Invalid data";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new StandardError(Instant.now(), status.value(), messages, message, request.getRequestURI());
    }
}
