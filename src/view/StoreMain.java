package view;

import entity.Admin;
import entity.OperationLog;
import entity.Store;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import service.AdminService;
import service.UserService;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreMain {

    public static SessionFactory sessionFactory = new Configuration().configure("view/hibernate.cfg.xml").buildSessionFactory();

    public static List<Store> stock = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();
    public static List<Admin> admins = new ArrayList<>();
    public static List<OperationLog> logList = new ArrayList<>();

    public static void main(String[] args) {
        readStoreFromDataBase();
        readUsersFromDataBase();
        readAdminsFromDataBase();

        int inputKey = welcome();

        switch (inputKey) {
            case 1:
                UserService.userLogIn(1);
                break;
            case 2:
                UserService.userLogIn(2);
                break;
            case 3:
                AdminService.adminController();
                break;
        }

    }

    private static void readAdminsFromDataBase() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Admin";
        Query query = session.createQuery(hql);
        admins = query.getResultList();

        transaction.commit();
        session.close();
    }


    private static void readUsersFromDataBase() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);
        userList = query.getResultList();

        transaction.commit();
        session.close();
    }

    private static void readStoreFromDataBase() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Store";
        Query query = session.createQuery(hql);
        stock = query.getResultList();

        transaction.commit();
        session.close();
    }

    private static int welcome() {
        System.out.println("Welcome to online store! " +
                "\nFor sign up (enter 1)" +
                "\nFor sign in (enter 2)" +
                "\nAdmin (enter 3)");
        while (true) {
            Scanner scn = new Scanner(System.in);
            String input = scn.next();
            switch (input) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                default:
                    System.out.println("Invalid input. Please try again");

            }
        }
    }
    }



