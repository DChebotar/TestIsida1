package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainServer {
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int port;
            while (true) {
                System.out.println("Укажите номер порта, для выхода введите exit.");
                try {
                    String command = reader.readLine().trim();
                    //Считываем команду из консоли
                    if (command.equals("exit")) {
                        System.exit(0);
                    }
                    //Определяем номер порта, на этом же порту должны слушать клиенты
                    port = Integer.parseInt(command);
                    //Валидируем
                    if (port < 0 || port > 65536) {
                        System.out.println("Номер порта должен быть в диапозоне 0 - 65536.");
                        continue;
                    }
                    //Создаем сервер и окно
                    Server server = new Server(port);
                    ServerWindow serverWindow = new ServerWindow(server);
                    serverWindow.setVisible(true);
                    System.out.println("Сервер запущен на порту " + port);
                    break;
                } catch (IOException e) {
                    System.out.println("Ошибка ввода/вывода.");
                } catch (NumberFormatException | NullPointerException e) {
                    System.out.println("Неверный формат числа.");
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода.");
        }
    }
}
