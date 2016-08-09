package lt.tieto.msi2016.utils.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lt.tieto.msi2016.utils.exception.DataNotFoundException;
import lt.tieto.msi2016.utils.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(DataNotFoundException.class)
    private ResponseEntity<Void> handleResourceNotFoundException(DataNotFoundException e) {
        LOG.debug("Data not found", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<Void> handleResourceNotFoundException(AccessDeniedException e) {
        LOG.debug("Access Denied", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Void> exception(Exception e) {
        LOG.error("Internal error", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<FieldValidationError> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return result.getFieldErrors()
                     .stream()
                     .map(error -> new FieldValidationError(error.getField(), error.getDefaultMessage()))
                     .collect(Collectors.toList());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private List<FieldValidationError> handleResourceNotFoundException(ValidationException e) {
        LOG.debug("Validation error", e);
        ArrayList<FieldValidationError> errors = new ArrayList<>();
        errors.add(new FieldValidationError(e.getName(), e.getMessage()));
        return errors;
    }

    public static class FieldValidationError {
        private String name;
        private String message;

        FieldValidationError(String name, String message) {
            this.name = name;
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }
    }

}
