package ir.maktab.service;

import ir.maktab.model.dao.StoreDao;
import ir.maktab.model.entity.Store;
import ir.maktab.view.StoreMain;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

import static ir.maktab.view.StoreMain.stock;

public class StoreService {


    Store newStock;
    StoreDao storeDao;

    Scanner scanner = new Scanner(System.in);

    public void fillTable() {

        fillLoop:
        while (true) {
            System.out.println("For add new stock please enter \'a\'," +
                    "for exit this process enter \'e\':");
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

    private void newStockEntered(Scanner scanner) {
        System.out.println("name:");
        newStock.setName(scanner.next());
        System.out.println("type:");
        newStock.setType(scanner.next());
        System.out.println("price:");
        newStock.setPrice(scanner.nextDouble());
        System.out.println("count:");
        newStock.setCount(scanner.nextInt());
        stock.add(newStock);
        storeDao.insertIntoStore(newStock);
    }


    public void updateAndDelete() {
        updateLoop:
        while (true) {
            System.out.println("For update any stock please enter \'u\'," +
                    "for delete one of them enter \'d\':" +
                    "for exit this process enter \'e\':");

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
                        storeDao.deleteStoreStock(selectedStock);
                    } else {
                        System.out.println("There is no such stock");
                    }
                    break;
                case "e":
                    break updateLoop;
            }

        }
    }

    private void updateControl(Scanner scanner) {
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
            storeDao.adminStockUpdate(selectedStock.getName(), price, count);
        } else {
            System.out.println("There is no such stock");
        }
    }


    public void update(Store purchase, int count) {
        String name = purchase.getName();
        if (purchase.getCount() == count) {
            storeDao.deleteStoreStock(purchase);
        } else {
            int number = purchase.getCount() - count;
            purchase.setCount(number);

            storeDao.purchaseUpdate(name, number);
        }
    }

    public void printMenu() {
        System.out.println("Goods     Type       Price         count");
        System.out.println("_____________________________________________");
        for (Store goods : stock) {
            System.out.println(goods.getName() + "---------" + goods.getType() + "---------" +
                    goods.getPrice() + "---------" + goods.getCount());
        }

    }

    public void setNewStock(Store newStock) {
        this.newStock = newStock;
    }

    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }
}
