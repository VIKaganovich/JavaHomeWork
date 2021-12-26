package com.pb.kaganovich.hw15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Сервер примитивного чата (отправляет сообщения каждого клиента всем осталным, включая его самого),
 * добавляет текущую дату и время
 */
public class Server {
    private static final List<Handler> handlers = new CopyOnWriteArrayList<>();

    /**
     * Класс потока ожидающего сообщений пользователя
     */
    static class Handler implements Runnable {
        private final static AtomicInteger connections = new AtomicInteger(0);
        private final int chatNumber;
        private final Socket clientSocket;
        private PrintWriter out;

        public Handler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            handlers.add(this);
            chatNumber = connections.incrementAndGet();
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException ex) {
                ex.getStackTrace();
            }
            System.out.println("Клиент " + chatNumber + " подключился");
            // поток для чтения данных
            BufferedReader in = null;

            try {
                // создаем потоки для связи с клиентом
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException ex) {
                System.out.println("Ошибка обмена данных");
                System.exit(-1);
            }

            String clientMessage;
            // цикл ожидания сообщений от клиента
            try {
                while ((clientMessage = in.readLine()) != null) {
                    if ("exit".equalsIgnoreCase(clientMessage)) {
                        break;
                    }
                    print(clientMessage);
                }
            } catch (IOException ex) {
                ex.getStackTrace();
            }

            handlers.remove(this);
            try {
                // Закрываем все соединения
                out.println("Сервер закрыл соединение");
                out.close();
                in.close();
                clientSocket.close();
            } catch (IOException ex) {
                ex.getStackTrace();
            }
            print("Отключился");
        }

        public void print(String message) {
            for (Handler handler : handlers)
                handler.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) + ": Клиент " + chatNumber + ": " + message);
        }

    }


    public static void main(String[] args) throws Exception {
        System.out.println("Старт сервера");
        int serverPort = 1234;
        // серверный сокет
        ServerSocket server = null;
        // сокет для обслуживания клиента
        Socket clientSocket;

        // создаем серверный сокет
        try {
            server = new ServerSocket(serverPort);
        } catch (IOException e) {
            System.out.println("Ошибка связывания с портом " + serverPort);
            System.exit(-1);
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        // В цикле ждем запроса клиента
        System.out.println("Ждем соединения");
        while (true)
            try {
                clientSocket = server.accept();
                threadPool.submit(new Handler(clientSocket));
            } catch (IOException e) {
                System.out.println("Не могу установить соединение");
                System.exit(-1);
            }

    }

}


