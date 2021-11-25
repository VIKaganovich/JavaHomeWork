package com.pb.kaganovich.hw10;

/**
 * Исключение неправильное обращение к массиву
 */
public class MyArrayOutOfBoundException extends Exception {
    public MyArrayOutOfBoundException() {
    }

    public MyArrayOutOfBoundException(String message) {
        super(message);
    }
}
