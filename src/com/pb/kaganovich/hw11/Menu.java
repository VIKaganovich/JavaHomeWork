package com.pb.kaganovich.hw11;

import java.util.Scanner;

/**
 * Класс меню взаимодействия пользователя с телефонной книгой
 */
public abstract class Menu {
    private static final Scanner scan = new Scanner(System.in);

    public static String mainMenu() {
        String option;
        System.out.println("--------");
        System.out.println("Телефонная книга");
        System.out.println("1. Просмотр книги");
        System.out.println("2. Добавить контакт");
        System.out.println("3. Поиск, редактирование, удаление контакта");
        System.out.println("4. Запись книги в файл");
        System.out.println("5. Чтение книги из файла");
        System.out.println("6. Выход");

        while (true) {
            System.out.print("Выберите пункт: ");
            option = scan.nextLine();
            if (!option.matches("[1-6]"))
                System.out.println("Ошибочный ввод");
            else
                break;
        }

        return option;
    }

    public static String subMenuSort() {
        String option;
        System.out.println("--------");
        System.out.println("Сортировка");
        System.out.println("1. По имени (по умолчанию)");
        System.out.println("2. По адресу");
        System.out.println("3. По дате рождения");

        System.out.print("Выберите пункт: ");
        option = scan.nextLine();
        if (option.matches("[2-3]"))
            return option;

        return "1";
    }

    public static String subMenuModify(String name) {
        String option;
        System.out.println("--------");
        System.out.println("Работа с контактом " + name);
        System.out.println("1. Редактировать");
        System.out.println("2. Удалить");
        System.out.println("3. Выход в основное меню (по умолчанию)");

        System.out.print("Выберите пункт: ");
        option = scan.nextLine();
        if (option.matches("[1-2]"))
            return option;

        return "3";
    }

    public static String subMenuEdit(String name) {
        String option;
        System.out.println("--------");
        System.out.println("Редактирование контакта " + name);
        System.out.println("1. Имя");
        System.out.println("2. Адрес");
        System.out.println("3. Дата рождения");
        System.out.println("4. Телефоны");
        System.out.println("5. Выход в основное меню (по умолчанию)");

        System.out.print("Выберите пункт: ");
        option = scan.nextLine();
        if (option.matches("[1-4]"))
            return option;

        return "5";
    }

}
