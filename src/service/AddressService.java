package service;

import entity.Address;

import java.util.Scanner;

public class AddressService {

    public static Address getAddress(){
        Scanner scn = new Scanner(System.in);
        System.out.println("To complete the registration we need your address! ");
        System.out.println("What is your state:");
        String state = scn.next();
        System.out.println("What is your city:");
        String city = scn.next();
        System.out.println("What is your street:");
        String street = scn.next();
        System.out.println("Enter your 10-digit postal code :");
        String zipcode = checkZipcode(scn);

        return Address.AddressBuilder.aAddress().withState(state)
                .withCity(city).withStreet(street)
                .withZipcode(zipcode).build();

    }

    private static String checkZipcode(Scanner scn) {

        String zip;

        while (true){
             zip=scn.next();
             if(zip.length()!=10){
                 System.out.println("Your zipcode length is not correct, Try again!");
                 continue;
             }
             for(int i=0; i< zip.length(); i++){
                 int ascii=zip.charAt(i);
                 if(ascii<48 || ascii>59){
                     System.out.println("Please use digit for zipcode, Try again!");
                     continue;
                 }
             }

            break;
        }
        return zip;
    }
}
