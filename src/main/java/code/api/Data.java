package code.api;

class Data {
    String city;
    String name;
    UserRating user_rating;
    int id;

    @Override
    public String toString() {
        return "Data{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", user_rating=" + user_rating +
                ", id=" + id +
                '}';
    }
}
