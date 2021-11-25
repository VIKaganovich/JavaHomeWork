package com.pb.kaganovich.hw10;

/**
 * Исключение неправильнрк обращение к массиву
 */
public class MyArrayOutOfBoundException extends Exception {
    public MyArrayOutOfBoundException() {
    }

    public MyArrayOutOfBoundException(String message) {
        super(message);
    }
}
