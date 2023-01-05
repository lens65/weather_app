package application;

import data.country.Country;
import data.dao.DAO;

import javax.swing.*;
import java.awt.*;

public class AddCountryFrame {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField urlField;
    private JButton addButton;
    private String name;
    private String url;

    public AddCountryFrame(){
        setFrame();
        setPanel();
        setNameField();
        setUrlField();
        setAddButton();
        addElementsToFrame();
        frame.setVisible(true);
    }

    private void addElementsToFrame(){
        panel.add(nameField);
        panel.add(urlField);
        panel.add(addButton);
        frame.add(panel);
    }

    private void setFrame(){
        frame = Components.getJFrame(300,200, "Add new country");
    }

    private void setPanel(){
        panel = Components.getJPanel(300,200);
    }

    private void setNameField(){
        nameField = Components.getTextField(50,30,200,30,"Введите название страны");
    }
    private void setUrlField(){
        urlField = Components.getTextField(50,85,200,30,"Введите url карты");
    }
    private void setAddButton(){
        addButton = Components.getJButton(50,140,200,30,"Добавить");
        addButton.addActionListener(l -> {
            name = nameField.getText();
            url = urlField.getText();

            if(name != null && urlField != null){
                Country country = new Country(name,url);
                DAO.saveCountry(country);
                frame.dispose();
            }
        });
    }

}
