package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerWindow extends JFrame{
    private JTextArea allNewsArea;
    private JTextArea newsArea;
    private JButton publishButton;

    public ServerWindow(Server server){
        //формируем окно издателя
        super("Test Isida 1");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        allNewsArea = new JTextArea(10,50);
        allNewsArea.setLineWrap(true);
        allNewsArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        allNewsArea.setEditable(false);
        JScrollPane allNewsPane = new JScrollPane(allNewsArea);
        allNewsPane.setBorder(BorderFactory.createTitledBorder("Архив новостей"));
        allNewsPane.setBounds(25,10,550,200);
        mainPanel.add(allNewsPane);
        newsArea = new JTextArea(10,50);
        newsArea.setLineWrap(true);
        newsArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        JScrollPane newsPane = new JScrollPane(newsArea);
        newsPane.setBorder(BorderFactory.createTitledBorder("Новые новости"));
        newsPane.setBounds(25,230,550,200);
        mainPanel.add(newsPane);
        publishButton = new JButton("Опубликовать");
        publishButton.setBounds(375,450,200,30);
        mainPanel.add(publishButton);
        getContentPane().add(mainPanel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 550));
        pack();
        setLocationRelativeTo(null);
        //добавляем обработчик событий по нажатию кнопки "опубликовать"
        publishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //если сообщение не пустое, отображаем его в архиве и отраляем по протоколу UDP в сеть
                if(!newsArea.getText().trim().isEmpty()){
                    server.sendNews(newsArea.getText().trim());
                    getAllNewsArea().append(newsArea.getText().trim() + System.lineSeparator());
                    newsArea.setText("");
                    //иначе очищаем поле ввода и выводим сообщение
                }else{
                    newsArea.setText("");
                    JOptionPane.showMessageDialog(null, "Отсутствует текст новости", "Новость не опубликована", JOptionPane.DEFAULT_OPTION);
                }
            }
        });
    }

    public JTextArea getAllNewsArea() {
        return allNewsArea;
    }
}
