import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

@XmlRootElement
class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean banned;
    private boolean provider;
    private boolean admin;

    //constructor
    User(){
        username = null;
        password = null;
        firstName = null;
        lastName = null;
        email = null;
        banned = false;
        provider = false;
        admin = false;
    }

    User(String username, String password, String firstName, String lastName, String email, Boolean provider, Boolean admin){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.banned = false;
        this.provider = provider;
        this.admin = admin;
        }

    User(String username, String password, String firstName, String lastName, String email){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.banned = false;
        this.provider = false;
        this.admin = false;
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
    boolean getBanned(){
        return banned;
    }
    void setbanned(String banned) {
        this.banned = Boolean.parseBoolean(banned);
    }

    @XmlElement
    boolean getProvider(){return provider;}
    void setProvider(String provider){
        this.provider = Boolean.parseBoolean(provider);
    }

    @XmlElement
    boolean getAdmin(){return admin;}
    void setAdmin(String admin){this.admin = Boolean.parseBoolean(admin);}

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

    @Override
    public String toString() {
        return firstName + lastName;
    }

    //imports profiles from into an array list from an xml file name profiles.xml

    static UserList importAccounts() {
        File file = new File("User\\profiles.xml");
        UserList userAccounts = new UserList();

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(UserList.class);
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            userAccounts = (UserList)jaxbUnMarshaller.unmarshal(file);
        }catch (JAXBException e){
            e.printStackTrace();
        }
        return userAccounts;
    }

    static void exportAccount( UserList profiles){
        File file = new File("User\\profiles.xml");

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(UserList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(profiles, file);
        }catch (JAXBException e){
            System.out.println(e);
        }
    }
}