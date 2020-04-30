package tedu.store.controller.ex;

public class FileUploadStateException extends FileUploadException{
    public FileUploadStateException() {
    }

    public FileUploadStateException(String message) {
        super(message);
    }

    public FileUploadStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadStateException(Throwable cause) {
        super(cause);
    }

    public FileUploadStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
