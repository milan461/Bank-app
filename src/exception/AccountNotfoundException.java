package exception;

public class AccountNotfoundException extends RuntimeException {
    public AccountNotfoundException(String message) {
        super(message);
    }

}