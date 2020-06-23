package service;

import model.entity.Store;

import java.util.Scanner;

import static model.dao.StoreDao.*;
import static view.StoreMain.stock;

public class StoreService {

    public static void fillTable() {

        fillLoop:
        while (true) {
            System.out.println("For add new stock please enter \'a\'," +
                    "for exit this process enter \'e\':");
            Scanner scanner = new Scanner(System.in);
            String entered = scanner.next();
            switch (entered) {
                case "a":
                    newStockEntered(scanner);
                    break;
                case "e":
                    break fillLoop;
            }
        }
    }

    private static void newStockEntered(Scanner scanner) {
        System.out.println("name:");
        String name = scanner.next();
        System.out.println("type:");
        String type = scanner.next();
        System.out.println("price:");
        double price = scanner.nextDouble();
        System.out.println("count:");
        int count = scanner.nextInt();
        Store newStock = new Store(name, type, price, count);
        stock.add(newStock);
        insertIntoStore(newStock);
    }


    public static void updateAndDelete() {
        updateLoop:
        while (true) {
            System.out.println("For update any stock please enter \'u\'," +
                    "for delete one of them enter \'d\':" +
                    "for exit this process enter \'e\':");
            Scanner scanner = new Scanner(System.in);
            String entered = scanner.next();
            switch (entered) {
                case "u":
                    updateControl(scanner);
                    break;
                case "d":
                    System.out.println("The produce name:");
                    String name = scanner.next();
                    Store selectedStock = null;
                    for (Store st : stock) {
                        if (st.getName().equals(name)) {
                            selectedStock = st;
                            break;
                        }
                    }
                    if (selectedStock != null) {
                        deleteStoreStock(selectedStock);
                    } else {
                        System.out.println("There is no such stock");
                    }
                    break;
                case "e":
                    break updateLoop;
            }

        }
    }

    private static void updateControl(Scanner scanner) {
        System.out.println("The produce name:");
        String name = scanner.next();
        Store selectedStock = null;
        for (Store st : stock) {
            if (st.getName().equals(name)) {
                selectedStock = st;
                break;
            }
        }
        if (selectedStock != null) {
            stock.remove(selectedStock);
            System.out.println(selectedStock.toString());
            System.out.println("new price:");
            Double price = scanner.nextDouble();
            selectedStock.setPrice(price);
            System.out.println("new count: ");
            int count = scanner.nextInt();
            selectedStock.setCount(count);
            stock.add(selectedStock);
            adminStockUpdate(selectedStock.getName(), price, count);
        } else {
            System.out.println("There is no such stock");
        }
    }



    public static void update(Store purchase, int count) {
        String name = purchase.getName();
        if (purchase.getCount() == count) {
            deleteStoreStock(purchase);
        } else {
            int number = purchase.getCount() - count;
            purchase.setCount(number);

            purchaseUpdate(name, number);
        }
    }

    public static void printMenu() {
        System.out.println("Goods     Type       Price         count");
        System.out.println("_____________________________________________");
        for (Store goods : stock) {
            System.out.println(goods.getName() + "---------" + goods.getType() + "---------" +
                    goods.getPrice() + "---------" + goods.getCount());
        }

    }


}
