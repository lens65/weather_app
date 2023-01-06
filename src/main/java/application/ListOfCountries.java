package application;

import data.dao.DAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Map;

public class ListOfCountries {
    private JFrame frame;
    private JPanel panel;
    private JList<String> list;
    private Map<String, Integer> countriesNamesWithId;

    public ListOfCountries(){
        setFrame();
        setPanel();
        setMap();
        setList();
        addElementsToFrame();

        frame.setVisible(true);
    }

    private void addElementsToFrame(){
        panel.add(list);
        frame.add(panel);
    }

    private void setFrame(){
        frame = Components.getJFrame(100,200, "Select country");
    }

    private void setPanel(){
        panel = Components.getJPanel(100,200);
    }

    private void setMap(){
        countriesNamesWithId = DAO.getAllCountriesNames();
    }
    private void setList(){
        list = Components.getJList(0,0,100,200,countriesNamesWithId.keySet().toArray(new String[countriesNamesWithId.size()]));
        list.addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(list.getSelectedValue());
                new CountryMapFrame(DAO.getCountry(countriesNamesWithId.get(list.getSelectedValue())));
                frame.dispose();
            }
        });
    }
}
