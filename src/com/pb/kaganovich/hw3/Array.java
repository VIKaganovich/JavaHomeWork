package com.pb.kaganovich.hw3;

import java.util.Scanner;

public class Array {
    static void swapArrayElements(int[] arr, int i, int j) {
        int tmp;
        tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1)
                System.out.print(arr[i]);
            else
                System.out.print(arr[i] + ", ");
        }
        System.out.println(" ]");
    }

    public static void main(String[] args) {
        final int ARR_LENGTH = 10;
        int[] array = new int[ARR_LENGTH];
        int posnum = 0; // Счетчик положительных чисел
        int sum = 0; // Сумма чисел массива
        Scanner in = new Scanner(System.in);
        System.out.println("\"Обработка целочисленного массив\"");
        System.out.println("Введите массив из " + ARR_LENGTH + " чисел");
        for (int i = 0; i < array.length; i++) {
            System.out.print("Введите " + (i + 1) + " целое число: ");
            array[i] = in.nextInt();
            if (array[i] > 0)
                posnum++;
            sum += array[i];
        }
        System.out.println("Введен следующий массив:");
        printArray(array);
        System.out.println("Итого в массиве " + posnum + " положительных чисел");
        System.out.println("Сумма чисел в массиве: " + sum);
        boolean swap;
        for (int i = 1; i < array.length; i++) {
            swap = false;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    swapArrayElements(array, j, (j + 1));
                    swap = true;
                }
            }
            if (!swap)
                break;
        }
        System.out.println("Массив отсортированный методом пузырька:");
        printArray(array);
    }
}
