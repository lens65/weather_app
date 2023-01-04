package data.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaService {
    private static JpaService jpaService;
    private EntityManagerFactory entityManagerFactory;

    public JpaService(){
        entityManagerFactory = Persistence.createEntityManagerFactory("weather_app");
    }

    public static JpaService getJpaService(){
        return jpaService == null ? jpaService = new JpaService() : jpaService;
    }

    public void close(){
        if(entityManagerFactory != null) entityManagerFactory.close();
    }

    public EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    }


}
