import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;


// Created as a merging of code from Evan Crabtree and Geoffrey Blech's User classes. for original code see UserDep.java

public class UserRep {
    private String username;
    @XmlElement(name="username")
    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }

    private String password;
    @XmlElement(name="password")
    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }

    private String firstName;
    @XmlElement(name="firstName")
    public String getFirstName() { return this.firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    private String lastName;
    @XmlElement(name="lastName")
    public String getLastName() { return this.lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    private String email;
    @XmlElement(name="email")
    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    private Boolean banned;
    @XmlElement(name="banned")
    public Boolean getBanned() { return this.banned; }
    public void setBanned(boolean banned) { this.banned = banned; }

    private Boolean provider;
    @XmlElement(name="provider")
    public Boolean getProvider() { return this.provider; }
    public void setProvider(Boolean provider) { this.provider = provider; }

    private Boolean admin;
    @XmlElement(name="admin")
    public Boolean getAdmin() { return this.admin; }
    // This should never be used
    public void setAdmin(Boolean admin) { this.admin = admin; }



    UserRep(String username, String password, String firstName, String lastName, String email, Boolean provider, Boolean banned){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.banned = banned;
        this.provider = provider;
        this.admin = false;
    }
}

@XmlRootElement(name="data")
@XmlAccessorType(XmlAccessType.FIELD)
class Data {
    // @XmlElement(name="userCount")
    // int count;

    @XmlElementWrapper(name="users")
    @XmlElement(name="user")
    ArrayList<User> users;
}

class UserStore {
    private Data data;

    UserStore() throws JAXBException {
        // Load users.xml
        JAXBContext jc = JAXBContext.newInstance(Data.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        this.data = (Data) unmarshaller.unmarshal(new File("users.xml"));

        System.out.printf("Number of users: %d\n", this.data.users.size());
    }

    void exportAccount() throws JAXBException{
        //exports data
        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(this.data, new File("profiles.xml"));
    }

    // Check if the given username exists
    public boolean containsUsername(String username) {
        for (int i = 0; i < this.data.users.size(); i++) {
            if (this.data.users.get(i).getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    // Verify the given password is correct. containsUsername() should have already been run
    public boolean verifyPassword(String username, String password) {
        for (int i = 0; i < this.data.users.size(); i++) {
            if (this.data.users.get(i).getUsername().equals(username)) {
                if (this.data.users.get(i).getPassword().equals(password)) {
                    return true;
                }
            }
        }

        return false;
    }
}


