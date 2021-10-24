package com.pb.kaganovich.hw4;

import java.util.Arrays;
import java.util.Scanner;

public class Anagram {
    //Сортирует и сравнивает массивы одной длины
    static boolean analyzeArrays(char[] arr1, char[] arr2) {
        if (arr1.length != arr2.length)
            return false;
        //Сортировка
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        // Сравнение
        return Arrays.equals(arr1, arr2);
    }

    public static void main(String[] args) {
        String ana1, ana2;
        Scanner in = new Scanner(System.in);
        System.out.println("\"Проверка анаграмм.\" Введите 2 фразы для анализа");
        System.out.println("Введите 1ю фразу:");
        ana1 = in.nextLine().toUpperCase();
        System.out.println("Введите 2ю фразу:");
        ana2 = in.nextLine().toUpperCase();

        String regex = "[\\s!.;\\-—]"; //Не слова
        char[] arrchar1 = ana1.replaceAll(regex, "").toCharArray();
        char[] arrchar2 = ana2.replaceAll(regex, "").toCharArray();

        if (analyzeArrays(arrchar1, arrchar2))
            System.out.println("Введенные фразы безусловно анаграммы");
        else
            System.out.println("Введенные фразы не являются анаграмамми");
    }
}
