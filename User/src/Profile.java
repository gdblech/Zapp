import jdk.nashorn.internal.ir.ReturnNode;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
    private Location location;
    private boolean ban; // false is default

    //constructor
    Profile(String uname, String pass, String fName, String lName,  String eml){
        username = uname;
        password = pass;
        firstName = fName;
        lastName = lName;
        email = eml;
        location = new Location("Greensboro", "North Carolina");
        reviews = new ReviewList(uname);
        ban = false;

        //todo add review linked list finder/loader
        //todo add location finder/loader
    }

    //getters
    String getUsername() {
        return username;
    }
    String getLastName() {
        return lastName;
    }
    String getFirstName() {
        return firstName;
    }
    String getEmail() {
        return email;
    }
    String getLocation() {
        return location.toString();
    }
    String getPassword() {
        return password;
    }
    private String getWriterOut(){
        StringBuilder str = new StringBuilder();
        str.append(this.username);
        str.append(" ");
        str.append(this.password);
        str.append(" ");
        str.append(this.firstName);
        str.append(" ");
        str.append(this.lastName);
        str.append(" ");
        str.append(this.email);
        str.append("\n");
        return str.toString();
    }

    //setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return firstName + lastName;
    }

    static ArrayList readAccounts() {
        ArrayList<Profile> userAccounts = new ArrayList<>();
        String fileName = "User\\profiles.txt";
        try {
            FileReader fin = new FileReader(fileName);
            Scanner accountFile = new Scanner(fin);

            String line;
            while (accountFile.hasNextLine()) {
                line = accountFile.nextLine();
                StringTokenizer st = new StringTokenizer(line);

                try {
                    if (st.countTokens() != 5) {
                        System.err.println("Skipping ill-formatted line " + line);
                        continue;
                    }
                    String user = st.nextToken();
                    String password = st.nextToken();
                    String firstName = st.nextToken();
                    String lastName = st.nextToken();
                    String email = st.nextToken();

                    Profile pfile = new Profile(user, password, firstName, lastName, email);
                    userAccounts.add(pfile);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping ill-formatted line " + line);
                }
            }
        }catch (IOException e) {
            System.err.println(e);
            return null;
        }
        return userAccounts;
    }
    static int writeAccounts(ArrayList<Profile>  prof){
        try{
            FileWriter fout = new FileWriter("User\\profiles.txt");
            Iterator<Profile> iter = prof.iterator();
            while(iter.hasNext()){
                Profile pro = iter.next();
                fout.write(pro.getWriterOut());
            }
            fout.flush();
            fout.close();
            return 0;
        }catch(IOException e) {
            System.err.println(e);
            return 1;
        }

    }
}


class Location{
    private String city;
    private String state;

    Location(String city, String state){
        this.city = city;
        this.state = state;
    }

    //getters
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }

    //setters
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return city + ", " + state;
    }
}