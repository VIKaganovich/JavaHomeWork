package com.pb.kaganovich.hw9;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Класс обработчика числовых файлов
 */
public class FileNumbers {
    private static final String pathToNumbersFile = "src\\com\\pb\\kaganovich\\hw9\\numbers.txt";
    private static final String pathToOddNumbersFile = "src\\com\\pb\\kaganovich\\hw9\\odd-numbers.txt";
    private static final String pathToLogProperties = "src\\com\\pb\\kaganovich\\hw9\\logging.properties";
    private static final Logger LOGGER = Logger.getLogger(FileNumbers.class.getName());

    public static void createNumbersFile() {
        LOGGER.log(Level.INFO, "Метод createNumbersFile() стартует");
        Random rand = new Random();

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathToNumbersFile))) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++)
                    writer.write(String.valueOf(rand.nextInt(100) + 1) + " ");
                writer.write(String.valueOf(rand.nextInt(100) + 1));
                if (i < 9)
                    writer.newLine();
                LOGGER.log(Level.INFO, "Добавлена строка " + (i + 1) + " в исходный файл");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Ошибка создания исходного файла ", ex);

        }
        LOGGER.log(Level.INFO, "Метод createNumbersFile() завершает работу ");

    }

    public static void createOddNumbersFile() {
        LOGGER.log(Level.INFO, "Метод createOddNumbersFile() стартует");
        int num, numbersInResultFile;

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathToOddNumbersFile))) {

            try (Scanner scan = new Scanner(Paths.get(pathToNumbersFile))) {
                numbersInResultFile = 0;
                while (scan.hasNextInt()) {
                    num = scan.nextInt();
                    if (num % 2 == 0)
                        num = 0;
                    numbersInResultFile++;
                    if (numbersInResultFile % 10 == 0) {
                        writer.write(String.valueOf(num));
                        LOGGER.log(Level.INFO, "В результирующий файл записано " + numbersInResultFile + " чисел");
                        if (numbersInResultFile < 100)
                            writer.newLine();
                    } else
                        writer.write(String.valueOf(num) + " ");
                }
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Ошибка обработки исходного файла ", ex);
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Ошибка записи в результирующий файл ", ex);
        }
        LOGGER.log(Level.INFO, "Метод createOddNumbersFile() завершает работу ");
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("java.util.logging.config.file", pathToLogProperties);
        LogManager.getLogManager().readConfiguration();
        LOGGER.log(Level.INFO, "Начинаю обработку...");

        System.out.println("\"Обработка числового файла\"");

        createNumbersFile();
        createOddNumbersFile();

        System.out.println("Подробности обработки смотрите в лог-файле");

        LOGGER.log(Level.INFO, "Обработка завершена.");
    }
}
