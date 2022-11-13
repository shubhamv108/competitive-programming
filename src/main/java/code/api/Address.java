package code.api;

public class Address {
    String street;
    String suite;
    String city;
    String zipcode;
    Geo geo;

    public Address(String city) {
        this.city = city;
    }
}
