package model.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.UserService;

import javax.persistence.Query;

import static view.StoreMain.admins;
import static model.dao.SessionFactoryConfig.sessionFactory;
//import static view.StoreMain.sessionFactory;

public class AdminDao {

    public static void readAdminsFromDataBase() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Admin";
        Query query = session.createQuery(hql);
        admins = query.getResultList();

        transaction.commit();
        session.close();
    }
}
