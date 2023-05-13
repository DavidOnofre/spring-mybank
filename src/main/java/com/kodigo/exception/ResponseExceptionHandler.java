package com.kodigo.exception;

import com.kodigo.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    Logger LOGGER = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        processLog(ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        processLog(ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String[] causes = getCauses(ex);
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), getMessage(causes), getDetail(causes));
        processLog(ex.getCause().getLocalizedMessage(), ex.getCause().getCause().getMessage());
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.CONFLICT);
    }

    private String[] getCauses(DataIntegrityViolationException ex) {
        String specificCause = ex.getMostSpecificCause().getMessage();
        return specificCause.split(Constant.BACK_SLASH_DOUBLE_QUOTES);
    }

    private String getMessage(String[] causes) {
        // return causes[0]; //spanish value
        return causes[2].replace(Constant.BACK_SLASH_N, Constant.SPACE);
    }

    private String getDetail(String[] causes) {
        String[] details = causes[1].split(Constant.ON_PUBLIC);
        return details[1].replace(Constant.NULLS_FIRST, Constant.SPACE);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(e -> {
                    return e.getDefaultMessage().toString().concat(Constant.COMMA);
                })
                .collect(Collectors.joining());

        String[] duplicatesMessage = message.split(Constant.COMMA);
        String aloneMessage = Arrays.stream(duplicatesMessage)
                .distinct()
                .map(e -> {
                    return e.toString().concat(Constant.COMMA);
                })
                .collect(Collectors.joining());

        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), aloneMessage, request.getDescription(false));

        processLog(aloneMessage, ex.getMessage());
        return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
    }

    private void processLog(String message, String details) {
        LOGGER.warn(message);
        LOGGER.error(details);
    }

}
