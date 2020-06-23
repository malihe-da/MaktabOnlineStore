package model.entity;

import java.util.*;

public class Basket {
    Map<Store, Integer> shoppingBasket = new HashMap<>(5);


    public void addToBasket(Store goods, int count) {
        for (Store kala :
                shoppingBasket.keySet()) {
            if (kala.equals(goods)) {
                int size = shoppingBasket.get(kala);
                shoppingBasket.put(goods, size + count);
                return;
            }
        }
        if (shoppingBasket.size() == 5) {
            System.out.println("Your Basket is Full. If you want this goods, you may delete your previous purchase.");
            return;
        }

        shoppingBasket.put(goods, count);
    }

    public void deleteFromBasket(Store goods) {
        for (Store kala :
                shoppingBasket.keySet()) {
            if (kala.equals(goods)) {
                int size = shoppingBasket.get(kala);
                if (size > 1) {
                    shoppingBasket.put(goods, size - 1);
                } else {
                    shoppingBasket.remove(goods);
                }
            }

        }
    }

    public void printBasket() {
        if (shoppingBasket.isEmpty()) {
            System.out.println("Your basket is empty!");
            return;
        }


        System.out.println( " shopping basket:");
        System.out.println("\n purchase ---------- unitPrice ---------- count");
        System.out.println("_______________________________________________________");

        List<Store> basket = new ArrayList<>();
        for (Store kala :
                shoppingBasket.keySet()) {
            basket.add(kala);
        }

        sortBasket(basket);

        for (Store kala :
                basket) {
            System.out.println(kala.getName() + " ----------  " + kala.getPrice() + " ----------  " + shoppingBasket.get(kala));
        }

    }

    private void sortBasket(List<Store> basket) {
        Comparator<Store> basketLambdaComparator = (o1, o2) -> o1.getPrice() == o2.getPrice() ? 0 : o1.getPrice() > o2.getPrice() ? 1 : -1;

        basket.sort(basketLambdaComparator);
    }


    public void totalPrice() {
        int total = 0;
        for (Store kala :
                shoppingBasket.keySet()) {
            total += (kala.getPrice()) * shoppingBasket.get(kala);
        }
        System.out.println("Your Total Price is = " + total);
    }

    public void cleanBasket() {
        shoppingBasket.clear();
    }

    public Map<Store, Integer> getShoppingBasket() {
        return shoppingBasket;
    }

}
