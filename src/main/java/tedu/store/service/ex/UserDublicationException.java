package tedu.store.service.ex;

public class UserDublicationException extends ServiceException{
    public UserDublicationException() {
    }

    public UserDublicationException(String message) {
        super(message);
    }

    public UserDublicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDublicationException(Throwable cause) {
        super(cause);
    }

    public UserDublicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
