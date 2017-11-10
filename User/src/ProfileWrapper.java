import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ProfileWrapper {
    private Profile profile;

    ProfileWrapper(){
        this.profile = null;
    }
    ProfileWrapper(Profile profile){
        this.profile = profile;
    }

    Profile getProfile() {
        return profile;
    }
    String  getFirstName(){
        if(this.profile != null){
            return this.profile.getFirstName();
        }else{
            return "null";
        }

    }
    String  getLastName(){
        if(this.profile != null){
            return this.profile.getLastName();
        }else{
            return "null";
        }

    }
    String  getEmail(){
        if(this.profile != null){
            return this.profile.getEmail();
        }else{
            return "null";
        }

    }
    String  getUsername(){
        if(this.profile != null){
            return this.profile.getUsername();
        }else{
            return "null";
        }
    }
    String getPassword(){
        if(this.profile != null){
            return this.profile.getPassword();
        }else{
            return "null";
        }
    }
    String getCity(){
        if(this.profile != null){
            return this.profile.getCity();
        }else{
            return "null";
        }
    }
    String getState(){
        if(this.profile != null){
            return this.profile.getState();
        }else{
            return "null";
        }
    }



    void setProfile(Profile profile) {
        this.profile = profile;
    }
    void setFirstName(String firstName){
        this.profile.setFirstName(firstName);
    }
    void setLastName(String lastName){
        this.profile.setLastName(lastName);
    }
    void setEmail(String email){
        this.profile.setEmail(email);
    }
    void setUsername(String username){
        this.profile.setUsername(username);
    }
    void setPassword(String password){
        this.profile.setPassword(password);
    }
}

//@XmlRootElement(name ="Users")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(Profile.class)
class ProfileHash extends HashMap<String, Profile>{
    public ProfileHash(){}

    @XmlElement
    public List<Profile> getAccounts(){
        return new ArrayList<>(this.values());
    }
}
