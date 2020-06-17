package service;


import entity.Store;
import entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

import static service.StoreService.printMenu;
import static view.StoreMain.stock;


public class UserShopping {
    private static final Logger logger = LogManager.getLogger(UserShopping.class);

    public static void shopping(User user) {
        printMenu();

        shoppingProcess(user);

        if (user.getBasket().getShoppingBasket().size() > 0) {
            user.getBasket().printBasket();
            while (true) {
                Scanner scn = new Scanner(System.in);
                System.out.println("Do you confirm your purchase (Y or N)?");
                String ans = scn.next();
                if (ans.toLowerCase().equals("y")) {
                    user.getBasket().totalPrice();

                    for (Store purchase :
                            user.getBasket().getShoppingBasket().keySet()) {
                        Iterator<Store> stockIterator = stock.iterator();

                        while (stockIterator.hasNext()) {
                            Store goods = stockIterator.next();
                            if (Objects.equals(purchase, goods)) {
                                StoreService.update(goods, user.getBasket().getShoppingBasket().get(purchase));
                            }
                        }
                    }
                    makePurchaseLog(user);

                    user.getBasket().cleanBasket();
                    System.out.println("Hope you are happy with your purchase.");



                    break;
                } else if (ans.toLowerCase().equals("n")) {
                    shoppingProcess(user);
                    break;
                }
                System.out.println("Invalid input!");
            }
        }
    }

    private static void makePurchaseLog(User user) {
        List<Integer> purchasesID = new ArrayList<>();

        for (Store purchase:
                user.getBasket().getShoppingBasket().keySet()) {
            purchasesID.add(purchase.getId());
        }

        String massage = " made a purchase in  " ;

        for (int i=0; i<purchasesID.size() ; i++){
            massage = massage +  ", id= " +  purchasesID.get(i) ;
        }
        UserService.customerLogging(user.getUserName(), massage);

        logger.info(user.getUserName() + massage );
        logger.debug(user.getUserName() + massage );
    }

    public static void shoppingProcess(User user) {
        Scanner scn = new Scanner(System.in);

        while (true) {

            System.out.println("What do you choose for shopping?" +
                    "\n If your shopping is finished, please enter 0 " +
                    "\n If you want to delete your  previous purchase, please enter \'d\'" +
                    "\n If you want to see market stock, please enter \'m\'" +
                    "\n Otherwise, Please enter the name of goods that you want:");
            String choice = scn.next();

            if (choice.equals("0")) {
                break;
            }
            if (choice.equals("m")) {
                printMenu();
            } else if (choice.equals("d")) {
                user.getBasket().printBasket();
                System.out.println("which one you want to delete?");
                String deleteOne = scn.next();
                for (Store goods : stock) {
                    if (goods.getName().equals(deleteOne)) {
                        user.getBasket().deleteFromBasket(goods);
                        break;
                    }
                }
            }

            boolean flag = true;
            Store purchase = null;
            int count = 0;

            for (Store goods : stock) {
                if (goods.getName().equals(choice)) {
                    purchase = goods;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println("This goods is not exist in maktab_dg_store, Please try again.");
                continue;
            }
            do {
                System.out.println("How many do you want?");
                count = scn.nextInt();
                if (count > purchase.getCount()) {
                    System.out.println("There is only " + purchase.getCount() + " of " + purchase.getName());
                } else {
                    break;
                }
            } while (true);
            user.getBasket().addToBasket(purchase, count);
        }
    }


}
