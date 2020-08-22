package ir.maktab.model.dao;

import ir.maktab.model.entity.Store;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

import static ir.maktab.view.StoreMain.stock;

public class StoreDao {

    private SessionFactoryConfig sessionFactory;

    public void insertIntoStore(Store newStock) {
        Session session = sessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(newStock);
        transaction.commit();

        session.close();
        stock.add(newStock);
    }

    public void readStoreFromDataBase() {
        Session session = sessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Store";
        Query query = session.createQuery(hql);
        stock = query.getResultList();

        transaction.commit();
        session.close();
    }

    public void adminStockUpdate(String name, Double price, int count) {
        Session session = sessionFactory.getSessionFactory().openSession();
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

    public void purchaseUpdate(String name, int count) {
        Session session = sessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "update Store s set s.count =:stockCount where s.name=: stockName ";
        Query query = session.createQuery(hql);
        query.setParameter("stockCount", count);
        query.setParameter("stockName", name);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public void deleteStoreStock(Store purchase) {
        stock.remove(purchase);
        Session session = sessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "delete Stock s where s.name = :stockName ";
        Query query = session.createQuery(hql);
        query.setParameter("stockName", purchase.getName());
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    public void setSessionFactory(SessionFactoryConfig sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
