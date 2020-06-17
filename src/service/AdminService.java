package service;

import entity.Admin;
import entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static service.StoreService.*;
import static view.StoreMain.*;

public class AdminService {

    private static final Logger logger = LogManager.getLogger(AdminService.class);

    public static void adminController(){
        Admin admin = checkAdmin();
        if (admin == null)
            return;

        System.out.println("welcome " + admin.getUserName());

        adminGuid();

        call:
        while (true){
            Scanner scn = new Scanner(System.in);
            System.out.print("\n\nNext Order:");
            String answer = scn.next();
            switch (answer){
                case "1":
                    printMenu();
                    break;
                case "2":
                    fillTable();
                    break;
                case "3":
                    updateAndDelete();
                    break;
                case "4":
                    sortUsers(userList);
                    System.out.println("Sort the customers by their age: \n" + userList.toString());
                    break;
                case "5":
                    readOperationLog();
                    System.out.println(logList.toString());
                    break;
                case "6":
                    adminGuid();
                    break;
                case "7":
                    break call;
                default:
                    System.out.println("-------Invalid input, choose the correct way");
                    adminGuid();
            }
        }
    }

    private static Admin checkAdmin() {
        Scanner scn = new Scanner(System.in);
        Admin tryAdmin = null;
        boolean flag = false;
        while (true) {
            System.out.println("Please enter your user name:");
            String userName = scn.nextLine();
            for (Admin admin :
                    admins) {
                if (admin.getUserName().equals(userName)) {
                    tryAdmin=admin;
                    flag = true;
                }
            }
            if (flag==false)
                continue;

            System.out.println(userName + ", please enter your passWord:");
            String passWord = scn.nextLine();
            if(tryAdmin.getPassword().equals(passWord))
                break;
        }
        logger.debug("admin with user name: " + tryAdmin.getUserName()+ " entered");
        logger.info("admin with user name: " + tryAdmin.getUserName()+ " entered");
        return tryAdmin;
    }

    private static void readOperationLog() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql= "from OperationLog";
        Query query = session.createQuery(hql);
        logList=query.getResultList();

        transaction.commit();
        session.close();

    }

    private static void adminGuid() {
        System.out.println("Please choose one of the following operation," +
                "\nShow the store stocks (enter 1)" +
                "\nFilling the store (enter 2)" +
                "\nUpdate the stock (enter 3)" +
                "\nShow the sorted customers by age (enter 4)" +
                "\nShow the last month operation log of system (enter 5)" +
                "\nShow the help (enter 6)" +
                "\n exit(enter 7)");
    }


    public static void sortUsers(List<User> userList) {
        Comparator<User> myComparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() == o2.getAge() ? 0 : o1.getAge() > o2.getAge() ? 1 : -1;
            }
        };
        //Comparator<User> myLambdaComparator = (o1, o2) -> o1.getAge() == o2.getAge() ? 0 : o1.getAge() > o2.getAge() ? 1 : -1;

        userList.sort(myComparator);
    }
}
