package ir.maktab.view;

import ir.maktab.model.dao.AdminDao;
import ir.maktab.model.dao.StoreDao;
import ir.maktab.model.dao.UserDao;
import ir.maktab.model.entity.Admin;
import ir.maktab.model.entity.OperationLog;
import ir.maktab.model.entity.Store;
import ir.maktab.model.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StoreMain {

    public static List<Store> stock = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();
    public static List<Admin> admins = new ArrayList<>();
    public static List<OperationLog> logList = new ArrayList<>();

    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanConfig.xml");

    public static void main(String[] args) {
        fillApplicationList();

        UserView userView =(UserView) applicationContext.getBean("userView");
        AdminView adminView = (AdminView) applicationContext.getBean("adminView");

        int inputKey = welcome();

        switch (inputKey) {
            case 1:
                userView.userLogIn(1);
                break;
            case 2:
                userView.userLogIn(2);
                break;
            case 3:
                adminView.checkAdmin();
                break;
        }

    }

    private static void fillApplicationList() {

        StoreDao storeDao = (StoreDao) applicationContext.getBean("storeDao");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        AdminDao adminDao = (AdminDao) applicationContext.getBean("adminDao");
        storeDao.readStoreFromDataBase();
        userDao.readUsersFromDataBase();
        adminDao.readAdminsFromDataBase();
    }


    private static int welcome() {
        System.out.println("\n\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n"+
                "Welcome to online store! " +
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



