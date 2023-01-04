import application.MainWindow;
import data.country.City;
import data.country.Country;
import data.country.Region;
import data.dao.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.drawWindow();



//        for(Country country : DAO.getAllCountries()){
//            System.out.println("Название страны: " + country.getName());
//            System.out.println("Id страны: " + country.getId());
//            System.out.println("Регионы страны: ");
//            for(Region region : country.getRegions()){
//                System.out.println("-" + region.getName());
//            }
//        }
//
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("weather_app");
//
//        EntityManager manager = factory.createEntityManager();
//
//        EntityTransaction transaction = manager.getTransaction();
//
//        transaction.begin();
//
//        List<City> cities = manager.createQuery("from City").getResultList();
//
//        transaction.commit();
//
//        factory.close();
//
//        System.out.println(cities.size());
//
//        System.out.println(cities.get(1).getJsonWeather());
//        System.out.println(cities.get(1).getJsonWeather().getClass());

    }
}
