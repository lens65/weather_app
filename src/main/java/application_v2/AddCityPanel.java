package application_v2;

import data.country.City;
import data.country.Country;
import data.dao.DAO;

import javax.swing.*;
import java.awt.*;

public class AddCityPanel extends ContentPanel {
    private MainFrame frame;
    private JTextField nameField;
    private JTextField latitudeField;
    private JTextField longitudeField;
    private JButton addButton;
    private JButton cancelButton;
    private String latitude;
    private String longitude;
    private final Country country;
    private final int x;
    private final int y;
    private final Dimension buttonSize = new Dimension(200,30);
    private final Dimension fieldSize = new Dimension(200,30);
    private final Dimension panelSize = new Dimension(200,270);
    private final Color panelBackground = new Color(32, 33, 36);

    public AddCityPanel(MainFrame frame, Country country, int x, int y) {
        super(frame);
        this.frame = frame;
        this.country = country;
        this.x = x;
        this.y = y;
        setThisPanel();
    }
    private void setThisPanel(){
        this.setLayout(null);
        this.setSize(panelSize);
        this.setBackground(panelBackground);
        setNameField();
        setLatitudeField();
        setLongitudeField();
        setAddButton();
        setCancelButton();

    }
    private void setNameField(){
        nameField = Components.getTextField(fieldSize,"Введите название города");
        nameField.setLocation(0,0);
        this.add(nameField);
    }
    private void setLatitudeField(){
        latitudeField = Components.getTextField(fieldSize,"Введите широту");
        latitudeField.setLocation(0,60);
        this.add(latitudeField);
    }
    private void setLongitudeField(){
        longitudeField = Components.getTextField(fieldSize,"Введите долготу");
        longitudeField.setLocation(0,120);
        this.add(longitudeField);
    }
    private void setAddButton(){
        addButton = Components.getButton(buttonSize, "Добавить");
        addButton.setLocation(0,180);
        this.add(addButton);
        addButton.addActionListener(l -> {
            City city = new City(nameField.getText(), Double.parseDouble(latitudeField.getText()), Double.parseDouble(longitudeField.getText()),x,y, country);
            DAO.addCity(country.getId(), city);
            this.removeAll();
            this.setEnabled(false);
            frame.setContentPanel(new CountryMapPanel(frame, DAO.getCountry(country.getId())));
        });
    }
    private void setCancelButton(){
        cancelButton = Components.getButton(buttonSize, "Назад");
        cancelButton.setLocation(0,240);
        this.add(cancelButton);
        cancelButton.addActionListener(l -> {
            this.removeAll();
            this.setEnabled(false);
            frame.setContentPanel(new CountryMapPanel(frame, country));
        });
    }


    @Override
    void actionOnResizing(int width, int height) {
        this.setLocation((width - panelSize.width) / 2, (height - panelSize.width) / 2);
    }
}
