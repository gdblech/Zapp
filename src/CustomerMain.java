import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.time.DayOfWeek;
import java.util.ArrayList;


public class CustomerMain extends Application{
    private Scene startScene, signUpScene, signInScene, newProfileScene, viewProfileScene, editProfileScene,
            preSearch, searchScene, reviewsScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        UserStore users = new UserStore();
        ArrayList<User> providers = new ArrayList<>();
        ArrayList<User> currentProviders = new ArrayList<>();
        UserWrapper user = new UserWrapper();
        final int WIDTH = 800;
        final int HEIGHT = 500;

        //labels
        Label[] labelsST, labelsSU, labelsSI, labelsVP, labelsEP, labelsNP, labelsSS, labelsRS;
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
        //New User Labels
        labelsNP = labelFillNP();
        //preSearch scene labels
        Label labelPS1 = new Label("Provider Availability:");
        //Search scene labels
        labelsSS = labelFillSS();
        //provider scene labels
        labelsRS = labelFillRS();


        //TextFields
        //todo field arrays
        TextField[] textFieldsEP, textFieldsNP;
        PasswordField passSU, passSU1,  passSI;
        //New User TextFields
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
        Button back = new Button("Return");
        //Sign up buttons
        Button backSU = new Button("Back");
        Button enterSU = new Button("Enter");
        //Sign in buttons
        Button backSI = new Button("Back");
        Button enterSI = new Button("Enter");
        //User View Buttons
        Button editVP = new Button("Edit");
        Button searchVP = new Button("Search");
        //new profile Buttons
        Button enterNP = new Button("Enter");
        //edit profile Buttons
        Button enterEP = new Button("Enter");
        //preSearch buttons
        Button searchPS = new Button("Search");
        Button backPS = new Button("Back");
        //search button
        Button backSS = new Button("Back");
        Button[] buttonsSS = new Button[5];
        for(int i = 0; i < buttonsSS.length; i++){
            buttonsSS[i] = new Button("Reviews");
        }
        //todo remove me
        //Start scene button events
        signUp.setOnAction(e -> primaryStage.setScene(signUpScene));
        signIn.setOnAction(e -> primaryStage.setScene(signInScene));
        back.setOnAction(e ->{
            try {
                new Landing().start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        //Sign Up button events
        backSU.setOnAction(e -> primaryStage.setScene(startScene));
        enterSU.setOnAction(e-> {

            String pass1 = passSU.getText();
            String pass2 = passSU1.getText();
            String username = userSU.getText();

            if(username.isEmpty()){
                AlertBox.display("A User Name is Required");
            }else if(users.containsUsername(username)){
                AlertBox.display("Username in Use, Please Choose Another.");
            }else if(!pass1.equals(pass2) || pass1.isEmpty() || pass2.isEmpty()){
                AlertBox.display("Passwords do not match");
            }
            else {
                primaryStage.setScene(newProfileScene);
            }
        });
        //sing in button events
        backSI.setOnAction(e -> primaryStage.setScene(startScene));
        enterSI.setOnAction(e-> {
            String username = userSI.getText();
            String password =  passSI.getText();
            User tempUser = users.getAccount(username,password);

            if(tempUser != null){

                if(tempUser.getBanned()){
                    AlertBox.display("User is Currently Banned");
                }else{
                    user.setUser(tempUser);
                    UpdateVP(labelsVP, user.getUser());
                    primaryStage.setScene(viewProfileScene);
                }
            }else{
                AlertBox.display("Incorrect Username Or Password");
            }
        });
        //profile view Button Events
        editVP.setOnAction(e -> {
            UpdateEP(textFieldsEP, user.getUser());
            primaryStage.setScene(editProfileScene);
        });
        searchVP.setOnAction(e -> primaryStage.setScene(preSearch));
        //new profile Button Events
        enterNP.setOnAction(e -> {
            User nUser = new User(userSU.getText(), passSU1.getText(), textFieldsNP[0].getText(),
                    textFieldsNP[1].getText(), textFieldsNP[2].getText(), false, false);
            users.add(nUser);
            user.setUser(nUser);
            UpdateVP(labelsVP, nUser);
            primaryStage.setScene(viewProfileScene);
        });
        //edit profile Button Events
        enterEP.setOnAction(e -> {
            editProfile(textFieldsEP[0].getText(), textFieldsEP[1].getText(), textFieldsEP[2].getText(), user.getUser());
            UpdateVP(labelsVP, user.getUser());
            primaryStage.setScene(viewProfileScene);
        });
        //preSearch button events
        searchPS.setOnAction(e -> {
            providers.addAll(users.getProviders());
            currentProviders.addAll(UpdateSS(labelsSS, providers));
            for(int i = 0; i < buttonsSS.length; i++){
                User provider = currentProviders.get(i);
                buttonsSS[i].setOnAction(e1 -> {
                    ReviewBox.display(provider, false, user.getUsername());
                });
            }
            primaryStage.setScene(searchScene);
        });
        backPS.setOnAction(e -> primaryStage.setScene(viewProfileScene));
        //search button events
        backSS.setOnAction( e ->primaryStage.setScene(preSearch));


        //chekcboxes
        CheckBox[] availability = new CheckBox[7];
        for(int i =0; i < availability.length; i++){
            availability[i] = new CheckBox(DayOfWeek.of(i+1).toString());
        }


        //populates the startScene
        HBox layout = new HBox(75);
        layout.getChildren().addAll(labelsST[0], signUp, signIn, back);
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

        //populates preSearch scene
        VBox layoutPS = new VBox(20);
        layoutPS.getChildren().add(labelPS1);
        for (CheckBox chk : availability){
            layoutPS.getChildren().add(chk);
        }
        layoutPS.getChildren().addAll(searchPS, backPS);
        preSearch = new Scene(layoutPS, WIDTH, HEIGHT);

        //populates the searchScene
        ScrollPane sp = new ScrollPane();
        VBox layoutSS = new VBox(20);
        int k = 0;
        for(int i = 0; i+3 < labelsSS.length; i= i+4){
            layoutSS.getChildren().add(labelsSS[i]);
            layoutSS.getChildren().add(labelsSS[i+1]);
            layoutSS.getChildren().add(labelsSS[i+2]);
            layoutSS.getChildren().add(labelsSS[i+3]);
            layoutSS.getChildren().add(buttonsSS[k]);
            layoutSS.getChildren().add(new Separator());
            k++;
        }
        sp.setContent(layoutSS);

        searchScene = new Scene(sp, WIDTH, HEIGHT);

        //populates review scene
        VBox layoutRS = new VBox();
        //todo review scene stuff
        reviewsScene = new Scene(layoutRS, WIDTH, HEIGHT);

        //populates provider scene todo


        //primary
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Zapp: Fast Electrician Finder");
        primaryStage.setOnCloseRequest(event -> {
            shutDown(users);
            System.exit(0);
        });
        primaryStage.show();
    }

    //changes user
    private void editProfile(String firstName, String lastName, String email, User user){
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
    }
    //creates a new profile

    //preps for shutdown by saving user profiles
    private void shutDown(UserStore users){
        try{
            users.exportAccount();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
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
        Label[] labels = new Label[4];
        labels[0] = new Label("Welcome, Null");
        labels[1] = new Label("Your First Name: Null");
        labels[2] = new Label("Your Last Name: Null");
        labels[3] = new Label("Your Email Address: Null");
        return labels;
    }
    private Label[] labelFillNP(){
        Label[] labels = new Label[3];
        labels[0] = new Label("Enter your First Name:");
        labels[1]= new Label("Enter your Last Name:");
        labels[2] = new Label("Enter your Email Address:");
        return labels;
    }
    private Label[] labelFillRS(){
        Label[] labels = new Label[1];
        labels[0] = new Label("Temp");
        //todo add labels
        return labels;
    }
    private Label[] labelFillEP(){
        Label[] labels = new Label[3];
        labels[0] = new Label("Enter your New First Name:");
        labels[1] = new Label("Enter your New Last Name:");
        labels[2] = new Label("Enter your New Email Address:");
        return labels;
    }
    private Label[] labelFillSS(){
        //todo add provider support will be a priority queue based on rating
        Label[] labels = new Label[20];
        for(int i = 0; i+3 < labels.length; i = i+4){
            labels[i] = new Label("Provider: ");
            labels[i+1] = new Label("Contact: " );
            labels[i+2] = new Label("Rating: ");
            labels[i+3] = new Label("Availability: ");
        }
        return labels;
    }

    //fills text field arrays
    private TextField[] textFieldsFillNP(){
        TextField[] textFields = new TextField[3];

        textFields[0] = new TextField();
        textFields[1] = new TextField();
        textFields[2] = new TextField();
        return textFields;
    }
    private TextField[] textFieldFillEP(UserWrapper user){
        TextField[] textFields = new TextField[3];
        textFields[0] = new TextField(user.getFirstName());
        textFields[1] = new TextField(user.getLastName());
        textFields[2] = new TextField(user.getEmail());

        return textFields;
    }

    private void UpdateEP( TextField[] textFields, User user){
        textFields[0].setText(user.getFirstName());
        textFields[1].setText(user.getLastName());
        textFields[2].setText(user.getEmail());
    }

    private void UpdateVP(Label[] labels, User user){
        labels[0].setText("Welcome, " + user.getUsername());
        labels[1].setText("Your First Name: " + user.getFirstName());
        labels[2].setText("Your Last Name: "+ user.getLastName());
        labels[3].setText("Your Email Address: " + user.getEmail());
    }

    private ArrayList<User> providerSearch(UserStore users){
        //todo get matching providers
        return users.getProviders();
    }

    private ArrayList<User> UpdateSS(Label[] labels, ArrayList<User> providers){
        ArrayList<User> current = new ArrayList<>();
        for(int i = 0; i+3 < labels.length; i = i+4){
            if(!providers.isEmpty()) {
                User user = providers.get(0);
                labels[i].setText("Provider: " + user.getLastName() + ", " + user.getFirstName());
                labels[i + 1].setText("Contact: " + user.getEmail());
                labels[i + 2].setText("Rating: " + "5");//todo add getRating
                labels[i + 3].setText("Availability: " + "MTWRFSS"); //todo add rating
                current.add(providers.get(0));
                providers.remove(0);
            }else {
                labels[i].setText("");
                labels[i+1].setText("");
                labels[i+2].setText("");
                labels[i+3].setText("");
            }

        }
    return current;
    }

    private  void UpdateRS(User user, Label[] labels){
        //todo get reviews
    }
}