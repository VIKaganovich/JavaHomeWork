package com.pb.kaganovich.hw5;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Класс Читатель
 */
public class Reader {
    private static int lastCardNumber = 0; //Последний номер билета
    private String name;
    private String faculty = "Без факультета";
    private String phone = "Без телефона";
    private LocalDate bithdayDate;
    private int libraryCard;

    public Reader(String name, LocalDate bithdayDate) {
        this.name = name;
        this.bithdayDate = bithdayDate;
        libraryCard = ++lastCardNumber;
    }

    public Reader(String name, String faculty, LocalDate bithdayDate, String phone) {
        this(name, bithdayDate);
        this.phone = phone;
        this.faculty = faculty;
    }

    public Reader(String name, LocalDate bithdayDate, String phone) {
        this(name, bithdayDate);
        this.phone = phone;
    }

    public Reader(String name, String faculty, LocalDate bithdayDate) {
        this(name, bithdayDate);
        this.faculty = faculty;
    }

    public void takeBook(int count) {
        System.out.println(name + " взял " + count + " книги");
    }

    public void takeBook(String... bookNames) {
        System.out.print(name + " взял книги: ");
        for (int i = 0; i < bookNames.length - 1; i++) {
            System.out.print(bookNames[i] + ", ");
        }
        System.out.println(bookNames[bookNames.length - 1] + ".");
    }

    public void takeBook(Book... books) {
        System.out.print(name + " взял книги: ");
        for (int i = 0; i < books.length - 1; i++) {
            System.out.print(books[i].getName() + "( " + books[i].getAuthor() + " "
                    + books[i].getYear() + "г.), ");
        }
        System.out.println(books[books.length - 1].getName() + "( " + books[books.length - 1].getAuthor() + " "
                + books[books.length - 1].getYear() + "г.).");
    }

    public void returnBook(int count) {
        System.out.println(name + " вернул " + count + " книги");
    }

    public void returnBook(String... bookNames) {
        System.out.print(name + " вернул книги: ");
        for (int i = 0; i < bookNames.length - 1; i++) {
            System.out.print(bookNames[i] + ", ");
        }
        System.out.println(bookNames[bookNames.length - 1] + ".");
    }

    public void returnBook(Book... books) {
        System.out.print(name + " вернул книги: ");
        for (int i = 0; i < books.length - 1; i++) {
            System.out.print(books[i].getName() + "( " + books[i].getAuthor() + " "
                    + books[i].getYear() + "г.), ");
        }
        System.out.println(books[books.length - 1].getName() + "( " + books[books.length - 1].getAuthor() + " "
                + books[books.length - 1].getYear() + "г.).");
    }

    String getInfo() {
        return name + ", " + bithdayDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ", факультет: "
                + faculty + ", № билета: " + libraryCard + ", Тел.: " + phone;
    }

}
