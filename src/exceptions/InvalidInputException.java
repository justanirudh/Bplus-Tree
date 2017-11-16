package exceptions;

/**
 * Created by paanir on 11/16/17.
 */
public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String message) {
        super(message);
    }
}
