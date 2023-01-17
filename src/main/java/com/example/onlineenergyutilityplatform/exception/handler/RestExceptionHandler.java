package com.example.onlineenergyutilityplatform.exception.handler;

import com.example.onlineenergyutilityplatform.exception.FailedLogInException;
import com.example.onlineenergyutilityplatform.exception.NickNameException;
import com.example.onlineenergyutilityplatform.exception.UpdateUserInformationException;
import com.example.onlineenergyutilityplatform.security.TokenEvaluatedException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This Exception Handler will take care of the exceptions that appear in the controller layer.
 * The @ControllerAdvice annotation allows us to create a global error handling component, so this
 * class will be enough for all the controller classes. If another exception will be thrown in the
 * code due to some future functionalities, we just need to add another method here to handle that
 * exception. The ResponseEntityExceptionHandler has a set of methods that can be override for some
 * specific exceptions (like our first method handleMethodArgumentNotValid). If an exception doesn't
 * belong to that set, we create a method to handle it and put the annotation @ExceptionHandler
 * specifying our exception (even if it is a custom exception or an imported one). For more details,
 * see the class {@link ResponseEntityExceptionHandler}
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * The exception MethodArgumentNotValid is thrown when an argument annotated with @Valid failed
     * validation
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = getErrors(ex);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Method Argument Not Valid", errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = getErrors(ex);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Constraint Violation Exception", errors);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Entity Not Found Exception", ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     * This method handles our custom exception that is specified inside @ExceptionHandler annotation
     */
    @ExceptionHandler({NickNameException.class})
    public ResponseEntity<ApiError> handleNickName(NickNameException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, "NickName Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     * This method handles our custom exception that is specified inside @ExceptionHandler annotation
     */
    @ExceptionHandler({UpdateUserInformationException.class})
    public ResponseEntity<ApiError> handleUpdateUserInformation(UpdateUserInformationException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Update User Information Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     * This method handles our custom exception that is specified inside @ExceptionHandler annotation
     */
    @ExceptionHandler({FailedLogInException.class})
    public ResponseEntity<ApiError> handleFailedLogIn(FailedLogInException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Failed Log In Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({TokenEvaluatedException.class})
    public ResponseEntity<ApiError> handleTokenEvaluated(
            TokenEvaluatedException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Token Evaluated Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ApiError> handleNullPointer(NullPointerException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Null Pointer Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity<ApiError> handleIO(IOException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "IOException", ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Illegal Argument Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ApiError> handleBadCredentials(BadCredentialsException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Bad Credentials Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ServletException.class})
    public ResponseEntity<ApiError> handleServlet(ServletException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Servlet Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ExceptionHandler({IllegalStateException.class})
    public ResponseEntity<ApiError> handleIllegalState(IllegalStateException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Illegal State Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ApiError> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Data Integrity Violation Exception", ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     * If the exception that just occurred doesn't belong to the ones already mentioned, this method
     * will handle it.
     *
     * @param ex is the thrown exception
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiError> handleAll(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Unspecific Error Occurred");
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    private List<String> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

    private List<String> getErrors(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }
}
