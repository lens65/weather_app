package application_v2;

import data.dao.DAO;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ChooseCountryPanel extends ContentPanel{
    private MainFrame frame;
    private JList<String> list;
    private Map<String, Integer> countriesNamesWithId;
    private final Dimension panelSize = new Dimension(200,200);
    private final Color panelBackground = new Color(32, 33, 36);

    public ChooseCountryPanel(MainFrame frame) {
        super(frame);
        this.frame = frame;
        setThisPanel();
    }

    private void setThisPanel(){
        this.setLayout(null);
        this.setSize(panelSize);
        this.setBackground(panelBackground);
        setMap();
        setList();
        this.add(list);

    }
    private void setMap(){
        countriesNamesWithId = DAO.getAllCountriesNames();
    }
    private void setList(){
        list = Components.getJList(panelSize,countriesNamesWithId.keySet().toArray(new String[countriesNamesWithId.size()]));
        list.addListSelectionListener(e -> {
            this.removeAll();
            this.setEnabled(false);
            frame.setContentPanel(new CountryMapPanel(frame, DAO.getCountry(countriesNamesWithId.get(list.getSelectedValue()))));
        });
        list.setLocation(0,0);
        this.add(list);
    }
    @Override
    void actionOnResizing(int width, int height) {
        this.setLocation((width - panelSize.width) / 2, (height - panelSize.width) / 2);
    }
}
