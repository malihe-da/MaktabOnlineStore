package ir.maktab.model.dao;

import ir.maktab.model.entity.OperationLog;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

import static ir.maktab.view.StoreMain.logList;

public class OperationLogDao {

    private SessionFactoryConfig sessionFactory;

    public void insertIntoOperationLog(OperationLog newLog) {
        /*Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(newLog);

        transaction.commit();
        session.close();*/

    }

    public void readOperationLog() {
        Session session = sessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from OperationLog";
        Query query = session.createQuery(hql);
        logList = query.getResultList();

        transaction.commit();
        session.close();

    }

    public void setSessionFactory(SessionFactoryConfig sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
