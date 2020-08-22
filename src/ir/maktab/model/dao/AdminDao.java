package ir.maktab.model.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

import static ir.maktab.view.StoreMain.admins;

public class AdminDao {

    private SessionFactoryConfig sessionFactory;

    public void readAdminsFromDataBase() {
        Session session = sessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Admin";
        Query query = session.createQuery(hql);
        admins = query.getResultList();

        transaction.commit();
        session.close();
    }

    public void setSessionFactory(SessionFactoryConfig sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
