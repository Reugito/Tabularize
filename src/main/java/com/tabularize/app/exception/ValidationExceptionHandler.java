package com.tabularize.app.exception;


import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tabularize.app.wrapper.ResponseWrapper;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper<String> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError fieldError = result.getFieldError();

        if (fieldError != null) {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            String responseMessage = "Validation Error: Field '" + fieldName + "' - " + errorMessage;
            return new ResponseWrapper<String>(
                HttpStatus.BAD_REQUEST,
                responseMessage,
                responseMessage
            );
        } else {
            return new ResponseWrapper<String>(
                HttpStatus.BAD_REQUEST,
                "Validation Error: Some fields are invalid.",
                "Validation Error: Some fields are invalid."
            );
        }
	}
}
