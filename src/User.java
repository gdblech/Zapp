import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;


public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean banned;
    private boolean provider;
    private boolean admin;
    private ArrayList<Comments> reviews;

    User(){}
    User(String username, String password, String firstName, String lastName, String email, Boolean provider, Boolean banned){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.banned = banned;
        this.provider = provider;
        this.admin = false;
        this.reviews = null;
    }

    @XmlElement(name="username")
    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }

    @XmlElement(name="password")
    public String getPassword() { return this.password; }

    public void setPassword(String password) { this.password = password; }

    @XmlElement(name="firstName")
    public String getFirstName() { return this.firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    @XmlElement(name="lastName")
    public String getLastName() { return this.lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    @XmlElement(name="email")
    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    @XmlElement(name="banned")
    public boolean getBanned() { return this.banned; }

    public void setBanned(boolean banned) { this.banned = banned; }

    @XmlElement(name="provider")
    public boolean getProvider() { return this.provider; }

    public void setProvider(boolean provider) { this.provider = provider; }

    @XmlElement(name="admin")
    public boolean getAdmin() { return this.admin; }
    void setAdmin(boolean admin){
        this.admin = admin;
    }

    @XmlElement(name = "reviews")
    public ArrayList<Comments> getReviews(){
        return this.reviews;
    }
    public void setReview(ArrayList<Comments> reviews){
        this.reviews = reviews;
    }

    public void addReview(String text, String username){
        if(this.reviews == null){
            this.reviews = new ArrayList<Comments>();
        }
        this.reviews.add(new Comments(text, username));
    }
}
