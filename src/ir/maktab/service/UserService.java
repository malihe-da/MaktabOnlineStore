package ir.maktab.service;


import ir.maktab.model.dao.OperationLogDao;
import ir.maktab.model.dao.UserDao;
import ir.maktab.model.entity.OperationLog;
import ir.maktab.model.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;


public class UserService {


    private static final Logger logger = LogManager.getLogger(UserService.class);

    UserDao userDao;
    OperationLogDao operationLogDao;


    public void newUser(User user) {
        userDao.insertToUser(user);

        String massage = " registered ";
        //customerLogging(user.getUserName(), massage);

     //   logger.debug(user.getUserName() + massage);
        logger.info(user.getUserName() + massage);
    }

    public boolean isValidPassword(String password) {
        if (password.length() < 4)
            return false;
        boolean containDigit = false;
        boolean containAlpha = false;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i)))
                containDigit = true;
            if (Character.isAlphabetic(password.charAt(i)))
                containAlpha = true;
            if (containAlpha && containDigit)
                return true;
        }
        return false;
    }

    public boolean isValidMobile(String mobile) {

        if (mobile.length() == 11) {
            for (int i = 0; i < 11; i++) {
                if (!Character.isDigit(mobile.charAt(i)))
                    return false;
            }
            return true;
        }
        return false;
    }

    public boolean isValidAge(String age) {
        if (age.length() < 3) {
            for (int i = 0; i < age.length(); i++) {
                if (!Character.isDigit(age.charAt(i)))
                    return false;
            }
            return true;
        }
        return false;
    }


    public boolean isValidMailAddress(String mail) {
        if (mail.length() > 7) {
            if (!mail.contains("@")) {
                return false;
            }
            if (!mail.endsWith(".com")) {
                return false;
            }

            return true;
        }
        return false;
    }

    public boolean isValidZipcode(String zipcode) {
        if (zipcode.length() == 10) {
            for (int i = 0; i < 10; i++) {
                if (!Character.isDigit(zipcode.charAt(i)))
                    return false;
            }
            return true;
        }
        return false;
    }


    public void customerLogging(String userName, String massage) {
        OperationLog newLog = OperationLog.OperationLogBuilder.aLog()
                .withAuthority(userName).withOperation(massage)
                .withDate(new Date()).withTime(Time.valueOf(LocalTime.now())).build();


        operationLogDao.insertIntoOperationLog(newLog);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setOperationLogDao(OperationLogDao operationLogDao) {
        this.operationLogDao = operationLogDao;
    }
}

