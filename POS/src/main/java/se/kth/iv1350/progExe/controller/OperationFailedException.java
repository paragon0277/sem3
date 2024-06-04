package se.kth.iv1350.progExe.controller;

/**
 * This class represents an exception that is thrown when an operation fails in the controller.
 */
public class OperationFailedException extends Exception {

    /**
     * Creates a new instance of the exception with a specified message and cause.
     *
     * @param msg   The message describing the reason for the exception.
     * @param cause The exception that caused this exception to be thrown.
     */
    public OperationFailedException(String msg, Exception cause) {
        super(msg, cause);
    }
}
