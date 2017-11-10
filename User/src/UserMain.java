import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class UserMain extends Application{
    private Scene startScene, signUpScene, signInScene, newProfileScene, viewProfileScene, editProfileScene,
            searchScene, providerScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ProfileHash profiles = Profile.importAccounts();
        //PriorityQueue providers = new PriorityQueue<Provider>(); todo add provider class from group
        ProfileWrapper user = new ProfileWrapper();
        final int WIDTH = 800;
        final int HEIGHT = 500;

        //labels
        Label[] labelsST, labelsSU, labelsSI, labelsVP, labelsEP, labelsNP, labelsSS, labelsPS;
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
        //Search scene labels
        labelsSS = labelFillSS(5);//temp as 5, todo add drop down for number of displayed providers
        //provider scene labels
        labelsPS = labelFillPS();


        //TextFields
        //todo field arrays
        TextField[] textFieldsEP, textFieldsNP;
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

        //Buttons
        //Start Scene Buttons
        Button signUp = new Button("Sign Up");
        Button signIn = new Button("Sign In");
        //Sign up buttons
        Button backSU = new Button("Back");
        Button enterSU = new Button("Enter");
        //Sign in buttons
        Button backSI = new Button("Back");
        Button enterSI = new Button("Enter");
        //Profile View Buttons
        Button editVP = new Button("Edit");
        Button searchVP = new Button("Search");
        //new profile Buttons
        Button enterNP = new Button("Enter");
        //edit profile Buttons
        Button enterEP = new Button("Enter");

        //Start scene button events
        signUp.setOnAction(e -> primaryStage.setScene(signUpScene));
        signIn.setOnAction(e -> primaryStage.setScene(signInScene));
        //Sign Up button events
        backSU.setOnAction(e -> primaryStage.setScene(startScene));
        enterSU.setOnAction(e-> {
            String pass1 = passSU.getText();
            String pass2 = passSU1.getText();
            if(userSU.getText().isEmpty()){
                AlertBox.display("A User Name is Required");
            }else if(profiles.containsKey(userSU.getText())){
                AlertBox.display("Username in Use, Please Choose Another.");
            }else if(!pass1.equals(pass2)){
                AlertBox.display("Passwords do not match");
            }else {
                primaryStage.setScene(newProfileScene);
            }
        });
        //sing in button events
        backSI.setOnAction(e -> primaryStage.setScene(startScene));
        enterSI.setOnAction(e-> {
            String username = userSI.getText();
            String password =  passSI.getText();

            if(profiles.containsKey(username)){
                if(profiles.get(username).passwordCheck(password)){
                    user.setProfile(profiles.get(username));
                    UpdateVP(labelsVP, user.getProfile());
                    primaryStage.setScene(viewProfileScene);
                }else{
                    AlertBox.display("Incorrect Username Or Password");
                }
            }else{
                AlertBox.display("Incorrect Username Or Password");
            }
        });
        //profile view Button Events
        editVP.setOnAction(e -> {
            UpdateEP(textFieldsEP, user.getProfile());
            primaryStage.setScene(editProfileScene);
        });
        searchVP.setOnAction(e -> primaryStage.setScene(searchScene));
        //new profile Button Events
        enterNP.setOnAction(e -> {
            Profile nUser = createProfile(userSU.getText(), passSU1.getText(), textFieldsNP[0].getText(),
                    textFieldsNP[1].getText(), textFieldsNP[2].getText());

            profiles.put(nUser.getUsername(), nUser);
            user.setProfile(nUser);
            UpdateVP(labelsVP, nUser);
            primaryStage.setScene(viewProfileScene);
        });
        //edit profile Button Events
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
        for (Label label : labelsVP) {
            layoutVP.getChildren().add(label);
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

        //populates the searchScene
        VBox layoutSS = new VBox(20);
        layoutSS.setAlignment(Pos.CENTER);
        for(Label label : labelsSS ){
            layoutSS.getChildren().add(label);
        }
        searchScene = new Scene(layoutSS, WIDTH, HEIGHT);

        //populates provider scene todo


        //primary
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Zapp: Fast Electrician Finder");
        primaryStage.setOnCloseRequest(event -> {
            shutDown(profiles);
            System.exit(0);
        });
        primaryStage.show();


    }

   //checks if email is in correct format
    private boolean checkEmail(String email){
        //todo add regex for email checking
        return true;
    }

    //changes profile
    private void editProfile(String firstName, String lastName, String email, Profile profile){
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setEmail(email);
    }
    //creates a new profile
    private Profile createProfile(String username, String password, String firstName, String lastName, String email){
        return new Profile(username, password, firstName, lastName, email);
        //todo investigate why this is here.
    }

    //preps for shutdown by saving user profiles
    private void shutDown(ProfileHash profiles){
        Profile.exportAccount(profiles);
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
    private Label[] labelFillPS(){
        Label[] labels = new Label[1];
        labels[0] = new Label("Temp");
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
    private Label[] labelFillSS(int quantity){
        //todo add provider support will be a priority queue based on rating
        Label[] labels = new Label[4*quantity];
        for(int i = 0; i+3 < labels.length; i = i+4){
            labels[i] = new Label("Provider: ");
            labels[i+1] = new Label("Hours: " );
            labels[i+2] = new Label("Rating: ");
            labels[i+3] = new Label("Availability: ");
        }
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
        labels[4].setText("Location: " + user.getCity() + ", " + user.getState());
    }
}