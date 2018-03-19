import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.ArrayList;

public class ElectricMain extends Application {

    UserStore users = new UserStore();
    User user = new User();
    String username;
    String password;
    Stage window;
    Scene scene1;//Landing
    Scene scene2;//Login Account
    Scene scene3;//Create Account
    Scene scene4;//Profile Landing
    Scene scene5;//Edit Account
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;//Cancel button for scene5
    Button button10;//Save button for scene5
    Button button11;//Delete account button for scene5
    TextField textField1;
    TextField textField2;
    TextField textField3;
    TextField textField4;
    TextField textField5;
    TextField textField6;
    TextField textField7;//Phone number textfield for scene5
    TextField textField8;//email textfield for scene5
    TextField textField9;
    TextField textField10;
    TextField textField11;
    TextField textField12;
    TextField textField13;
    TextField textField14;
    TextField textField15;
    TextField textField16;
    TextField textField17;
    TextField textField18;
    TextField textField19;
    TextField textField20;
    TextField textField21;
    TextField textField22;
    PasswordField passwordField1;
    Label label1;
    Label label2;
    Label label3;
    Label label4;
    Label label5;
    Label label6;
    Label label7;
    Label label8;
    Label label9;
    Label label10;
    Label label11;
    Label label12;//Availability label for scene5
    Label label13;//Contact info label for scene5
    Label label14;//Phone number label
    Label label15;//email address label
    Label label16;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    CheckBox checkBox6;
    CheckBox checkBox7;


