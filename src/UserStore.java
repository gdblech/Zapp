import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;

@XmlRootElement(name="data")
@XmlAccessorType(XmlAccessType.FIELD)
class Data {
    @XmlElementWrapper(name="users")
    @XmlElement(name="user")
    ArrayList<User> users;

    void add(User user){
        users.add(user);
    }
}

public class UserStore {
    private Data data;

    UserStore() throws JAXBException {
        // Load users.xml
        JAXBContext jc = JAXBContext.newInstance(Data.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        this.data = (Data) unmarshaller.unmarshal(new File("users.xml"));
    }

    void exportAccount() throws JAXBException{
        //exports data
        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(this.data, new File("users.xml"));
    }


    // Check if the given username exists
    public boolean containsUsername(String username) {
        for (User user : this.data.users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }

        return false;
    }

    // Verify the given password is correct. containsUsername() should have already been run
    public boolean verifyPassword(String username, String password) {
        for (User user : this.data.users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Get the current number of users
    public int getNumCustomers() {
        int count = 0;
        for (User user : this.data.users) {
            if (!user.getProvider()) {
                count++;
            }
        }

        return count;
    }

    // Get the current number of banned users
    public int getNumBannedCustomers() {
        int count = 0;
        for (User user : this.data.users) {
            if (!user.getProvider() && user.getBanned()) {
                count++;
            }
        }

        return count;
    }

    // Get the current number of providers
    public int getNumProviders() {
        int count = 0;
        for (User user : this.data.users) {
            if (user.getProvider()) {
                count++;
            }
        }

        return count;
    }

    // Get the current number of providers
    public int getNumBannedProviders() {
        int count = 0;
        for (User user : this.data.users) {
            if (user.getProvider() && user.getBanned()) {
                count++;
            }
        }

        return count;
    }

    // Get an arraylist of customers
    public ArrayList<User> getCustomers() {
        ArrayList<User> users = new ArrayList<>();

        for (User user : this.data.users) {
            if (!user.getProvider()) {
                users.add(user);
            }
        }

        return users;
    }

    //returns an ArrayList of providers
    public ArrayList<User> getProviders(){
        ArrayList<User> providers = new ArrayList<>();

        for(User user : this.data.users){
            if(user.getProvider()){
                providers.add(user);
            }
        }
        return  providers;
    }

    //removes an account
    public void removeAccount(String username, String password) throws JAXBException {
        for (int i = 0;  i < this.data.users.size(); i++){
            if(this.data.users.get(i).getUsername().equalsIgnoreCase(username)){
                if(this.data.users.get(i).getPassword().equals(password)){
                    this.data.users.remove(i);
                    exportAccount();
                }
            }
        }
    }

    // Returns an ArrayList of reviews
    // public ArrayList<Review> getReviews() {
    // }

    // returns a user if present, null if not
    public User getAccount(String username, String password) {
        for (User user : this.data.users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    void add( User user){
        this.data.add(user);
    }

    public boolean getAdmin(String username) {
        boolean status = false;

        for (User user : this.data.users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                status = user.getAdmin();
            }
        }

        return status;
    }

    public boolean getBanned(String username) {
        boolean status = false;

        for (User user : this.data.users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                status = user.getBanned();
            }
        }

        return status;
    }

    public void setBanned(String username) {
        for (User user : this.data.users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.getBanned()) {
                    user.setBanned(false);
                } else {
                    user.setBanned(true);
                }
            }
        }
    }
}
