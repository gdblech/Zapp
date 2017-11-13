import com.sun.org.apache.xpath.internal.operations.Bool;

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
    //getters and setters
    @XmlElement
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

    @XmlAttribute
    Boolean getBanned(){
        return banned;
    }
    void setBanned(boolean banned){
        this.banned = banned;
    }

    @XmlElement
    boolean getProvider(){return provider;}
    void setProvider(boolean provider) {
        this.provider = provider;
    }

    @XmlElement
    boolean getAdmin(){return admin;}
    void setAdmin(boolean admin){
        this.admin = admin;
    }

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
}



/*
package sample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;

class User {
}



    // Change the given username's password
    // public boolean changePassword(String username, String password) {
    //     for (int i = 0; i < this.data.users.size(); i++) {
    //         if (this.data.users.get(i).getUsername().equals(username)) {
    //             this.data.users.get(i).setPassword(password);
    //             return true;
    //         }
    //     }

    //     return false;
    // }

    // TODO: changeEmail()

    // TODO: changeBanned()

    // TODO: changeProvider()

    // TODO: changeAdmin() -- probably not
}

 */