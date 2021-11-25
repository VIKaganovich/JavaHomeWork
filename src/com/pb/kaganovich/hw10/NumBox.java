package com.pb.kaganovich.hw10;

/**
 * Класс NumBox контейнер массива чисел
 *
 * @param <T> любые числовые типы
 */
public class NumBox<T extends Number> {
    private final T[] numbers;

    @SuppressWarnings("unchecked")
    public NumBox(int size) {
        numbers = (T[]) new Number[size];
    }

    public int length() {
        for (int i = 0; i < numbers.length; i++)
            if (numbers[i] == null)
                return i;
        return numbers.length;
    }

    public T get(int index) throws MyArrayOutOfBoundException {
        if (index < 0 || index >= length())
            throw new MyArrayOutOfBoundException("Неверный доступ к массиву");
        return numbers[index];
    }

    public void add(T num) throws MyArrayOutOfBoundException {
        int len = length();
        if (len == numbers.length)
            throw new MyArrayOutOfBoundException("Ошибка добавления в массив");
        numbers[len] = num;
    }

    public T max() throws MyArrayOutOfBoundException {
        int len = length();
        if (len == 0)
            throw new MyArrayOutOfBoundException("Обращение к элементам пустого массива");
        T max = numbers[0];
        for (int i = 1; i < len; i++)
            if (max.doubleValue() < numbers[i].doubleValue())
                max = numbers[i];
        return max;
    }

    public double sum() throws MyArrayOutOfBoundException {
        int len = length();
        if (len == 0)
            throw new MyArrayOutOfBoundException("Обращение к элементам пустого массива");
        double sum = numbers[0].doubleValue();
        for (int i = 1; i < len; i++)
            sum += numbers[i].doubleValue();
        return sum;
    }
    public double average() throws MyArrayOutOfBoundException {
        int len = length();
        if (len == 0)
            throw new MyArrayOutOfBoundException("Обращение к элементам пустого массива");
        return sum()/len;
    }
}
