package com.rith.id.exception;

import com.rith.id.dto.ErrorDto;
import jakarta.persistence.LockTimeoutException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.PessimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
@RestController
public class ExceptionAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorDto> handleRecordNotFoundException(final RecordNotFoundException recordNotFoundException) {
        ErrorDto error = new ErrorDto();
        error.setCause(recordNotFoundException.getMessage());
        error.setAdvise(recordNotFoundException.getAdvicemsg());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {PersistenceException.class})
    public final ResponseEntity<ErrorDto> handlePessimisticLockException(final PersistenceException persistenceException){
        ErrorDto error = new ErrorDto();
        error.setCause(persistenceException.getMessage());
        error.setAdvise("Please wait for transaction to complete");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {LockTimeoutException.class})
    public final ResponseEntity<ErrorDto> handleLockTimeoutException(final LockTimeoutException persistenceException){
        ErrorDto error = new ErrorDto();
        error.setCause(persistenceException.getMessage());
        error.setAdvise("Please wait for transaction to complete");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PessimisticLockException.class})
    public final ResponseEntity<ErrorDto> handlePessimisticLockException(final PessimisticLockException persistenceException){
        ErrorDto error = new ErrorDto();
        error.setCause(persistenceException.getMessage());
        error.setAdvise("Please wait for transaction to complete");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
