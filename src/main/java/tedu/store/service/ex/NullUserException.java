package tedu.store.service.ex;

public class NullUserException extends ServiceException{
    public NullUserException() {
    }

    public NullUserException(String message) {
        super(message);
    }

    public NullUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullUserException(Throwable cause) {
        super(cause);
    }

    public NullUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
