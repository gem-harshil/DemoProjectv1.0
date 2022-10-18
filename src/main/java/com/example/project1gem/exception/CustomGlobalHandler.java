package com.example.project1gem.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomGlobalHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetail errorDetail = new CustomErrorDetail(new Date(), "Method Argument is/are not Valid", ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles HttpRequestMethodNotSupportedException.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetail errorDetail = new CustomErrorDetail(new Date(), "Please Check Http Request Method", ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Handles MissingPathVariableException.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetail errorDetail = new CustomErrorDetail(new Date(), "Please provide Path Variable properly", ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles MissingServletRequestParameterException
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetail errorDetail = new CustomErrorDetail(new Date(), "Please provide request parameter properly", ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles NoHandlerFoundException.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetail errorDetail = new CustomErrorDetail(new Date(), ex.getMessage(), "No Handler Found");
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exception when no data found.
     *
     * @param ex      exception
     * @param request webRequest
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public final ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
        CustomErrorDetail errorDetail = new CustomErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exception for invalid id.
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(IdNotFoundException.class)
    public final ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException ex, WebRequest request) {
        CustomErrorDetail errorDetail = new CustomErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    /**
     * It Handles all unwanted exception.
     *
     * @param ex      exception
     * @param request webRequest
     * @return ResponseEntity<?>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        CustomErrorDetail errorDetails = new CustomErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
