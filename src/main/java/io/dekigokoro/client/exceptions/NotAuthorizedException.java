package io.dekigokoro.client.exceptions;

/**
 * @author amy
 * @since 7/9/19.
 */
public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException() {
    }
    
    public NotAuthorizedException(final String message) {
        super(message);
    }
    
    public NotAuthorizedException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public NotAuthorizedException(final Throwable cause) {
        super(cause);
    }
    
    protected NotAuthorizedException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
