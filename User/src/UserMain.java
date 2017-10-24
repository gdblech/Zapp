import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Iterator;


public class UserMain extends Application{
    private Scene startScene, signUpScene, signInScene, newProfileScene, viewProfileScene, editProfileScene;
    final int WIDTH = 800;
    final int HEIGHT = 500;

    public static void main(String[] args) {
//        ArrayList<Profile> profiles = Profile.readAccounts();
//        profiles.get(0).setFirstName("RickyRick");
//        Profile.writeAccounts(profiles);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Profile> profiles = Profile.readAccounts();
        ProfileWrapper user = new ProfileWrapper();

        //start parts
        //labels
        Label label1 = new Label("Welcome:");
        //buttons
        Button signUp = new Button("Sign Up");
        Button signIn = new Button("Sign In");
        //button actions
        signUp.setOnAction(e -> primaryStage.setScene(signUpScene));
        signIn.setOnAction(e -> primaryStage.setScene(signInScene));

        // editProfile parts
        //Labels
        Label labelEP1 = new Label("Enter your New First Name:");
        Label labelEP2 = new Label("Enter your New Last Name:");
        Label labelEP3 = new Label("Enter your New Email Address:");
        Label labelEP4 = new Label("Enter your New City");
        Label labelEP5 = new Label("Enter your New State");
        //Text Fields
        TextField fNameEP = new TextField(user.getFirstName());
        TextField lNameEP = new TextField(user.getLastName());
        TextField emailEP = new TextField(user.getEmail());
        TextField cityEP = new TextField("Greensboro");
        TextField stateEP = new TextField("North Carolina");
        cityEP.setEditable(false);
        stateEP.setEditable(false);

        //New Profile Parts
        //Labels
        Label labelNP1 = new Label("Enter your First Name:");
        Label labelNP2 = new Label("Enter your Last Name:");
        Label labelNP3 = new Label("Enter your Email Address:");
        Label labelNP4 = new Label("Enter your City");
        Label labelNP5 = new Label("Enter your State");
        //Text Fields
        TextField fNameNP = new TextField();
        TextField lNameNP = new TextField();
        TextField emailNP = new TextField();
        TextField cityNP = new TextField("Greensboro");
        TextField stateNP = new TextField("North Carolina");
        cityNP.setEditable(false);
        stateNP.setEditable(false);

        //Sign Up parts
        //labels
        Label labelSU1 = new Label("Please Enter A User Name:");
        Label labelSU2 = new Label("Please Enter A Password:");
        Label labelSU3 = new Label("Please Re-Enter Password:");
        //Text Fields
        TextField userSU = new TextField();
        PasswordField passSU1 = new PasswordField();
        PasswordField passSU2 = new PasswordField();
        //buttons
        Button backSU = new Button("Back");
        Button enterSU = new Button("Enter");
        //buttonActions
        backSU.setOnAction(e -> primaryStage.setScene(startScene));
        enterSU.setOnAction(e-> {
            //todo replace with try block

            primaryStage.setScene(newProfileScene);
        });

        //Sign in parts
        //labels
        Label labelSI1 = new Label("Enter Username:");
        Label labelSI2 = new Label("Enter Password:");
        //TextFields
        TextField userSI = new TextField();
        PasswordField passSI = new PasswordField();
        //buttons
        Button backSI = new Button("Back");
        Button enterSI = new Button("Enter");
        //button events
        backSI.setOnAction(e -> primaryStage.setScene(startScene));
        enterSI.setOnAction(e-> primaryStage.setScene(viewProfileScene));

        //Profile View Parts
        //Labels
        Label labelVP1 = new Label("Welcome, " + user.getUsername());
        Label labelVP2 = new Label("Your First Name: " + user.getFirstName());
        Label labelVP3 = new Label("Your Last Name: "+ user.getLastName());
        Label labelVP4 = new Label("Your Email Address: " + user.getEmail());
        Label labelVP5 = new Label("Location: " + user.getLocation());
        //Buttons
        Button editVP = new Button("Edit");
        Button searchVP = new Button("Search");
        //Button Events
        editVP.setOnAction(e -> {
            fNameEP.setText(user.getFirstName());
            lNameEP.setText(user.getLastName());
            emailEP.setText(user.getEmail());
            cityEP.setText("Greensboro");
            stateEP.setText("North Carolina");
            primaryStage.setScene(editProfileScene);
        });

        // editProfile parts2
        //Buttons
        Button enterNP = new Button("Enter");
        //Button Events
        enterNP.setOnAction(e -> {
            Profile nUser = createProfile(userSU.getText(), passSU1.getText(), fNameNP.getText(), lNameNP.getText(), emailNP.getText());
            if(nUser != null){
                profiles.add(nUser);
                user.setProfile(nUser);
                labelVP1.setText("Welcome, " + user.getUsername());
                labelVP2.setText("Your First Name: " + user.getFirstName());
                labelVP3.setText("Your Last Name: "+ user.getLastName());
                labelVP4.setText("Your Email Address: " + user.getEmail());
                labelVP5.setText("Location: " + user.getLocation());
            }else{

            }
            primaryStage.setScene(viewProfileScene);
        });



        //Buttons
        Button enterEP = new Button("Enter");
        //Button Events
        enterEP.setOnAction(e -> {
            editProfile(fNameEP.getText(), lNameEP.getText(), emailEP.getText(), user.getProfile());
            labelVP1.setText("Welcome, " + user.getUsername());
            labelVP2.setText("Your First Name: " + user.getFirstName());
            labelVP3.setText("Your Last Name: "+ user.getLastName());
            labelVP4.setText("Your Email Address: " + user.getEmail());
            labelVP5.setText("Location: " + user.getLocation());
            primaryStage.setScene(viewProfileScene);
        });

        //populates the startScene
        HBox layout = new HBox(100);
        layout.getChildren().addAll(label1, signUp, signIn);
        startScene = new Scene(layout, WIDTH, HEIGHT);

        //populates the signUpScene
        VBox layoutSU = new VBox(20);
        layoutSU.getChildren().addAll(labelSU1, userSU, labelSU2, passSU1, labelSU3, passSU2, enterSU, backSU);
        signUpScene = new Scene(layoutSU, WIDTH, HEIGHT);

        //populates the signInScene
        VBox layoutSI = new VBox(20);
        layoutSI.getChildren().addAll(labelSI1, userSI, labelSI2, passSI, enterSI, backSI);
        signInScene = new Scene(layoutSI, WIDTH, HEIGHT);

        //populates the newProfileScene
        VBox layoutNP = new VBox(20);
        layoutNP.getChildren().addAll(labelNP1, fNameNP, labelNP2, lNameNP, labelNP3, emailNP, labelNP4, cityNP, labelNP5, stateNP, enterNP);
        newProfileScene = new Scene(layoutNP, WIDTH, HEIGHT);

        //populates the viewProfileScene
        VBox layoutVP = new VBox(20);
        layoutVP.getChildren().addAll(labelVP1, labelVP2, labelVP3, labelVP4, labelVP5, editVP, searchVP);
        viewProfileScene = new Scene(layoutVP, WIDTH, HEIGHT);

        //populates the editProfileScene
        VBox layoutEP = new VBox(20);
        layoutEP.getChildren().addAll(labelEP1, fNameEP, labelEP2, lNameEP, labelEP3, emailEP, labelEP4, cityEP, labelEP5, stateEP, enterEP);
        editProfileScene = new Scene(layoutEP, WIDTH, HEIGHT);



        //primary
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Zapp: Fast Electrician Finder");
        primaryStage.setOnCloseRequest(event -> {
            shutDown(profiles);
            System.exit(0);
        });
        primaryStage.show();
    }
    //set user

