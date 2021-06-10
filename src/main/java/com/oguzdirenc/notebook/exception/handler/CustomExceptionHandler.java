package com.oguzdirenc.notebook.exception.handler;

import com.oguzdirenc.notebook.exception.IdNullException;
import com.oguzdirenc.notebook.exception.NotFoundException;
import com.oguzdirenc.notebook.exception.UserValidationException;
import com.oguzdirenc.notebook.exception.UsernameAlreadyExistsException;
import com.oguzdirenc.notebook.response.IdNullExceptionResponse;
import com.oguzdirenc.notebook.response.NotFoundExceptionResponse;
import com.oguzdirenc.notebook.response.UserValidationExceptionResponse;
import com.oguzdirenc.notebook.response.UsernameAlreadyExistsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex){
        NotFoundExceptionResponse response =new NotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUserValidationException(UserValidationException ex){
        UserValidationExceptionResponse response =new UserValidationExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex,
                                                                             WebRequest request){
        UsernameAlreadyExistsResponse response = new UsernameAlreadyExistsResponse(ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleIdNullException(IdNullException ex){
        IdNullExceptionResponse response = new IdNullExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){

        var errors = new HashMap<String,String>();

        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex){

        var errors = new ArrayList<>();

        ex.getConstraintViolations()
                .forEach(violation -> errors.add(violation.getMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
