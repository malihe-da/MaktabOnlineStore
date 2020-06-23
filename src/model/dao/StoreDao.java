package model.dao;

import model.entity.Store;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

import static model.dao.SessionFactoryConfig.sessionFactory;
import static view.StoreMain.stock;

public class StoreDao {



    public static void insertIntoStore(Store newStock) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(newStock);
        transaction.commit();

        session.close();
        stock.add(newStock);
    }

    public static void readStoreFromDataBase() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Store";
        Query query = session.createQuery(hql);
        stock = query.getResultList();

        transaction.commit();
        session.close();
    }

    public static void adminStockUpdate(String name, Double price, int count) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "update Stock s set s.price = :stockPrice and s.count =:stockCount where s.name=: stockName ";
        Query query = session.createQuery(hql);
        query.setParameter("stockPrice", price);
        query.setParameter("stockCount", count);
        query.setParameter("stockName", name);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public static void purchaseUpdate(String name, int count) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "update Store s set s.count =:stockCount where s.name=: stockName ";
        Query query = session.createQuery(hql);
        query.setParameter("stockCount", count);
        query.setParameter("stockName", name);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public static void deleteStoreStock(Store purchase) {
        stock.remove(purchase);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "delete Stock s where s.name = :stockName ";
        Query query = session.createQuery(hql);
        query.setParameter("stockName", purchase.getName());
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

}
