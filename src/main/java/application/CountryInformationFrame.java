package application;

import data.country.Country;

import javax.swing.*;

public class CountryInformationFrame {
    private JFrame frame;
    private CountryInformation countryInformation;


    public CountryInformationFrame(Country country){
        setFrame(country);
        setCountryInformation(country);
        frame.add(countryInformation);
        frame.setVisible(true);
        countryInformation.startShowingInfo();
    }
    private void setFrame(Country country){
        frame = Components.getJFrame(1300,950, country.getName());
    }
    private void setCountryInformation(Country country){
        countryInformation = new CountryInformation(country);
    }

}
