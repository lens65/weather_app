package application_v2;

import data.country.Country;
import data.dao.DAO;

import javax.swing.*;
import java.awt.*;

public class AddCountryPanel extends ContentPanel{
    private MainFrame frame;
    private JTextField nameField;
    private JTextField urlField;
    private JButton addButton;
    private JButton cancelButton;
    private String name;
    private String url;
    private final Dimension buttonSize = new Dimension(200,30);
    private final Dimension fieldSize = new Dimension(200,30);
    private final Dimension panelSize = new Dimension(200,210);
    private final Color panelBackground = new Color(32, 33, 36);
//    private final Color panelBackground = Color.BLUE;

    public AddCountryPanel(MainFrame frame) {
        super(frame);
        this.frame = frame;
        setThisPanel();
    }
    private void setThisPanel(){
        this.setLayout(null);
        this.setSize(panelSize);
        this.setBackground(panelBackground);
        setNameField();
        setUrlField();
        setAddButton();
        setCancelButton();
    }
    @Override
    void actionOnResizing(int width, int height) {
        this.setLocation((width - panelSize.width) / 2, (height - panelSize.width) / 2);
    }
    private void setNameField(){
        nameField = Components.getTextField(fieldSize,"Введите название страны");
        nameField.setLocation(0,0);
        this.add(nameField);
    }
    private void setUrlField(){
        urlField = Components.getTextField(fieldSize, "Введите url карты");
        urlField.setLocation(0,60);
        this.add(urlField);
    }
    private void setAddButton(){
        addButton = Components.getButton(buttonSize, "Добавить");
        addButton.setLocation(0,120);
        this.add(addButton);
        addButton.addActionListener(l -> {
            name = nameField.getText();
            url = urlField.getText();
            if(name != null && urlField != null){
                Country country = new Country(name,url);
                DAO.saveCountry(country);
            }
        });
    }
    private void setCancelButton(){
        cancelButton = Components.getButton(buttonSize,"Отмена");
        cancelButton.setLocation(0,180);
        this.add(cancelButton);
        cancelButton.addActionListener(l ->{
            this.removeAll();
            this.setEnabled(false);
            frame.setContentPanel(new MenuPanel(frame));
        });
    }
}