    //checks for matching password
    private boolean checkPassword(String password1, String password2){
        if(password1.equals(password2)){
            return true;
        }else{
            return false;
        }
    }
    //checks if email is correct format
    private boolean checkEmail(String email){
        //todo add regex for email checking
        return true;
    }
    //checks if usernames match
    private boolean checkUsername(String username1, String username2){
        if(username1.equalsIgnoreCase(username2)){
            return true;
        }else{
            return false;
        }
    }

    //checks if an account exists
    private Profile checkAccount(String username, String password, ArrayList<Profile> profiles){
        //todo add try, catch & exception block.
        Iterator<Profile> iter = profiles.iterator();
        while(iter.hasNext()){
            Profile prof = iter.next();
            if(checkUsername(prof.getUsername(), username) && checkPassword(prof.getPassword(), password)){
                return prof;
            }
        }
        return null;
    }
    //changes profile
    private void editProfile(String firstName, String lastName, String email, Profile profile){
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setEmail(email);
    }
    //creates a new profile
    private Profile createProfile(String username, String password, String firstName, String lastName, String email){
        Profile profile = new Profile(username, password, firstName, lastName, email);
        return profile;
    }
    //prepsfor shutdown by saving user profiles
    private void shutDown(ArrayList<Profile> profiles){
        Profile.writeAccounts(profiles);
    }

}

