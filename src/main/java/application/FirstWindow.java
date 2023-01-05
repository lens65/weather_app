package application;

import javax.swing.*;
import java.awt.*;

public class FirstWindow {
    private JFrame frame;
    private JPanel panel;
    private JButton chooseCountryButton;
    private JButton addNewCountryButton;

    public FirstWindow(){
        setFrame();
        setChooseCountryButton();
        setAddNewCountryButton();
        setPanel();
        addElementsToFrame();
        frame.setVisible(true);
    }

    private void setFrame(){
        frame = Components.getJFrame(300,200,"Weather");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addElementsToFrame(){
        panel.add(chooseCountryButton);
        panel.add(addNewCountryButton);
        frame.add(panel);
    }

    private void setPanel(){
        panel = Components.getJPanel(300,200);
    }

    private void setChooseCountryButton(){
        chooseCountryButton = Components.getJButton(50,50,200,30,"Выбрать страну");
        chooseCountryButton.addActionListener(l -> {
            new ListOfCountries();
        });
    }

    private void setAddNewCountryButton(){
        addNewCountryButton = Components.getJButton(50,120,200,30,"Добавить новую страну");
        addNewCountryButton.addActionListener(l -> {
            new AddCountryFrame();
        });
    }
}
