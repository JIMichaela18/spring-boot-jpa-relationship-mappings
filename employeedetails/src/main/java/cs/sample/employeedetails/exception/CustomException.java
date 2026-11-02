package cs.sample.employeedetails.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.UnknownHttpStatusCodeException;

public class CustomException extends Exception {

    private HttpStatus status;
    private String errorMessage;

    public CustomException(String errorMessage, HttpStatus status){
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