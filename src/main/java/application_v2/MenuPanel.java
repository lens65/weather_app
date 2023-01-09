package application_v2;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends ContentPanel {
    private MainFrame frame;
    private JButton chooseCountryButton;
    private JButton addNewCountryButton;
    private final Dimension buttonSize = new Dimension(200,30);
    private final Dimension panelSize = new Dimension(200,90);
    private final Color panelBackground = new Color(32, 33, 36);

    public MenuPanel(MainFrame frame){
        super(frame);
        this.frame = frame;
        setThisPanel();
    }
    private void setThisPanel(){
        this.setLayout(null);
        this.setSize(panelSize);
        this.setBackground(panelBackground);
        setChooseCountryButton();
        setAddNewCountryButton();
        this.add(chooseCountryButton);
        this.add(addNewCountryButton);
    }
    private void setChooseCountryButton(){
        chooseCountryButton = Components.getButton(buttonSize,"Выбрать страну");
        chooseCountryButton.setLocation(0,0);
        chooseCountryButton.addActionListener(l -> {
            this.removeAll();
            this.setEnabled(false);
            frame.setContentPanel(new ChooseCountryPanel(frame));
        });
    }
    private void setAddNewCountryButton(){
        addNewCountryButton = Components.getButton(buttonSize, "Добавить новую страну");
        addNewCountryButton.setLocation(0,60);
        addNewCountryButton.addActionListener(l -> {
            this.removeAll();
            this.setEnabled(false);
            frame.setContentPanel(new AddCountryPanel(frame));
        });
    }
    @Override
    public void actionOnResizing(int width, int height){
        this.setLocation((width - panelSize.width) / 2, (height - panelSize.width) / 2);
    }
}
