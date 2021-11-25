package com.pb.kaganovich.hw10;

/**
 * Исключение неправильный индекс
 */
public class MyArrayOutOfBoundException extends Exception {
    public MyArrayOutOfBoundException() {
    }

    public MyArrayOutOfBoundException(String message) {
        super(message);
    }
}
