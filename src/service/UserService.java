package service;


import entity.OperationLog;
import entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

import static view.StoreMain.*;


public class UserService {


    private static final Logger logger = LogManager.getLogger(UserService.class);

    public static void userLogIn(int key) {
        User user;
        if (key == 1) {
            user = register();
        } else {
            user = oldUserLogIn();
        }

        UserShopping.shopping(user);

    }

    public static User register(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter your user name:");
        String userName = scn.nextLine();
        System.out.println("Please enter password: ");
        String passWord = scn.nextLine();

        User user = new User(userName, passWord);

        System.out.println("We are very happy that you have chosen our store to buy!");
        System.out.println(user.getUserName() + " please enter your name: ");
        user.setName(scn.nextLine());
        System.out.println("Please enter your family: ");
        user.setFamily(scn.next());
        System.out.println("Please enter your age: ");
        user.setAge(scn.nextInt());
        System.out.println("Please enter your mobil number:");
        user.setPhone(scn.next());
        System.out.println("Please enter your email address:");
        user.seteMail(scn.next());

        user.setAddress(AddressService.getAddress());

        insertToUser(user);

        String massage = " registered ";
        customerLogging(userName, massage);

        logger.debug(userName + massage );
        logger.info(userName + massage );
        return user;
    }

    public static void customerLogging(String userName, String massage) {
        OperationLog newLog = OperationLog.OperationLogBuilder.aLog()
                .withAuthority(userName).withOperation(massage)
                .withDate(new Date()).withTime(Time.valueOf(LocalTime.now())).build();


        insertIntoOperationLog(newLog);
    }

    private static void insertIntoOperationLog(OperationLog newLog) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(newLog);

        transaction.commit();
        session.close();

    }

    public static User oldUserLogIn() {

        if (userList.isEmpty()) {
            User user = register();
            return user;
        }

        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter your user name:");
        String userName = scn.nextLine();
        System.out.println("Please enter password: ");
        String passWord = scn.nextLine();

        User user = new User(userName, passWord);

        for (User us :
                userList) {
            if (us.getUserName().equals(userName)) {
                if (us.getPassword().equals(passWord)) {
                    return user;
                }
            }
        }
        System.out.println("You are not registered before. Sign up now please! ");

        user = register();
        return user;
    }
    public static void insertToUser(User user){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);
        transaction.commit();

        session.close();
        userList.add(user);
    }

}

