package com.pb.kaganovich.hw10;

import java.util.Random;

/**
 * Класс Main - демонстрация работы
 */
public class Main {
    private static final int SIZEOFARRAYFLOAT = 6;
    private static final int SIZEOFARRAYINT = 5;

    public static void main(String[] args) {
        NumBox<Float> numBoxFloat = new NumBox<>(SIZEOFARRAYFLOAT);
        NumBox<Integer> numBoxInt = new NumBox<>(SIZEOFARRAYINT);
        Random rand = new Random();
        System.out.println("\"Работа с массивами разных типов\"");

        System.out.println("Массив Float");
        for (int i = 0; i < SIZEOFARRAYFLOAT; i++)
            try {
                numBoxFloat.add(100 * rand.nextFloat());
                System.out.print(numBoxFloat.get(i) + " ");
            } catch (MyArrayOutOfBoundException ex) {
                System.out.println(ex.getMessage());
            }
        System.out.println();

        System.out.println("Массив Integer");
        for (int i = 0; i < SIZEOFARRAYINT; i++)
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
