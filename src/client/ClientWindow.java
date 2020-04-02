package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientWindow extends JFrame{

    private JTextArea allNewsArea;

    public ClientWindow(){
        //Формируем окно подписчика
        super("Test Isida 1");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        allNewsArea = new JTextArea(10,50);
        allNewsArea.setLineWrap(true);
        allNewsArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        JScrollPane allNewsPane = new JScrollPane(allNewsArea);
        allNewsPane.setBorder(BorderFactory.createTitledBorder("Новости"));
        allNewsPane.setBounds(25,10,550,500);
        mainPanel.add(allNewsPane);
        getContentPane().add(mainPanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 550));
        pack();
        setLocationRelativeTo(null);
        //При закрытии окна выходим из цикла в методе run() класса Client, чтобы закрыть сокет
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Client.isActive = false;
                super.windowClosing(e);
            }
        });
    }

    public JTextArea getAllNewsArea() {
        return allNewsArea;
    }
}
