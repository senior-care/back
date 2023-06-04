package rnqhstlr.senior.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseErrorResponse InvalidPasswordException(InvalidPasswordException e){
        return new BaseErrorResponse(e.getMessage());
    }

    @ExceptionHandler(NotExistSocialWorker.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseErrorResponse NotExistSocialWorker(NotExistSocialWorker e){
        return new BaseErrorResponse(e.getMessage());
    }


}
