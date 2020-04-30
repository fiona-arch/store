package tedu.store.service.ex;

public class RegException extends ServiceException{
    public RegException() {
    }

    public RegException(String message) {
        super(message);
    }

    public RegException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegException(Throwable cause) {
        super(cause);
    }

    public RegException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
