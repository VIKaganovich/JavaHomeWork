package com.pb.kaganovich.hw2;

import java.util.Scanner;

public class Interval {
    public static void main(String[] args) {
        int x;
        Scanner in = new Scanner(System.in);
        System.out.println("\"Вычисление известного интервала\"");
        System.out.print("Введите целое число: ");
        x = in.nextInt();
        if (x >= 0 && x <= 100) {
            if (x < 15) {
                System.out.println("Определен интервал [0-14]");
            }
            if (x > 14 && x < 36) {
                System.out.println("Определен интервал [15-35]");
            }
            if (x > 35 && x < 51) {
                System.out.println("Определен интервал [36-50]");
            }
            if (x > 50) {
                System.out.println("Определен интервал [51-100]");
            }
        } else {
            System.out.println("Неизвестный интервал");
        }
    }
}
