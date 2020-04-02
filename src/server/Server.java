package server;

import java.io.IOException;
import java.net.*;

public class Server {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void sendNews(String news){
        byte[] buf = news.getBytes();
        //Открываем сокет на введенном порту, отправляем сообщение, закрываем сокет. P.S.: Дорогое решение, так что в жизни мы так делать не будем)
        try (DatagramSocket datagramSocket = new DatagramSocket(port)){
            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName("255.255.255.255"), port);
            datagramSocket.send(datagramPacket);
        } catch (UnknownHostException e) {
            System.out.println("Неизвестный адрес хоста.");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода.");
        }
    }
}
