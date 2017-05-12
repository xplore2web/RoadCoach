package com.ajit.exception;

/**
 *
 * @author ajit
 */
public class ProcessFailed extends RuntimeException {

    private static final long serialVersionUID = 7032436818875287097L;

    /**
     * @param message
     */
    public ProcessFailed(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ProcessFailed(String message, Throwable cause) {
        super(message, cause);
    }

}
