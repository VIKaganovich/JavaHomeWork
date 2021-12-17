package com.pb.kaganovich.hw13;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Задача Производитель-потребитель
 */
public class ProducerConsumer {
    private static final int MAX_BUFFER_SIZE = 5;
    private static final int TOTAL_ELEMENTS = 100;

    /**
     * Потребляет строки, печатая их на консоль
     */
    static class StringConsumer implements Runnable {

        private final ReentrantLock lock;
        private final Condition full;
        private final Condition empty;
        private final Queue<String> buffer;
        private final static String color = ConsoleColors.ANSI_CYAN;
        private final static String colorWait = ConsoleColors.ANSI_RED;

        public StringConsumer(ReentrantLock lock, Condition full, Condition empty, Queue<String> buffer) {
            this.lock = lock;
            this.full = full;
            this.empty = empty;
            this.buffer = buffer;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < TOTAL_ELEMENTS; i++) {

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();
                System.out.println(color + threadName + ": Пытаюсь обработать. Элементов осталось: " + buffer.size());
                try {
                    while (buffer.size() < 1) {
                        System.out.println(colorWait + threadName + ": Очередь пуста, отправляюсь отдыхать");
                        empty.await();
                    }
                    System.out.println(color + threadName + ": Считыван элемент " + buffer.poll() + " Элементов осталось: " + buffer.size());
                    full.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(color + threadName + ": Завершена обработка. Элементов осталось: " + buffer.size());
                    lock.unlock();
                }
            }
        }
    }

    /**
     * Производит строки из 10 символов, исползуя случайные коды символов (1024-1123)
     */
    static class StringProducer implements Runnable {

        private final ReentrantLock lock;
        private final Condition full;
        private final Condition empty;
        private final Queue<String> buffer;
        private final static String color = ConsoleColors.ANSI_PURPLE;
        private final static String colorWait = ConsoleColors.ANSI_RED;

        public StringProducer(ReentrantLock lock, Condition full, Condition empty, Queue<String> buffer) {
            this.lock = lock;
            this.full = full;
            this.empty = empty;
            this.buffer = buffer;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            Random random = new Random();
            for (int i = 0; i < TOTAL_ELEMENTS; i++) {

                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();
                System.out.println(color + threadName + ": Пытаюсь запустить производство. Элементов осталось: " + buffer.size());
                try {
                    while (buffer.size() == MAX_BUFFER_SIZE) {
                        System.out.println(colorWait + threadName + ": Очередь переполнена, отправляюсь отдыхать");
                        full.await();
                    }

                    StringBuilder element = new StringBuilder();
                    for (int j = 0; j < 10; j++)
                        element.append((char) (random.nextInt(100) + 1024));
                    System.out.println(color + threadName + ": Создан элемент " + element + " Элементов осталось: " + buffer.size());

                    buffer.offer(element.toString());
                    empty.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(color + threadName + ": Завершено производство. Элементов осталось: " + buffer.size());
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition full = lock.newCondition();
        Condition empty = lock.newCondition();

        Deque<String> buffer = new ArrayDeque<>(MAX_BUFFER_SIZE);

        Thread stringConsumer = new Thread(new StringConsumer(lock, full, empty, buffer));
        Thread stringProducer = new Thread(new StringProducer(lock, full, empty, buffer));

        stringConsumer.setName("Потребитель");
        stringProducer.setName("Производитель");

        System.out.println("Задача \"Производитель-потребитель\"");
        stringConsumer.start();
        stringProducer.start();

    }

}
