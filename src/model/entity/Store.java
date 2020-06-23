package model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column()
    private String name;
    @Column()
    private String type;
    @Column()
    private double price;
    @Column
    private int count;

    public Store() {
    }

    public Store(String name, String type, double price, int count) {
            this.name = name;
            this.type=type;
            this.price = price;
            this.count=count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "name= " + name + '\t' +
                " type= " + type + '\t' +
                " price= " + price + '\t' +
                " count= " + count;
    }

    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Store store = (Store) o;
            return Double.compare(store.price, price) == 0 &&
                    Objects.equals(name, store.name) &&
                    Objects.equals(type, store.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, type, price);
        }
    }


