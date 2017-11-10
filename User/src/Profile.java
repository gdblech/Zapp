import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

@XmlRootElement
class Profile {
    /* private attributes:
    firstName: string
    lastName: string
    location: object ( city, state, country)
    email: string
    reviews: linked list
    username : string
    */
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String state;
    private boolean banned; // false is default

    //constructor
    Profile(){
        username = null;
        password = null;
        firstName = null;
        lastName = null;
        email = null;
        banned = true;
        city = null;
        state = null;
    }
    Profile(String username, String password, String firstName, String lastName,  String email){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        city = "greensboro";
        state = "North Carolina";
        banned = false;
//        reviews = null;

        //todo add reviews
    }

    //getters and setters
    @XmlAttribute
    String getUsername() {
        return username;
    }
    void setUsername(String username) {
        this.username = username;
    }

    @XmlElement
    String getLastName() {
        return lastName;
    }
    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement
    String getFirstName() {
        return firstName;
    }
    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement
    String getEmail() {
        return email;
    }
    void setEmail(String email) {
        this.email = email;
    }

    @XmlElement
    String getPassword() {
        return password;
    }
    void setPassword(String password) {
        this.password = password;
    }

    @XmlElement
    boolean getBan(){
        return banned;
    }
    void setban(boolean banned) {
        this.banned = banned;
    }

    @XmlElement
    String getCity(){
        return city;
    }
    void setCity(String city){
        this.city = city;
    }

    @XmlElement
    String getState(){
        return state;
    }
    void setState(String state){
        this.state = state;
    }

//    @XmlElement
//    ReviewList getReviews(){
//        return reviews;
//    }
//    public void setReviews(ReviewList reviews) {
//        this.reviews = reviews;
//    }

    //returns the users ban status
    boolean isBanned(){
        return banned;
    }
    //check to see if the string matches the profiles password
    boolean passwordCheck(String pass){
        return password.equals(pass);
    }
    //checks if string matches the profiles username
    boolean usernameCheck(String user){
        return username.equalsIgnoreCase(user);
    }
    //adds a review to list
//    void addReview(Review review){
//        reviews.add(review);
//    }

    @Override
    public String toString() {
        return firstName + lastName;
    }

    //imports profiles from into an array list from an xml file name profiles.xml
    static ProfileHash importAccounts() {
        File file = new File("User\\profiles.xml");
        ProfileHash userAccounts = new ProfileHash();

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(ProfileHash.class);
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            userAccounts = (ProfileHash)jaxbUnMarshaller.unmarshal(file);
        }catch (JAXBException e){
            e.printStackTrace();
            }
        System.out.println(userAccounts.toString());
        return userAccounts;
    }

    //exports an array list of profiles to an xml file named profiles.xml
    static void exportAccount( ProfileHash profiles){
        File file = new File("User\\profiles.xml");

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProfileHash.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(profiles, file);
        }catch (JAXBException e){
            System.out.println(e);
        }
        System.out.println(profiles.toString());
    }
}