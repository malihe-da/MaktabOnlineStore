package model.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {
    public static SessionFactory sessionFactory = new Configuration().configure("view/hibernate.cfg.xml").buildSessionFactory();

    /*public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            sessionFactory = new Configuration().configure("view/hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }*/
}
