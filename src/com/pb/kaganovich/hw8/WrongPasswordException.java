package com.pb.kaganovich.hw8;

/**
 * Исключение неправильный пароль
 */
public class WrongPasswordException extends Exception {
    public WrongPasswordException() {
        super("Неправильный пароль");
    }

    public WrongPasswordException(String msg) {
        super(msg);
    }
}
