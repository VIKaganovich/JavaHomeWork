package com.pb.kaganovich.hw15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Клиент примитивного чата
 */
public class Client implements Runnable {
    private static Socket server;
    private static BufferedReader inServer;
    private static PrintWriter outServer;
    private static JTextField messageField;

    private static void actionPerformed(ActionEvent e) {
        outServer.println(messageField.getText());
        messageField.setText("");
    }

    @Override
    public void run() {
        // создаем фрейм и устанавливаем его размер.
        JFrame jf = new JFrame("Чат");
        jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jf.setSize(400, 300);
        jf.setResizable(false);
        jf.setLayout(new BorderLayout());

        // обработчик закрытия окна (метод windowClosing)
        jf.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                Object[] options = {"Да", "Нет"};
                int n = JOptionPane
                        .showOptionDialog(event.getWindow(), "Закрыть окно?",
                                "Подтверждение", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (n == 0) {
                    event.getWindow().setVisible(false);
                    try {
                        outServer.close();
                        inServer.close();
                        server.close();
                    } catch (IOException ex) {
                        ex.getStackTrace();
                    }
                    System.exit(0);
                }
            }
        });

        // создаем панели
        JPanel panelChat = new JPanel();
        JPanel panelUser = new JPanel();
        panelUser.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // текстовое поле чата
        JTextArea area = new JTextArea("", 15, 50);
        area.setEditable(false);
        panelChat.add(new JScrollPane(area));

        // поле ввода
        messageField = new JTextField(40);
        messageField.setPreferredSize(new Dimension(400, 50));
        messageField.setFont(new Font("", Font.PLAIN, 14));
        messageField.addActionListener(Client::actionPerformed);
        messageField.setToolTipText("Введите сообщение (exit для отключения)");
        panelUser.add(messageField);

        // кнопка "Отправить"
        JButton button = new JButton("Отправить");
        button.setPreferredSize(new Dimension(100, 50));
        button.addActionListener(Client::actionPerformed);
        button.setToolTipText("Отправить сообщение");
        panelUser.add(button);

        jf.add(panelUser, BorderLayout.SOUTH);
        jf.add(panelChat, BorderLayout.CENTER);

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

        //Логика клиента
        area.append("Клиент чата стартовал\n");
        String serverIp = "127.0.0.1";
        int serverPort = 1234;
        area.append("Соединяемся с сервером " + serverIp + ":" + serverPort + "\n");

        try {
            server = new Socket(serverIp, serverPort);
            inServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            outServer = new PrintWriter(server.getOutputStream(), true);
            Thread listener = new Thread(() -> {
                String dataFromServer;
                try {
                    while ((dataFromServer = inServer.readLine()) != null)
                        area.append(dataFromServer + "\n");
                } catch (IOException ex) {
                    ex.getStackTrace();
                }
            });
            listener.start();

        } catch (Exception ex) {
            ex.getStackTrace();
            System.out.println("Не удалось подключиться");
            jf.dispose();
        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Client());

    }
}
