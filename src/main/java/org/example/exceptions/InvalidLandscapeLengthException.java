package org.example.exceptions;

public class InvalidLandscapeLengthException extends RuntimeException {

    public InvalidLandscapeLengthException(int length) {
        super(String.format("Invalid landscape length {%d}. The length must be greater than 3 and less than 32000!", length));
    }
}
