
import application_v2.CountryMapPanel;
import application_v2.MainFrame;
import application_v2.MenuPanel;
import data.dao.DAO;
import data.dao.JpaService;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        JpaService.getJpaService();
        new MainFrame();

    }
}
