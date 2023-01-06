package application;

import data.country.City;
import data.country.Country;
import data.dao.DAO;

import javax.swing.*;

public class AddCityFrame {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField latitudeField;
    private JTextField longitudeField;
    private JButton addButton;
    private String name;
    private String url;
    private int x;
    private int y;
    private Country country;

    public AddCityFrame(int x, int y, Country country){
        this.x = x;
        this.y = y;
        this.country = country;
        setFrame();
        setPanel();
        setNameField();
        setLatitudeField();
        setLongitudeField();
        setAddButton();
        addElementsToFrame();
        frame.setVisible(true);
    }

    private void addElementsToFrame(){
        panel.add(nameField);
        panel.add(latitudeField);
        panel.add(longitudeField);
        panel.add(addButton);
        frame.add(panel);
    }

    private void setFrame(){
        frame = Components.getJFrame(300,300,"Add new city");
    }

    private void setPanel(){
        panel = Components.getJPanel(300,300);
    }

    private void setNameField(){
        nameField = Components.getTextField(50,37,200,30,"Введите название города");
    }

    private void setLatitudeField(){
        latitudeField = Components.getTextField(50,104,200,30,"Введите широту");
    }

    private void setLongitudeField(){
        longitudeField = Components.getTextField(50,171,200,30,"Введите долготу");
    }

    private void setAddButton(){
        addButton = Components.getJButton(50,238,200,30,"Добавить город");
        addButton.addActionListener(l -> {
            City city = new City(nameField.getText(), Double.parseDouble(latitudeField.getText()), Double.parseDouble(longitudeField.getText()),x,y, country);

            DAO.addCity(country.getId(), city);
            frame.dispose();
        });
    }


}
