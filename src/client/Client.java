package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Client {

    private final int port;
    protected static boolean isActive = false;
    private final ClientWindow clientWindow;

    public Client(int port, ClientWindow clientWindow) {
        this.port = port;
        this.clientWindow = clientWindow;
    }

    public void listen() {
        isActive = true;
        /*Можно подумать, чтобы принимать сообщения неограниченной длинны, необходимо вводить признак конца сообщения
        (в данном случае мы так делать не будем и ограничимся 1024)*/
        byte[] buffer = new byte[1024];
        //Открываем сокет UDP, слушаем и выводим сообщения, пока нас не остановят
        try (DatagramSocket datagramSocket = new DatagramSocket(port)){
            while (isActive) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    datagramSocket.receive(packet);
                    String news = new String(buffer, packet.getOffset(), packet.getLength(), "UTF8");
                    showNews(news);
                } catch (IOException ex) {
                    System.out.println("Ошибка при приеме сообщения.");
                }
            }
        } catch (SocketException ex) {
            System.out.println("Невозможно открыть сокет на порту " + port + ". Возможно порт занят другим приложением.");
        }
    }

    private void showNews(String message) {
       clientWindow.getAllNewsArea().append(message + System.lineSeparator());
    }
}
