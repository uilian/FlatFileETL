package com.sample.etl.exception;

/**
 * Created by uilian.souza on 13/02/2016.
 */
public class WrongLineFormatException extends Exception {

    private static String errorMessage = "\nUnexpected value found.";

    public WrongLineFormatException() {
        super(errorMessage);
    }

    public WrongLineFormatException(String message) {
        super(message + errorMessage);
    }
}