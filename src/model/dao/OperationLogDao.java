package model.dao;

import model.entity.OperationLog;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.UserService;

import javax.persistence.Query;

import static view.StoreMain.logList;
//import static view.StoreMain.sessionFactory;

import static model.dao.SessionFactoryConfig.sessionFactory;

public class OperationLogDao {

    public static void insertIntoOperationLog(OperationLog newLog) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(newLog);

        transaction.commit();
        session.close();

    }

    public static void readOperationLog() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from OperationLog";
        Query query = session.createQuery(hql);
        logList = query.getResultList();

        transaction.commit();
        session.close();

    }
}
