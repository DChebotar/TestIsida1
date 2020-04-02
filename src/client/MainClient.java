package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClient {

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int port;
            while (true) {
                System.out.println("Укажите номер порта (номер порта должен соответствовать номеру порта сервера), для выхода введите exit.");
                try {
                    //Считываем команду из консоли
                    String command = reader.readLine().trim();
                    if (command.equals("exit")) {
                        System.exit(0);
                    }
                    //Определяем номер порта
                    port = Integer.parseInt(command);
                    //Валидируем
                    if (port < 0 || port > 65535) {
                        System.out.println("Номер порта должен быть в диапозоне 0 - 65536.");
                        continue;
                    }
                    //Создаем окно, подписчика и запускаем
                    ClientWindow clientWindow = new ClientWindow();
                    Client client = new Client(port, clientWindow);
                    clientWindow.setVisible(true);
                    System.out.println("Программа запущена на порту " + port);
                    client.listen();
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
