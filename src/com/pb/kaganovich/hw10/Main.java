package com.pb.kaganovich.hw10;

import java.util.Random;

/**
 * Класс Main - демонстрация работы NumBox
 */
public class Main {
    private static final int SIZE_OF_ARRAY_FLOAT = 10;
    private static final int SIZE_OF_ARRAY_INT = 10;

    public static void main(String[] args) {
        NumBox<Float> numBoxFloat = new NumBox<>(SIZE_OF_ARRAY_FLOAT);
        NumBox<Integer> numBoxInt = new NumBox<>(SIZE_OF_ARRAY_INT);
        Random rand = new Random();
        System.out.println("\"Работа с массивами разных типов\"");

        System.out.println("Массив Float");
        int realLength = rand.nextInt(SIZE_OF_ARRAY_FLOAT) + 1;
        for (int i = 0; i < realLength; i++)
            try {
                numBoxFloat.add(100 * rand.nextFloat());
                System.out.print(numBoxFloat.get(i) + " ");
            } catch (MyArrayOutOfBoundException ex) {
                System.out.println(ex.getMessage());
            }
        System.out.println();

        System.out.println("Массив Integer");
        realLength = rand.nextInt(SIZE_OF_ARRAY_INT) + 1;
        for (int i = 0; i < realLength; i++)
            try {
                numBoxInt.add(rand.nextInt(100));
                System.out.print(numBoxInt.get(i) + " ");
            } catch (MyArrayOutOfBoundException ex) {
                System.out.println(ex.getMessage());
            }
        System.out.println();

        System.out.println("*************");
        System.out.println("Данные массива Float");
        show(numBoxFloat);
        System.out.println("*************");
        System.out.println("Данные массива Integer");
        show(numBoxInt);
    }

    public static void show(NumBox<? extends Number> numBox) {
        System.out.println("Элементов в массиве: " + numBox.length());
        try {
            System.out.println("Максимальный элемент: " + numBox.max().doubleValue());
            System.out.println("Сумма элементов: " + numBox.sum());
            System.out.println("Среднее арифметическое: " + numBox.average());
        } catch (MyArrayOutOfBoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
