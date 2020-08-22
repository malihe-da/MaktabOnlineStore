package ir.maktab.view;

import ir.maktab.model.entity.Address;
import ir.maktab.model.entity.User;
import ir.maktab.service.UserService;
import ir.maktab.service.UserShopping;

import java.util.Scanner;

import static ir.maktab.view.StoreMain.userList;


public class UserView {

    UserService userService;
    UserShopping userShopping;

    Scanner scn = new Scanner(System.in);

    public void userLogIn(int key) {
        User user;
        if (key == 1) {
            user = register();
        } else {
            user = oldUserLogIn();
        }

        userShopping.shopping(user);

    }

    public User register() {

        System.out.println("Please enter your user name:");
        String userName = scn.nextLine();
        String passWord;
        while (true) {
            System.out.println("Please enter password: ");
            String checkOut = scn.nextLine();
            if(userService.isValidPassword(checkOut)){
                passWord=checkOut;

                break;
            }
        }

        User user = new User(userName, passWord);

        System.out.println("We are very happy that you have chosen our store to buy!");
        System.out.println(user.getUserName() + " please enter your name: ");
        user.setName(scn.nextLine());
        System.out.println("Please enter your family: ");
        user.setFamily(scn.nextLine());
        while (true) {
            System.out.println("Please enter your age: ");
            String checkAge=scn.nextLine();
            if(userService.isValidAge(checkAge));
            user.setAge(Integer.parseInt(checkAge));
            break;
        }
        while (true) {
            System.out.println("Please enter your mobil number:  (enter 11 digit number like 09*********)");
            String checkMobile = scn.nextLine();
            if (userService.isValidMobile(checkMobile)) {
                user.setPhone(checkMobile);
                break;
            }
        }
        while (true) {
            System.out.println("Please enter your email address: (It must contains @ and ends with .com)");
            String checkMail = scn.nextLine();
            if (userService.isValidMailAddress(checkMail)) {
                user.seteMail(checkMail);
                break;
            }
        }
        Address address= getAddress();
        user.setAddress(address);

        userService.newUser(user);

        return user;
    }


    public User oldUserLogIn() {
        if (userList.isEmpty()) {
            User user = register();
            return user;
        }

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

    public  Address getAddress(){

        System.out.println("To complete the registration we need your address! ");
        System.out.println("What is your state:");
        String state = scn.next();
        System.out.println("What is your city:");
        String city = scn.next();
        System.out.println("What is your street:");
        String street = scn.next();
        String zipcode;
        while (true) {
            System.out.println("Enter your 10-digit postal code :");
            String check = scn.next();
            if(userService.isValidZipcode(check)) {
                zipcode=check;
                break;
            }
        }

        return Address.AddressBuilder.aAddress().withState(state)
                .withCity(city).withStreet(street)
                .withZipcode(zipcode).build();

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserShopping(UserShopping userShopping) {
        this.userShopping = userShopping;
    }

}
