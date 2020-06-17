package entity;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column()
    private String state;
    @Column()
    private String city;
    @Column()
    private String street;
    @Column()
    private String zipcode;



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public static class AddressBuilder {
        private String state;
        private String city;
        private String street;
        private String zipcode;

        public static AddressBuilder aAddress(){return new AddressBuilder();}

        public AddressBuilder withState(String state) {
            this.state = state;
            return this;
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder withZipcode(String zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public Address build(){
            Address address = new Address();
            address.setState(state);
            address.setCity(city);
            address.setStreet(street);
            address.setZipcode(zipcode);
            return address;
        }
    }
}
