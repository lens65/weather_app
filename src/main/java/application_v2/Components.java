package application_v2;

import javax.swing.*;
import java.awt.*;

public class Components {
    public static JButton getButton(Dimension dimension, String name){
        JButton button = new JButton(name);
        button.setSize(dimension);
        button.setBackground(new Color(72, 72, 74));
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setForeground(new Color(176, 176, 176));
        button.setFont(new Font("Serif", Font.BOLD, 17));
        return button;
    }
    public static JTextField getTextField(Dimension dimension, String text){
        JTextField textField = new JTextField(text);
        textField.setSize(dimension);
        return textField;
    }
    public static JList<String> getJList(Dimension dimension, String[] str){
        JList<String> jList = new JList<>(str);
        jList.setLayout(null);
        jList.setFont(new Font("Serif", Font.BOLD, 25));
        jList.setSelectionBackground(Color.white);
        jList.setBackground(new Color(32, 33, 36));
        jList.setSize(dimension);
        return jList;
    }
}
