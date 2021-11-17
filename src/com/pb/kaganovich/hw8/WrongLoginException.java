package com.pb.kaganovich.hw8;

/**
 * Исключение неправильный логин
 */
public class WrongLoginException extends Exception {
    public WrongLoginException() {
        super("Неправильный логин");
    }

    public WrongLoginException(String msg) {
        super(msg);
    }
}
