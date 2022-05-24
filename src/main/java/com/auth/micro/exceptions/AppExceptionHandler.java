package com.auth.micro.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.auth.micro.dto.BusinessResourceExceptionDTO;
import com.auth.micro.responses.UserException;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = { UserException.class })
    public ResponseEntity<Object> handlerUserException(UserException userException, WebRequest request) {
        return new ResponseEntity<>(userException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessResourceException.class)
    public ResponseEntity<BusinessResourceExceptionDTO> businessResourceError(HttpServletRequest req, BusinessResourceException ex) {
        BusinessResourceExceptionDTO businessResourceExceptionDTO = new BusinessResourceExceptionDTO();
        businessResourceExceptionDTO.setTimestamp(ex.getTimestamp());
        businessResourceExceptionDTO.setStatus(ex.getStatus());
        businessResourceExceptionDTO.setErrorCode(ex.getErrorCode());
        businessResourceExceptionDTO.setErrorMessage(ex.getMessage());
        businessResourceExceptionDTO.setRequestURL(req.getRequestURL().toString());
        return new ResponseEntity<BusinessResourceExceptionDTO>(businessResourceExceptionDTO, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BusinessResourceExceptionDTO> unknowError(HttpServletRequest req, Exception ex) {
        BusinessResourceExceptionDTO response = new BusinessResourceExceptionDTO();
        response.setTimestamp(new Date());
        response.setErrorCode("Technical Error");
        response.setErrorMessage(ex.getMessage());
        response.setRequestURL(req.getRequestURL().toString());
        return new ResponseEntity<BusinessResourceExceptionDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> hadleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest req) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
