package code.api;

class UserRating {
    double average_rating;
    int votes;

    @Override
    public String toString() {
        return "UserRating{" +
                "average_rating=" + average_rating +
                ", votes=" + votes +
                '}';
    }
}

