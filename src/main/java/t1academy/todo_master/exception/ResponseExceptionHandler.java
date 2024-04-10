package t1academy.todo_master.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(CopyClassException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // для генерации документации
    public ResponseEntity<String> handleCopyClassException(CopyClassException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
    @ExceptionHandler(IncorrectClassException.class)
     @ResponseStatus(HttpStatus.BAD_REQUEST) // для генерации документации
    public ResponseEntity<String> handleIncorrectClassException(IncorrectClassException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // для генерации документации
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
