package com.stone.commons.core.assertion;

public class AssertionException extends RuntimeException {

    /**
     * 
     */
    public AssertionException() {

        super();
    }

    /**
     * @param message
     * @param cause
     */
    public AssertionException(final String message, final Throwable cause) {

        super(message, cause);
    }

    /**
     * @param message
     */
    public AssertionException(final String message) {

        super(message);
    }

    /**
     * @param cause
     */
    public AssertionException(final Throwable cause) {

        super(cause);
    }

}
