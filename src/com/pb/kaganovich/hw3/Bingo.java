package com.pb.kaganovich.hw3;

import java.util.Random;
import java.util.Scanner;

public class Bingo {
    public static void main(String[] args) {
        String inp;
        final String PRG_END = "end";
        Random rand = new Random();
        int bingo = rand.nextInt(101);
        int attempts = 0;
        int x = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("\"Бинго!\" Угадайте число от 0 до 100 (" + PRG_END + " для завершения)");
        do {
            if (attempts % 5 == 0)
                System.out.print("Введите целое число (" + PRG_END + " если надоело): ");
            else
                System.out.print("Введите целое число: ");
            inp = in.next();
            if (inp.equals(PRG_END)) {
                System.out.println("К сожалению Вы не смогли угадать число " + bingo + ", использовав " + attempts + " попыток");
                return;
            }
            try {
                x = Integer.parseInt(inp);
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод (целое число или " + PRG_END + ")");
                continue;
            }
            attempts++;
            if (x > bingo)
                System.out.println("Число " + x + " больше загаданного");
            if (x < bingo)
                System.out.println("Число " + x + " меньше загаданного");
            if (x < 0 || x > 100)
                System.out.println("А был ли смысл в такой попытке?;)");
        } while (x != bingo);

        if (attempts == 1)
            System.out.println("Бинго! Великолепно! Вы угадали число " + bingo + " с ПЕРВОЙ попытки!!!");
        else
            System.out.println("Бинго! Вы угадали число " + bingo + ", использовав " + attempts + " попыток");
    }
}
