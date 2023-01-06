package data.dao;

import data.country.City;
import data.country.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DAO {

    private static JpaService jpaService = JpaService.getJpaService();
    private static EntityManager entityManager;

//    public DAO(){
//        jpaService = JpaService.getJpaService();
//    }

    public static List<Country> getAllCountries(){
        List<Country> countries = new ArrayList<>();
        try {
            entityManager = jpaService.getEntityManagerFactory().createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            countries = entityManager.createQuery("from Country").getResultList();
            transaction.commit();
        }finally {
            entityManager.close();
        }

        return countries;
    }

    public static Country getCountry(int id){
        Country country = new Country();
        try {
            entityManager = jpaService.getEntityManagerFactory().createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            country = entityManager.find(Country.class, id);
            transaction.commit();
        } finally {
            entityManager.close();
        }
        return country;
    }

    public static TreeMap<String, Integer> getAllCountriesNames(){
        TreeMap<String, Integer> map = new TreeMap<>();
        List<Object[]> list = new ArrayList<>();
        try {
            entityManager = jpaService.getEntityManagerFactory().createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            list = entityManager.createQuery("select name,id from Country").getResultList();
            transaction.commit();
        }finally {
            entityManager.close();
        }
        for(Object[] object : list){
            map.put((String)object[0], (Integer)object[1]);
        }
        return map;
    }

    public static void saveCountry(Country country){
        try {
            entityManager = jpaService.getEntityManagerFactory().createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(country);
            transaction.commit();
        }finally {
            entityManager.close();
        }
    }

    public static void addCity(int id, City city){
        try {
            entityManager = jpaService.getEntityManagerFactory().createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.find(Country.class, id).addCityToCountry(city);
            transaction.commit();
        }finally {
            entityManager.close();
        }
    }

    public static void setWeather(City city){
        try {
            entityManager = jpaService.getEntityManagerFactory().createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            City newCity = entityManager.find(City.class, city.getId());
            newCity.setTime(city.getTime());
            newCity.setJsonWeather(city.getJsonWeather());
            transaction.commit();
        }finally {
            entityManager.close();
        }
    }

}
