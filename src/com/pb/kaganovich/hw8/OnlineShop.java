package com.pb.kaganovich.hw8;

import java.util.Scanner;

/**
 * Класс онлайн магазин (авторизация)
 */
public class OnlineShop {


    public static void main(String[] args) {
        String login, password, confirmpassword;
        Auth auth = new Auth();
        Scanner in = new Scanner(System.in);
        System.out.println("\"Онлайн магазин (авторизация).\"");


        while (true) {
            System.out.println("Регистрируемся...");
            System.out.print("Логин: ");
            login = in.nextLine();
            System.out.print("Пароль: ");
            password = in.nextLine();
            System.out.print("Подтверждение пароля: ");
            confirmpassword = in.nextLine();

            try {
                auth.signUp(login, password, confirmpassword);
                break;
            } catch (WrongLoginException | WrongPasswordException ex) {
                System.out.println(ex.getMessage());
            }
        }
        int attempts = 0;
        while (attempts < 3) {
            System.out.println("Авторизуемся...");
            System.out.print("Логин: ");
            login = in.nextLine();
            System.out.print("Пароль: ");
            password = in.nextLine();
            attempts++;
            try {
                auth.signIn(login, password);
                break;
            } catch (WrongLoginException | WrongPasswordException ex) {
                System.out.println("Неправильный логин или пароль");
            }
        }
        if (attempts < 3)
            System.out.println("Успешная авторизация");
        else
            System.out.println("Подобрать пароль не удалось");

    }
}
