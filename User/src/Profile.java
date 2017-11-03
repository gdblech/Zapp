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
    private ReviewList reviews;
    private String city;
    private String state;
    private boolean ban; // false is default

    //constructor
    Profile(){
        username = null;
        password = null;
        firstName = null;
        lastName = null;
        email = null;
        ban = true;
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
        //this.reviews = new ReviewList(uname);
        ban = false;

        //todo add review linked list finder/loader
        //todo add location finder/loader
    }
    Profile(String username, String password, String firstName, String lastName,  String email, String city, String state){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.state = state;
        //this.reviews = new ReviewList(uname);
        ban = false;

        //todo add review linked list finder/loader
        //todo add location finder/loader
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
        return ban;
    }
    void setBan(boolean ban) {
        this.ban = ban;
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





    @Override
    public String toString() {
        return firstName + lastName;
    }

    static ProfileList importAccounts() {
        File file = new File("User\\profiles.xml");
        ProfileList userAccounts = new ProfileList();

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(ProfileList.class);
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();

            userAccounts = (ProfileList)jaxbUnMarshaller.unmarshal(file);

        }catch (JAXBException e){
            System.out.println(e);
        }

        return userAccounts;
    }


    //todo add profileHash object to handle the profile creation and such.
    static void exportAccount( ProfileList profiles){
        File file = new File("User\\profiles.xml");

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProfileList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(profiles, file);


        }catch (JAXBException e){
            System.out.println(e);
        }
    }
}