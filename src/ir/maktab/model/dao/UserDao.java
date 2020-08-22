package ir.maktab.model.dao;

import ir.maktab.model.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

import static ir.maktab.view.StoreMain.userList;

public class UserDao {

    private SessionFactoryConfig sessionFactory;

    public void insertToUser(User user){

        Session session = sessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);
        transaction.commit();

        session.close();
        userList.add(user);
    }
    public void readUsersFromDataBase() {
        Session session = sessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);
        userList = query.getResultList();

        transaction.commit();
        session.close();
    }

    public void setSessionFactory(SessionFactoryConfig sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
