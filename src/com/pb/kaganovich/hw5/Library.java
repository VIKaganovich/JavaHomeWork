package com.pb.kaganovich.hw5;

import java.time.LocalDate;

/**
 * Класс библиотека
 */
public class Library {
    public static void main(String[] args) {
        System.out.println("\"Библиотека.\" Инвентаризация:");
        Book[] books = {new Book("Иванов И. И.", "Приключения", 2000),
                new Book("Сидоров А. В.", "Словарь", 1980),
                new Book("Гусев К. В.", "Энциклопедия", 2010)
        };
        System.out.println("Наши книги:");
        for (Book book : books) {
            System.out.println(book.getInfo());
        }

        Reader[] readers = {new Reader("Петров В. В.", LocalDate.of(1979, 5, 31)),
                new Reader("Васечкин В. Ю.", "мехмат", LocalDate.of(1990, 2, 4), "0961234568"),
                new Reader("Пупкин А. Г.", LocalDate.of(1992, 4, 12), "0678844557")
        };
        System.out.println("Наши читатели:");
        for (Reader reader : readers) {
            System.out.println(reader.getInfo());
        }

        System.out.println("**** Текущая работа ****");
        readers[0].takeBook(43);
        readers[0].returnBook(4);
        readers[1].takeBook(books[0].getName(), books[1].getName(), books[2].getName());
        readers[1].returnBook(books[0].getName(), books[1].getName(), books[2].getName());
        readers[2].takeBook(books[0], books[1], books[2]);
        readers[2].returnBook(books[0], books[1], books[2]);
    }
}
