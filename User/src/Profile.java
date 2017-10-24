import java.util.LinkedList;

class Profile {
    /* private attributes:
    firstName: string
    lastName: string
    location: object ( city, state, country)
    email: string
    reviews: linked list
    username : string
    */
    String firstName;
    String lastName;
    String account;
    String email;
    LinkedList<Review> reviews;
    Location location;

}

class Location{
    String city;
    String state;
    String country;

}