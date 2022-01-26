package com.manifoldfinance.quarkus.logging.logfmt.runtime;

public class LogfmtException extends RuntimeException {
    public LogfmtException(String message) {
        super(message);
    }

    public LogfmtException(String message, Throwable cause) {
        super(message, cause);
    }
}
