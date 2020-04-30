package tedu.store.service.ex;

public class WrongPasswordException extends ServiceException {
    public WrongPasswordException() {
    }

    public WrongPasswordException(String message) {
        super(message);
    }

    public WrongPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongPasswordException(Throwable cause) {
        super(cause);
    }

    public WrongPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
