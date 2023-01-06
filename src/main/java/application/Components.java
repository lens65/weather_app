package application;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Components {
    public static JFrame getJFrame(int width, int height, String name){
        JFrame frame = new JFrame(name);
//        frame.setUndecorated(true);
//        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.ORANGE));
        frame.pack();
        Insets insets = frame.getInsets();
        frame.setSize(insets.left + insets.right + width, insets.top + insets.bottom + height);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    public static JPanel getJPanel(int width, int height){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,width,height);
        panel.setBackground(new Color(32, 33, 36));
        return panel;
    }

    public static JButton getJButton(int x, int y, int width, int height, String name){
        JButton button = new JButton(name);
        button.setBounds(x,y,width,height);
        button.setBackground(new Color(72, 72, 74));
        button.setFocusPainted(false);
        button.setForeground(new Color(176, 176, 176));
        button.setBorderPainted(false);
        button.setFont(new Font("Serif", Font.PLAIN, 15));
        return button;
    }

    public static JTextField getTextField(int x, int y, int width, int height, String name){
        JTextField field = new JTextField(name);
        field.setBounds(x,y,width,height);
        return field;
    }

    public static JList<String> getJList(int x, int y, int width, int height, String[] str){
        JList<String> jList = new JList<>(str);
        jList.setLayout(null);
        jList.setBackground(new Color(32, 33, 36));
        jList.setBounds(x,y,width,height);
        return jList;
    }
}
