package com.pb.kaganovich.hw2;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        int operand1, operand2;
        String sign;
        Scanner in = new Scanner(System.in);
        System.out.println("\"Примитивный калькулятор\"");
        System.out.print("Введите 1й операнд (целое число): ");
        operand1 = in.nextInt();
        System.out.print("Выберите знак операции (+ - * /): ");
        sign = in.next();
        System.out.print("Введите 2й операнд (целое число): ");
        operand2 = in.nextInt();
        switch (sign.charAt(0)) {
            case '+':
                System.out.println(operand1 + " " + sign + " " + operand2 + " = " + (operand1 + operand2));
                break;
            case '-':
                System.out.println(operand1 + " " + sign + " " + operand2 + " = " + (operand1 - operand2));
                break;
            case '*':
                System.out.println(operand1 + " " + sign + " " + operand2 + " = " + (operand1 * operand2));
                break;
            case '/':
                if (operand2 == 0) {
                    System.out.println("Деление на 0");
                    break;
                }
                System.out.println(operand1 + " " + sign + " " + operand2 + " = " + (operand1 / operand2));
                if (operand1 % operand2 != 0) {
                    System.out.println("Было выполнено округление...");
                    break;
                }
            default:
                System.out.println("Недопустимая операция "+sign);
        }
    }
}
