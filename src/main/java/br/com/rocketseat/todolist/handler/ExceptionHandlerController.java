package br.com.rocketseat.todolist.handler;

import org.springframework.http.converter.HttpMessageNotReadbleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(HttpMessageNotReadbleException)
    public ResponseEntity<String> handleHttpMessageNotReadbleException(HttpMessageNotReadbleException ex){
        return ResponseEntity.status(HttpStatus.BAD_RESQUEST).body(ex.getMostSpecificCause().getMessage());
    }
}