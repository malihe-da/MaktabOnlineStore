package entity;

import javax.persistence.*;
import java.util.*;

    @Entity
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(name = "user_name")
        private String userName;
        @Column()
        private String password;
        @Column()
        private String name;
        @Column()
        private String family;
        @Column()
        private int age;
        @Column()
        private String phone;
        @Column()
        private String eMail;
        @OneToOne(cascade = CascadeType.ALL)
        private Address address;
        @Transient
        private Basket basket;

        public User() {
        }

        public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        basket=new Basket();
    }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String geteMail() {
            return eMail;
        }

        public void seteMail(String eMail) {
            this.eMail = eMail;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(family, user.family) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(eMail, user.eMail) &&
                Objects.equals(address, user.address) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, name, family, phone, eMail, address);
    }

    @Override
    public String toString() {
        return "User= " +
                name + family +
                ", age=" + age;
    }
}


