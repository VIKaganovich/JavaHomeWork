package com.pb.kaganovich.hw4;

import java.util.Scanner;

public class CapitalLetter {
    //Переводит в верхний регистр символ с № index в строке
    static String upCaseLetter(String str, int index) {
        if (index < 0 || index >= str.length())
            return str;
        char[] chars = str.toCharArray();
        chars[index] = Character.toUpperCase(chars[index]);
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("\"Прописная буква.\" Введите фразу для исправления первых букв слов:");
        String[] words = in.nextLine().split("\\s");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++)
            builder.append(upCaseLetter(words[i], 0)).append(" ");
        builder.append(upCaseLetter(words[words.length - 1], 0));

        System.out.println("Исправленная фраза:");
        System.out.println(builder);
    }
}
