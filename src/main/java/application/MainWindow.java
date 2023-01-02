package application;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private JFrame frame = new JFrame("Weather in Ukraine");
    private JPanel topPanel = new JPanel();
    private MainPanel mainPanel = new MainPanel();


    public void drawWindow(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topPanel.setBounds(0,0,1300,50);
        topPanel.setBackground(new Color(50, 52, 56));

        frame.add(topPanel);
        mainPanel.setBounds(0, 50,1300,950);
        mainPanel.setBackground(new Color(32, 34, 36));
        frame.add(mainPanel);

        frame.setSize(1300, 1000);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        mainPanel.startDrawing();

    }

}

