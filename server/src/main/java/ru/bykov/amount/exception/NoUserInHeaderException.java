package ru.bykov.amount.exception;

public class NoUserInHeaderException extends RuntimeException {
    public NoUserInHeaderException(String message) {
        super(message);
    }
}
