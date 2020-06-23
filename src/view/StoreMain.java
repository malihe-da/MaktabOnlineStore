package view;

import model.dao.StoreDao;
import model.entity.Admin;
import model.entity.OperationLog;
import model.entity.Store;
import model.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.AdminService;
import service.UserService;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.dao.AdminDao.readAdminsFromDataBase;
import static model.dao.StoreDao.readStoreFromDataBase;
import static model.dao.UserDao.readUsersFromDataBase;

public class StoreMain {

    public static List<Store> stock = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();
    public static List<Admin> admins = new ArrayList<>();
    public static List<OperationLog> logList = new ArrayList<>();

    public static void main(String[] args) {
        readStoreFromDataBase();
        readUsersFromDataBase();
        readAdminsFromDataBase();

        UserView userView = new UserView();
        AdminView adminView = new AdminView();

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



