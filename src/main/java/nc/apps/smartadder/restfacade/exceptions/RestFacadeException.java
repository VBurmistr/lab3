package nc.apps.smartadder.restfacade.exceptions;

public class RestFacadeException extends Exception {
    public RestFacadeException(String message) {
        super(message);
    }

    public RestFacadeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestFacadeException(Throwable cause) {
        super(cause);
    }
}
