package data.dao;

import data.country.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class DAO {

    private static JpaService jpaService = JpaService.getJpaService();
    private static EntityManager entityManager;

    public static List<Country> getAllCountries(){
        List<Country> countries = new ArrayList<>();
        try {
            entityManager = jpaService.getEntityManagerFactory().createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            countries = entityManager.createQuery("from Country").getResultList();
            transaction.commit();
        }finally {
            jpaService.close();
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
            jpaService.close();
        }
        return country;
    }

}
