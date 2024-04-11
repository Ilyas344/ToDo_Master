package t1academy.todo_master.exception;

public class ResponseException extends IllegalArgumentException {
    public ResponseException(String message) {
        super(message);
    }
}
