package application;

import data.country.Country;

import javax.swing.*;

public class CountryMapFrame {
    private JFrame frame;
    private CountryMap countryMap;


    public CountryMapFrame(Country country){
        setFrame(country);
        setCountryMap(country);
        frame.add(countryMap);
        frame.setVisible(true);
        countryMap.startShowingInfo();
    }
    private void setFrame(Country country){
        frame = Components.getJFrame(1300,950, country.getName());
    }
    private void setCountryMap(Country country){
        countryMap = new CountryMap(country);
    }

}
