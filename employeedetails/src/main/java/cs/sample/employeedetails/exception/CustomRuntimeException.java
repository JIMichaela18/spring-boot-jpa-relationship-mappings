package cs.sample.employeedetails.exception;

import org.springframework.http.HttpStatus;

public class CustomRuntimeException extends RuntimeException {

    private HttpStatus status;
    private String errorMessage;

    public CustomRuntimeException(String errorMessage, HttpStatus status){
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
