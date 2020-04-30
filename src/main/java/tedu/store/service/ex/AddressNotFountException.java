package tedu.store.service.ex;

public class AddressNotFountException extends ServiceException{
    public AddressNotFountException() {
    }

    public AddressNotFountException(String message) {
        super(message);
    }

    public AddressNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotFountException(Throwable cause) {
        super(cause);
    }

    public AddressNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
