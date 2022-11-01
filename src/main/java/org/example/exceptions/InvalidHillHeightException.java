package org.example.exceptions;

public class InvalidHillHeightException extends RuntimeException {

    public InvalidHillHeightException(int hillHeight) {
        super(String.format("Invalid hill height {%d}. Hill height should be between 0 and 32000!", hillHeight));
    }
}
