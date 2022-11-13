package code.api;

public class Data {
    int id;
    String name;
    String username;
    String email;
    Address address;
    String website;
    Company company;

    public Data(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }
}

class Company {
    String name;
    String basename;
}

class Geo {
    String lat;
    String lng;
}