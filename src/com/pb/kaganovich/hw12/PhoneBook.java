package com.pb.kaganovich.hw12;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Класс телефонная книга
 */
public class PhoneBook {
    private static final String PHONE_BOOK_JSON = "src\\com\\pb\\kaganovich\\hw12\\phonebook.json";
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // pretty printing (json с отступами)
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // для работы с полями типа LocalDateTime, LocalDate
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        mapper.registerModule(module);

        Path jsonPath = Paths.get(PHONE_BOOK_JSON);
        List<Contact> phoneBook = new ArrayList<>();

        while (true) {
            switch (Menu.mainMenu()) {
                //Содержимое книги
                case "1":
                    //Компаратор по умолчанию
                    Comparator<Contact> comparator = Comparator.comparing(Contact::getName);

                    //Альтернативные сортировки
                    switch (Menu.subMenuSort()) {
                        case "2":
                            comparator = Comparator.comparing(Contact::getAddress);
                            break;
                        case "3":
                            comparator = Comparator.comparing(Contact::getDateOfBirth);
                            break;
                    }

//                    Collections.sort(phoneBook, comparator);
                    phoneBook.stream()
                            .sorted(comparator)
                            .forEach(System.out::println);
//                    showBook(phoneBook);
                    System.out.print("Продолжить работу? (Y/n): ");
                    if (scan.nextLine().equals("n"))
                        return;
                    break;
                // Добавление контакта
                case "2":
                    addContact(phoneBook);
                    System.out.print("Продолжить работу? (Y/n): ");
                    if (scan.nextLine().equals("n"))
                        return;
                    break;

                //Поиск, редактирование, удаление контакта
                case "3":
                    int index;
                    //Поиск
                    while (true) {
                        System.out.print("Поиск в книге, введите имя: ");
                        index = searchByName(phoneBook, scan.nextLine());
                        if (index < 0) {
                            System.out.print("Поиск не дал результатов. Продолжить поиск? (Y/n): ");
                            if (scan.nextLine().equals("n"))
                                break;
                        } else
                            break;
                    }
                    if (index < 0)
                        break;
                    System.out.println(phoneBook.get(index));

                    //Редактироввние, удаление
                    switch (Menu.subMenuModify(phoneBook.get(index).getName())) {
                        //Редактирование
                        case "1":
                            editContact(phoneBook.get(index));
                            break;
                        //Удаление
                        case "2":
                            System.out.print("Вы действительно хотите удалить контакт? " + phoneBook.get(index).getName() + " (y/N): ");
                            if (scan.nextLine().equals("y"))
                                try {
                                    phoneBook.remove(index);
                                    System.out.println("Успешное удаление");
                                } catch (Exception ex) {
                                    System.out.println("Не удалось удалить контакт");
                                }
                            System.out.print("Продолжить работу? (Y/n): ");
                            if (scan.nextLine().equals("n"))
                                return;
                            break;
                    }
                    break;

                // Запись книги в файл
                case "4":
                    mapper.writeValue(jsonPath.toFile(), phoneBook);
                    break;
                // Чтение книги из файла
                case "5":
                    phoneBook = mapper.readValue(jsonPath.toFile(), new TypeReference<ArrayList<Contact>>() {
                    });
                    break;

                //Завершение работы
                case "6":
                    System.out.println("Завершение работы");
                    return;
            }
        }

    }

//    public static void showBook(List<Contact> phoneBook) {
//        phoneBook.forEach(System.out::println);
//    }

    public static void addContact(List<Contact> phoneBook) {
        Contact newContact = new Contact();
        System.out.println();
        System.out.println("Добавление контакта:");
        System.out.print("Имя: ");
        newContact.setName(scan.nextLine());
        System.out.print("Адрес: ");
        newContact.setAddress(scan.nextLine());

        while (true) {
            System.out.print("Дата рождения в формате YYYY-MM-DD: ");
            try {
                newContact.setDateOfBirth(LocalDate.parse(scan.nextLine()));
                break;
            } catch (DateTimeParseException ex) {
                System.out.println("Неверный формат");
            }
        }

        System.out.println("Телефоны, разделенные пробелами:");
//        List<String> phones = new ArrayList<>();
//        Collections.addAll(phones, scan.nextLine().split("\\s"));
        List<String> phones = Arrays.stream(scan.nextLine().split("\\s"))
                .collect(Collectors.toCollection(ArrayList::new));
        newContact.setPhone(phones);


        newContact.setLastTimeModified(LocalDateTime.now());
        phoneBook.add(newContact);
        System.out.println("Успешно добавлен " + newContact.getName());
    }

    /**
     * Поиск контакта по имени
     *
     * @param phoneBook Тел. книга
     * @param name      Имя контакта для поиска
     * @return Индекс элемента найденного в книге, -1 если не найдено
     */
    public static int searchByName(List<Contact> phoneBook, String name) {
//        return phoneBook.stream()
//                .filter(contact -> contact.getName().equals(name))
//                .findFirst()
//                .map(phoneBook::indexOf)
//                .orElse(-1);
        return IntStream.range(0, phoneBook.size())
                .filter(index -> phoneBook.get(index).getName().equals(name))
                .findFirst()
                .orElse(-1);
    }


    public static void editContact(Contact contact) {
        do {
            switch (Menu.subMenuEdit(contact.getName())) {
                //Имя
                case "1":
                    System.out.print("Введите новое имя: ");
                    contact.setName(scan.nextLine());
                    System.out.println(contact);
                    break;
                //Адрес
                case "2":
                    System.out.print("Введите новый адрес: ");
                    contact.setAddress(scan.nextLine());
                    System.out.println(contact);
                    break;
                //Дата рождения
                case "3":
                    while (true) {
                        System.out.print("Новая дата рождения в формате YYYY-MM-DD: ");
                        try {
                            contact.setDateOfBirth(LocalDate.parse(scan.nextLine()));
                            break;
                        } catch (DateTimeParseException ex) {
                            System.out.println("Неверный формат");
                        }
                    }
                    break;
                //Телефоны
                case "4":
                    System.out.println("Новый список телефонов, разделенных пробелами:");
//                    List<String> phones = new ArrayList<>();
//                    Collections.addAll(phones, scan.nextLine().split("\\s"));
                    List<String> phones = Arrays.stream(scan.nextLine().split("\\s"))
                            .collect(Collectors.toCollection(ArrayList::new));
                    contact.setPhone(phones);
                    break;
                //Конец редактирования
                case "5":
                    return;
            }

            contact.setLastTimeModified(LocalDateTime.now());
            System.out.print("Продолжить редактирование? (Y/n): ");
        } while (!scan.nextLine().equals("n"));
    }

}
