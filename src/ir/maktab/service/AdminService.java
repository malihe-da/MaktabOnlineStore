package ir.maktab.service;


import ir.maktab.model.dao.OperationLogDao;
import ir.maktab.model.entity.Admin;
import ir.maktab.model.entity.User;
import ir.maktab.view.StoreMain;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static ir.maktab.view.StoreMain.*;

public class AdminService {

    private static final Logger logger = LogManager.getLogger(AdminService.class);


    public  void adminController(Admin admin){


        OperationLogDao operationLogDao= (OperationLogDao) StoreMain.applicationContext.getBean("operationLogDao");
        StoreService storeService = (StoreService) applicationContext.getBean("storeService");

        logger.debug("admin with user name: " + admin.getUserName()+ " entered");
        logger.info("admin with user name: " + admin.getUserName()+ " entered");

        System.out.println("welcome " + admin.getUserName());



        call:
        while (true){
            adminGuid();
            Scanner scn = new Scanner(System.in);
            System.out.print("\n\nNext Order:");
            String answer = scn.next();
            switch (answer){
                case "1":
                    storeService.printMenu();
                    break;
                case "2":
                    storeService.fillTable();
                    break;
                case "3":
                    storeService.updateAndDelete();
                    break;
                case "4":
                    sortUsers(userList);
                    System.out.println("Sort the customers by their age: \n" + userList.toString());
                    break;
                case "5":
                    operationLogDao.readOperationLog();
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




    private  void adminGuid() {
        System.out.println("\n\n-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.\n" +
                "Please choose one of the following operation," +
                "\nShow the store stocks (enter 1)" +
                "\nFilling the store (enter 2)" +
                "\nUpdate the stock (enter 3)" +
                "\nShow the sorted customers by age (enter 4)" +
                "\nShow the last month operation log of system (enter 5)" +
                "\nShow the help (enter 6)" +
                "\n exit(enter 7)");
    }


    public  void sortUsers(List<User> userList) {
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