    public ElectricMain() throws JAXBException {
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException, JAXBException {
        window = primaryStage;
        button1 = new Button("Login");
        button2 = new Button("Create Account");
        button3 = new Button("Login");
        button4 = new Button("Back");
        button5 = new Button("Back");
        button6 = new Button("Back");
        button7 = new Button("Continue");
        button8 = new Button("Edit Account");
        button9 = new Button("Cancel");
        button10 = new Button("Save");
        button11 = new Button("Delete Account");
        textField1 = new TextField();
        textField2 = new TextField();
        textField3 = new TextField();
        textField4 = new TextField();
        textField5 = new TextField();
        textField6 = new TextField();
        textField7 = new TextField();
        textField8 = new TextField();
        textField9 = new TextField();
        textField10 = new TextField();
        textField11 = new TextField();
        textField12 = new TextField();
        textField13 = new TextField();
        textField14 = new TextField();
        textField15 = new TextField();
        textField16 = new TextField();
        textField17 = new TextField();
        textField18 = new TextField();
        textField19 = new TextField();
        textField20 = new TextField();
        textField21 = new TextField();
        textField22 = new TextField();
        passwordField1 = new PasswordField();
        label1 = new Label("Username");
        label2 = new Label("Password");
        label3 = new Label("");
        label4 = new Label("Welcome back");
        label5 = new Label("First Name");
        label6 = new Label("Last Name");
        label7 = new Label("Username");
        label8 = new Label("Password");
        label9 = new Label("Confirm Password");
        label10 = new Label("");
        label11 = new Label("");
        label12 = new Label("Availability");
        label13 = new Label("Contact Information");
        label14 = new Label("Phone number");
        label15 = new Label("Email Address");
        label16 = new Label("");
        button1.setOnAction(e -> window.setScene(scene2));
        button2.setOnAction(e -> window.setScene(scene3));
        button3.setOnAction(e -> buttonPressAction1());
        button4.setOnAction(e -> window.setScene(scene1));
        button6.setOnAction(e -> window.setScene(scene1));
        button7.setOnAction(e -> {
            try {
                buttonPressAction2();
            } catch (JAXBException e1) {
                e1.printStackTrace();
            }
        });
        button8.setOnAction(e -> window.setScene(scene5));
        button9.setOnAction(e -> buttonPressAction3());
        button10.setOnAction(e -> {
            try {
                buttonPressAction4();
            } catch (JAXBException e1) {
                e1.printStackTrace();
            }
        });
        button11.setOnAction(e -> {
            try {
                buttonPressAction5();
            } catch (JAXBException e1) {
                e1.printStackTrace();
            }
        });
        checkBox1 = new CheckBox("Sunday");
        checkBox2 = new CheckBox("Monday");
        checkBox3 = new CheckBox("Tuesday");
        checkBox4 = new CheckBox("Wednesday");
        checkBox5 = new CheckBox("Thursday");
        checkBox6 = new CheckBox("Friday");
        checkBox7 = new CheckBox("Saturday");

        //scene1 Login or Create Account landing
        AnchorPane electricianLanding = new AnchorPane();
        electricianLanding.getChildren().addAll(button1, button2);
        button1.setLayoutX(120.0);
        button1.setLayoutY(150.0);
        button2.setLayoutX(200.0);
        button2.setLayoutY(150.0);

        //scene2 Login landing
        AnchorPane login = new AnchorPane();
        login.getChildren().addAll(textField1, passwordField1, button3, button4, label1, label2, label3);
        label1.setLayoutX(40.0);
        label1.setLayoutY(80.0);
        label2.setLayoutX(210.0);
        label2.setLayoutY(80.0);
        label3.setLayoutX(40.0);
        label3.setLayoutY(130.0);
        textField1.setLayoutX(40.0);
        textField1.setLayoutY(100.0);
        passwordField1.setLayoutX(210.0);
        passwordField1.setLayoutY(100.0);
        button3.setLayoutX(200);
        button3.setLayoutY(150.0);
        button4.setLayoutX(20.0);
        button4.setLayoutY(250.0);

        //scene3 Create Account Landing
        AnchorPane createAccount = new AnchorPane();
        createAccount.getChildren().addAll(textField2, textField3, textField4, textField5, textField6,
                button6, button7, label5, label6, label7, label8, label9, label10);
        label5.setLayoutX(20.0);
        label5.setLayoutY(55.0);
        label6.setLayoutX(20.0);
        label6.setLayoutY(85.0);
        label7.setLayoutX(20.0);
        label7.setLayoutY(115.0);
        label8.setLayoutX(20.0);
        label8.setLayoutY(145.0);
        label9.setLayoutX(20.0);
        label9.setLayoutY(175.0);
        label10.setLayoutX(20.0);
        label10.setLayoutY(205.0);
        textField2.setLayoutX(130.0);
        textField2.setLayoutY(50.0);
        textField3.setLayoutX(130.0);
        textField3.setLayoutY(80.0);
        textField4.setLayoutX(130.0);
        textField4.setLayoutY(110.0);
        textField5.setLayoutX(130.0);
        textField5.setLayoutY(140.0);
        textField6.setLayoutX(130.0);
        textField6.setLayoutY(170.0);
        button6.setLayoutX(20.0);
        button6.setLayoutY(250.0);
        button7.setLayoutX(215.0);
        button7.setLayoutY(200.0);

        //scene4 Account Landing
        AnchorPane accountLanding = new AnchorPane();
        accountLanding.getChildren().addAll(label4, label11, label16, button8);
        label4.setLayoutX(20.0);
        label4.setLayoutY(20.0);
        label11.setLayoutX(103.0);
        label11.setLayoutY(20.0);
        label16.setLayoutY(20.0);
        label16.setLayoutY(50);
        button8.setLayoutX(290.0);
        button8.setLayoutY(20.0);


        //scene5 edit account info
        AnchorPane accountEdit = new AnchorPane();
        accountEdit.getChildren().addAll(button9, button10, button11, textField7, textField8, textField9, textField10,
                textField11 , textField12, textField13, textField14, textField15, textField16, textField17, textField18,
                textField19, textField20, textField21, textField22, label12, label13, label14, label15, checkBox1,
                checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7);
        button9.setLayoutX(20.0);
        button9.setLayoutY(250.0);
        button10.setLayoutX(160.0);
        button10.setLayoutY(250.0);
        button11.setLayoutX(440.0);
        button11.setLayoutY(250.0);
        textField7.setLayoutX(430.0);
        textField7.setLayoutY(40.0);
        textField8.setLayoutX(430.0);
        textField8.setLayoutY(70.0);
        textField9.setPrefWidth(30.0);
        textField9.setLayoutX(110.0);
        textField9.setLayoutY(40.0);
        textField10.setPrefWidth(30.0);
        textField10.setLayoutX(110.0);
        textField10.setLayoutY(70.0);
        textField11.setPrefWidth(30.0);
        textField11.setLayoutX(110.0);
        textField11.setLayoutY(100.0);
        textField12.setPrefWidth(30.0);
        textField12.setLayoutX(110.0);
        textField12.setLayoutY(130.0);
        textField13.setPrefWidth(30.0);
        textField13.setLayoutX(110.0);
        textField13.setLayoutY(160.0);
        textField14.setPrefWidth(30.0);
        textField14.setLayoutX(110.0);
        textField14.setLayoutY(190.0);
        textField15.setPrefWidth(30.0);
        textField15.setLayoutX(110.0);
        textField15.setLayoutY(220.0);
        textField16.setPrefWidth(30.0);
        textField16.setLayoutX(170.0);
        textField16.setLayoutY(40.0);
        textField17.setPrefWidth(30.0);
        textField17.setLayoutX(170.0);
        textField17.setLayoutY(70.0);
        textField18.setPrefWidth(30.0);
        textField18.setLayoutX(170.0);
        textField18.setLayoutY(100.0);
        textField19.setPrefWidth(30.0);
        textField19.setLayoutX(170.0);
        textField19.setLayoutY(130.0);
        textField20.setPrefWidth(30.0);
        textField20.setLayoutX(170.0);
        textField20.setLayoutY(160.0);
        textField21.setPrefWidth(30.0);
        textField21.setLayoutX(170.0);
        textField21.setLayoutY(190.0);
        textField22.setPrefWidth(30.0);
        textField22.setLayoutX(170.0);
        textField22.setLayoutY(220.0);
        label12.setLayoutX(20.0);
        label12.setLayoutY(20.0);
        label13.setLayoutX(430.0);
        label13.setLayoutY(20.0);
        label14.setLayoutX(350.0);
        label14.setLayoutY(40.0);
        label15.setLayoutX(350.0);
        label15.setLayoutY(70.0);
        checkBox1.setLayoutX(20.0);
        checkBox1.setLayoutY(40.0);
        checkBox2.setLayoutX(20.0);
        checkBox2.setLayoutY(70.0);
        checkBox3.setLayoutX(20.0);
        checkBox3.setLayoutY(100.0);
        checkBox4.setLayoutX(20.0);
        checkBox4.setLayoutY(130.0);
        checkBox5.setLayoutX(20.0);
        checkBox5.setLayoutY(160.0);
        checkBox6.setLayoutX(20.0);
        checkBox6.setLayoutY(190.0);
        checkBox7.setLayoutX(20.0);
        checkBox7.setLayoutY(220.0);


        scene1 = new Scene(electricianLanding, 400, 300);
        scene2 = new Scene(login, 400, 300);
        scene3 = new Scene(createAccount, 400, 300);
        scene4 = new Scene(accountLanding, 400, 300);
        scene5 = new Scene(accountEdit, 600, 300);

        window.setScene(scene1);
        window.show();



    }

    //Button to login to existing account
    public void buttonPressAction1(){
        if(users.containsUsername(textField1.getText()) && users.verifyPassword(textField1.getText(), passwordField1.getText())){
            user = users.getAccount(textField1.getText(), passwordField1.getText());
            username = textField1.getText();
            password = passwordField1.getText();
            window.setScene(scene4);
            label11.setText(user.getFirstName() + " " + user.getLastName());
            label16.setText(users.getAccount(username, password).getEmail());

        }else{
            label3.setText("Incorrect username or password");
            label3.setTextFill(Color.RED);
        }
    }

    //button to continue creating account
    public void buttonPressAction2() throws JAXBException {
        if(!users.containsUsername(textField4.getText()) && textField5.getText().equals(textField6.getText())){
            User temp = new User();
            temp.setFirstName(textField2.getText());
            temp.setLastName(textField3.getText());
            temp.setUsername(textField4.getText());
            temp.setPassword(textField5.getText());
            temp.setProvider(true);
            users.add(temp);
            users.exportAccount();
            window.setScene(scene4);
            user = users.getAccount(textField4.getText(), textField5.getText());
            label11.setText(user.getFirstName() + " " + user.getLastName());
        }else if(!textField5.getText().equals(textField6.getText())){
            label10.setText("Password does not match");
            label10.setTextFill(Color.RED);
        }else{
            label10.setText("User already exists");
            label10.setTextFill(Color.RED);
        }
    }

    //button to cancel eddditing account
    public void buttonPressAction3(){
        User temp = new User();
        temp = users.getAccount(username, password);
        window.setScene(scene4);
        label11.setText(temp.getFirstName() + " " + temp.getLastName());
        label16.setText(users.getAccount(username, password).getEmail());
    }

    //button to save editing an account
    public void buttonPressAction4() throws JAXBException {
        //users.getAccount(username, password).setPhoneNumber(textField7.getText());
        users.getAccount(username, password).setEmail(textField8.getText());

        boolean[] days = new boolean[7];
        days[0] = checkBox1.isSelected();
        days[1] = checkBox2.isSelected();
        days[2] = checkBox3.isSelected();
        days[3] = checkBox4.isSelected();
        days[4] = checkBox5.isSelected();
        days[5] = checkBox6.isSelected();
        days[6] = checkBox7.isSelected();

        String[] hours1 = new String[7];
        hours1[0] = textField9.getText();
        hours1[1] = textField10.getText();
        hours1[2] = textField11.getText();
        hours1[3] = textField12.getText();
        hours1[4] = textField13.getText();
        hours1[5] = textField14.getText();
        hours1[6] = textField15.getText();

        String[] hours2 = new String[7];
        hours2[0] = textField16.getText();
        hours2[1] = textField17.getText();
        hours2[2] = textField18.getText();
        hours2[3] = textField19.getText();
        hours2[4] = textField20.getText();
        hours2[5] = textField21.getText();
        hours2[6] = textField22.getText();

        Availability av = new Availability();

        window.setScene(scene4);

        users.exportAccount();
    }

    //button to delete account
    public void buttonPressAction5() throws JAXBException {
        window.setScene(scene1);
        AlertBox.display("Goodbye :(");
        users.removeAccount(username, password);
    }


}
