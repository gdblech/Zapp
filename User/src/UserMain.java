import javafx.application.Application;
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
    private Scene startScene, signUpScene, signInScene, newProfileScene, viewProfileScene, editProfileScene, reviewsScene;
    final int WIDTH = 800;
    final int HEIGHT = 500;

    public static void Usermain(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Profile> profiles = Profile.readAccounts();
        ProfileWrapper user = new ProfileWrapper();

        //labels
        Label[] labelsST, labelsSU, labelsSI, labelsVP, labelsEP, labelsNP, labelsRS;
        //start stage labels
        labelsST = labelFillST();
        //Sign up stage labels
        labelsSU = labelFillSU();
        //Sign in labels
        labelsSI = labelFillSI();
        //View profile Labels
        labelsVP = labelFillVP();
        // Edit profile Labels
        labelsEP = labelFillEP();
        //New Profile Labels
        labelsNP = labelFillNP();
        //Review scene labels
        labelsRS = labelFillRS();
        Label labelRS1 = new Label("Rating: ");
        Label labelRS2 = new Label();

        //TextFields
        //todo field arrays
        TextField[] textFieldsSU, textFieldsSI, textFieldsEP, textFieldsNP;
        PasswordField passSU, passSU1,  passSI;
        //New Profile TextFields
        textFieldsNP = textFieldsFillNP();
        //sign up TextFields
        TextField userSU = new TextField();
        passSU = new PasswordField();
        passSU1 = new PasswordField();
        //sign in TextFields
        TextField userSI = new TextField();
        passSI = new PasswordField();
        //Edit profile TextFields
        textFieldsEP = textFieldFillEP(user);


        //start parts
        //buttons
        Button signUp = new Button("Sign Up");
        Button signIn = new Button("Sign In");
        //button actions
        signUp.setOnAction(e -> primaryStage.setScene(signUpScene));
        signIn.setOnAction(e -> primaryStage.setScene(signInScene));

        // editProfile parts


        //New Profile Parts

        //Sign Up parts
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
        //buttons
        Button backSI = new Button("Back");
        Button enterSI = new Button("Enter");
        //button events
        backSI.setOnAction(e -> primaryStage.setScene(startScene));
        enterSI.setOnAction(e-> {
            user.setProfile(checkAccount(userSI.getText(), passSI.getText(), profiles));
            if(user.getProfile() == null){
            }else{
                UpdateVP(labelsVP, user.getProfile());
                primaryStage.setScene(viewProfileScene);
            }

        });

        //Profile View Parts
        //Buttons
        Button editVP = new Button("Edit");
        Button searchVP = new Button("Search");
        //Button Events
        editVP.setOnAction(e -> {
            UpdateEP(textFieldsEP, user.getProfile());
            primaryStage.setScene(editProfileScene);
        });
        searchVP.setOnAction(e -> primaryStage.setScene(reviewsScene));

        //Buttons
        Button enterNP = new Button("Enter");
        //Button Events
        enterNP.setOnAction(e -> {
            Profile nUser = createProfile(userSU.getText(), passSU1.getText(), textFieldsNP[0].getText(),
                    textFieldsNP[1].getText(), textFieldsNP[2].getText());

            if(nUser != null){
                profiles.add(nUser);
                user.setProfile(nUser);
                UpdateVP(labelsVP, nUser);
                primaryStage.setScene(viewProfileScene);
            }
        });

        //Buttons
        Button enterEP = new Button("Enter");
        //Button Events
        enterEP.setOnAction(e -> {
            editProfile(textFieldsEP[0].getText(), textFieldsEP[1].getText(), textFieldsEP[2].getText(), user.getProfile());
            UpdateVP(labelsVP, user.getProfile());
            primaryStage.setScene(viewProfileScene);
        });

        //populates the startScene
        HBox layout = new HBox(100);
        layout.getChildren().addAll(labelsST[0], signUp, signIn);
        startScene = new Scene(layout, WIDTH, HEIGHT);

        //populates the signUpScene
        VBox layoutSU = new VBox(20);
        layoutSU.getChildren().addAll(labelsSU[0], userSU, labelsSU[1], passSU, labelsSU[2], passSU1, enterSU, backSU);
        signUpScene = new Scene(layoutSU, WIDTH, HEIGHT);

        //populates the signInScene
        VBox layoutSI = new VBox(20);
        layoutSI.getChildren().addAll(labelsSI[0], userSI, labelsSI[1], passSI, enterSI, backSI);
        signInScene = new Scene(layoutSI, WIDTH, HEIGHT);

        //populates the newProfileScene
        VBox layoutNP = new VBox(20);
        for(int i = 0; i < labelsNP.length; i++){
            layoutNP.getChildren().addAll(labelsNP[i], textFieldsNP[i]);
        }
        layoutNP.getChildren().add(enterNP);
        newProfileScene = new Scene(layoutNP, WIDTH, HEIGHT);

        //populates the viewProfileScene
        VBox layoutVP = new VBox(20);
        for(int i = 0; i < labelsVP.length; i++ ){
            layoutVP.getChildren().add(labelsVP[i]);
        }
        layoutVP.getChildren().addAll(editVP, searchVP);
        viewProfileScene = new Scene(layoutVP, WIDTH, HEIGHT);

        //populates the editProfileScene
        VBox layoutEP = new VBox(20);
        for(int i = 0; i < labelsEP.length; i++){
            layoutEP.getChildren().addAll(labelsEP[i], textFieldsEP[i]);
        }
        layoutEP.getChildren().add(enterEP);
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
    //preps for shutdown by saving user profiles
    private void shutDown(ArrayList<Profile> profiles){
        Profile.writeAccounts(profiles);
    }

    //fills labels arrays
    private Label[] labelFillST(){
        Label[] labels = new Label[1];
        labels[0] = new Label("Welcome:");
        return labels;
    }
    private Label[] labelFillSU(){
        Label[] labels = new Label[3];
        labels[0] = new Label("Please Enter A User Name:");
        labels[1]= new Label("Please Enter A Password:");
        labels[2] = new Label("Please Re-Enter Password:");
        return labels;
    }
    private Label[] labelFillSI(){
        Label[] labels = new Label[2];
        labels[0]  = new Label("Enter Username:");
        labels[1]  = new Label("Enter Password:");
        return labels;
    }
    private Label[] labelFillVP(){
        Label[] labels = new Label[5];
        labels[0] = new Label("Welcome, Null");
        labels[1] = new Label("Your First Name: Null");
        labels[2] = new Label("Your Last Name: Null");
        labels[3] = new Label("Your Email Address: Null");
        labels[4] = new Label("Location: Null");
        return labels;
    }
    private Label[] labelFillNP(){
        Label[] labels = new Label[5];
        labels[0] = new Label("Enter your First Name:");
        labels[1]= new Label("Enter your Last Name:");
        labels[2] = new Label("Enter your Email Address:");
        labels[3] = new Label("Enter your City");
        labels[4] = new Label("Enter your State");
        return labels;
    }
    private Label[] labelFillRS(){
        Label[] labels = new Label[2];
        //todo add labels
        return labels;
    }
    private Label[] labelFillEP(){
        Label[] labels = new Label[5];
        labels[0] = new Label("Enter your New First Name:");
        labels[1] = new Label("Enter your New Last Name:");
        labels[2] = new Label("Enter your New Email Address:");
        labels[3] = new Label("Enter your New City");
        labels[4] = new Label("Enter your New State");
        return labels;
    }

    //fills text field arrays
    private TextField[] textFieldsFillNP(){
        TextField[] textFields = new TextField[5];

        textFields[0] = new TextField();
        textFields[1] = new TextField();
        textFields[2] = new TextField();
        textFields[3] = new TextField("Greensboro");
        textFields[4] = new TextField("North Carolina");
        textFields[3].setEditable(false);
        textFields[4].setEditable(false);
        return textFields;
    }
    private TextField[] textFieldFillEP(ProfileWrapper user){
        TextField[] textFields = new TextField[5];
        textFields[0] = new TextField(user.getFirstName());
        textFields[1] = new TextField(user.getLastName());
        textFields[2] = new TextField(user.getEmail());
        textFields[3] = new TextField("Greensboro");
        textFields[4] = new TextField("North Carolina");
        //Text Fields
        textFields[3].setEditable(false);
        textFields[4].setEditable(false);

        return textFields;
    }

    private void UpdateEP( TextField[] textFields, Profile user){
        textFields[0].setText(user.getFirstName());
        textFields[1].setText(user.getLastName());
        textFields[2].setText(user.getEmail());
        textFields[3].setText("Greensboro");
        textFields[4].setText("North Carolina");
    }
    private void UpdateVP(Label[] labels, Profile user){
        labels[0].setText("Welcome, " + user.getUsername());
        labels[1].setText("Your First Name: " + user.getFirstName());
        labels[2].setText("Your Last Name: "+ user.getLastName());
        labels[3].setText("Your Email Address: " + user.getEmail());
        labels[4].setText("Location: " + user.getLocation());
    }

}

