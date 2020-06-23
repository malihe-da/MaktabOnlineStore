package model.dao;

import model.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;


//import static view.StoreMain.sessionFactory;
import static model.dao.SessionFactoryConfig.sessionFactory;
import static view.StoreMain.userList;

public class UserDao {

    public static void insertToUser(User user){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);
        transaction.commit();

        session.close();
        userList.add(user);
    }
    public static void readUsersFromDataBase() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);
        userList = query.getResultList();

        transaction.commit();
        session.close();
    }
}
