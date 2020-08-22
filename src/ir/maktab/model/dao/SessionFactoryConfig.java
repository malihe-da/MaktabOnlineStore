package ir.maktab.model.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {
    public SessionFactory sessionFactory;

    public  SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            sessionFactory = new Configuration().configure("ir/maktab/view/hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }
}
