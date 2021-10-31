package com.pb.kaganovich.hw5;

/**
 * Класс Книга
 */
public class Book {
    private String name, author;
    private short year;

    public Book(String author, String name, int year) {
        this.name = name;
        this.author = author;
        this.year = (short) year;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public short getYear() {
        return year;
    }

    String getInfo() {
        return author + ", \"" + name + "\", " + year + "г.";
    }
}