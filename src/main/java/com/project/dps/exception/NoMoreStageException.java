package com.project.dps.exception;

public class NoMoreStageException extends RuntimeException {
    public NoMoreStageException() {
    }

    public NoMoreStageException(String message) {
        super(message);
    }

    public NoMoreStageException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoreStageException(Throwable cause) {
        super(cause);
    }
}
