package cs.sample.employeedetails.exception;

import cs.sample.employeedetails.dto.MessageInfo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    private static final MessageInfo errorInfo = MessageInfo.getInstance();
    private static final String ERROR = "ERROR";

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<MessageInfo> onDataIntegrityViolationException(DataIntegrityViolationException e) {
        errorInfo.setMsgCode(ERROR);
        errorInfo.setMsg(e.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<MessageInfo> onNoSuchElementException(NoSuchElementException e) {
        errorInfo.setMsgCode(ERROR);
        errorInfo.setMsg(e.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<MessageInfo> onHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        errorInfo.setMsgCode(ERROR);
        errorInfo.setMsg(e.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomRuntimeException.class)
    private ResponseEntity<MessageInfo> onRecordCreationFailed(CustomRuntimeException e) {
        errorInfo.setMsgCode(ERROR);
        errorInfo.setMsg(e.getErrorMessage());
        return new ResponseEntity<>(errorInfo, e.getStatus());
    }

    @ExceptionHandler(CustomException.class)
    private ResponseEntity<MessageInfo> onRecordCreationFailed(CustomException e) {
        errorInfo.setMsgCode(ERROR);
        errorInfo.setMsg(e.getErrorMessage());
        return new ResponseEntity<>(errorInfo, e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<MessageInfo> onException(Exception e) {
        e.printStackTrace();
        errorInfo.setMsgCode(ERROR);
        errorInfo.setMsg(e.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

}
