package com.jrinehuls.mimeemail;

import com.jrinehuls.mimeemail.exception.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(MultipartException.class)
    protected ResponseEntity<Object> handleMultipartException(MultipartException ex) {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(messages), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ArrayList<String> messages = new ArrayList<>();
        for (ObjectError error: ex.getBindingResult().getAllErrors()) {
            messages.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorResponse(messages), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(ex.getParameterName());
        return new ResponseEntity<>(new ErrorResponse(messages), HttpStatus.BAD_REQUEST);
    }

}
