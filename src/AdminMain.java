
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.geometry.HPos.RIGHT;

public class AdminMain extends Application {

    @Override
    // This has been the worst function in the history of functions, maybe ever
    public void start(Stage primaryStage) throws Exception{
        // Load users
        UserStore userStore = new UserStore();

        GridPane primaryGrid = new GridPane();
        primaryGrid.setAlignment(Pos.CENTER);
        primaryGrid.setHgap(10);
        primaryGrid.setVgap(10);
        primaryGrid.setPadding(new Insets(25, 25, 25, 25));

        primaryStage.setTitle("Electrician");
        primaryStage.setScene(new Scene(primaryGrid, 300, 275));
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(275);

        GridPane rootGrid = new GridPane();
        rootGrid.setAlignment(Pos.CENTER);
        rootGrid.setHgap(10);
        rootGrid.setVgap(10);
        rootGrid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Electrician Finder");
        sceneTitle.setFont(Font.font("Helvetica Neue", FontWeight.NORMAL, 20));
        rootGrid.add(sceneTitle, 0, 0, 2, 1);

        Label username = new Label("Username:");
        rootGrid.add(username, 0, 1);
        TextField usernameTF = new TextField();
        rootGrid.add(usernameTF, 1, 1);

        Label password = new Label("Password:");
        rootGrid.add(password, 0, 2);
        TextField passwordTF = new TextField();
        rootGrid.add(passwordTF, 1, 2);

        Button landingBtn = new Button("Landing");
        HBox hbLandingBtn = new HBox(10);
        hbLandingBtn.setAlignment(Pos.CENTER);
        hbLandingBtn.getChildren().add(landingBtn);
        rootGrid.add(hbLandingBtn, 0, 4);

        Button signinBtn = new Button("Sign in");
        HBox hbSigninBtn = new HBox(10);
        hbSigninBtn.setAlignment(Pos.CENTER);
        hbSigninBtn.getChildren().add(signinBtn);
        rootGrid.add(hbSigninBtn, 1, 4);

        // Error text if the username/password is wrong
        final Text actiontarget = new Text();
        rootGrid.add(actiontarget, 0, 6);
        rootGrid.setColumnSpan(actiontarget, 2);
        rootGrid.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");

        // Build the scene --
        Scene loginScene = new Scene(rootGrid, 300, 275);

        // Landing button click
        landingBtn.setOnAction(backEvent -> {
            try {
                new Landing().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Sign in button click
        signinBtn.setOnAction(signinEvent -> {
            // User authentication
            // Verify password and an admin
            if (userStore.containsUsername(usernameTF.getText())) {
                if (userStore.verifyPassword(usernameTF.getText(), passwordTF.getText())) {
                    if (userStore.getAdmin(usernameTF.getText())) {
                        // TODO: Ensure the user's an admin
                        // Move to the main menu
                        GridPane mainMenuGrid = new GridPane();
                        mainMenuGrid.setAlignment(Pos.CENTER);
                        mainMenuGrid.setHgap(10);
                        mainMenuGrid.setVgap(10);
                        mainMenuGrid.setPadding(new Insets(25, 25, 25, 25));

                        Text mainMenuSceneTitle = new Text("Welcome, " + usernameTF.getText() + ".");
                        mainMenuSceneTitle.setFont(Font.font("Helvetica Neue", FontWeight.NORMAL, 20));
                        mainMenuGrid.add(mainMenuSceneTitle, 0, 0, 2, 1);

                        Button systemBtn = new Button("System Info");
                        HBox hbSystemBtn = new HBox(10);
                        hbSystemBtn.setAlignment(Pos.CENTER);
                        hbSystemBtn.getChildren().add(systemBtn);
                        mainMenuGrid.add(hbSystemBtn, 0, 2);

                        Button reviewsBtn = new Button("Reviews");
                        HBox hbReviewsBtn = new HBox(10);
                        hbReviewsBtn.setAlignment(Pos.CENTER);
                        hbReviewsBtn.getChildren().add(reviewsBtn);
                        mainMenuGrid.add(hbReviewsBtn, 1, 2);

                        Button customersBtn = new Button("Customers");
                        HBox hbCustomersBtn = new HBox(10);
                        hbCustomersBtn.setAlignment(Pos.CENTER);
                        hbCustomersBtn.getChildren().add(customersBtn);
                        mainMenuGrid.add(hbCustomersBtn, 0, 3);

                        Button providersBtn = new Button("Providers");
                        HBox hbProvidersBtn = new HBox(10);
                        hbProvidersBtn.setAlignment(Pos.CENTER);
                        hbProvidersBtn.getChildren().add(providersBtn);
                        mainMenuGrid.add(hbProvidersBtn, 1, 3);

                        Button signoutBtn = new Button("Sign out");
                        HBox hbSignoutBtn = new HBox(10);
                        hbSignoutBtn.setAlignment(Pos.CENTER);
                        hbSignoutBtn.getChildren().add(signoutBtn);
                        mainMenuGrid.add(hbSignoutBtn, 1, 4);

                        Scene mainMenuScene = new Scene(mainMenuGrid, 375, 200);

                        // TODO: This scene should be in a new scope
                        primaryStage.setTitle("Electrician -- Sysadmin");
                        primaryStage.setScene(mainMenuScene);
                        primaryStage.setMinWidth(425);
                        primaryStage.setMinHeight(275);
                        primaryStage.show();

                        systemBtn.setOnAction(systemEvent -> {
                            // TODO: Turn this into a function
                            GridPane systemInfoGrid = new GridPane();
                            systemInfoGrid.setAlignment(Pos.CENTER);
                            systemInfoGrid.setHgap(10);
                            systemInfoGrid.setVgap(10);
                            systemInfoGrid.setPadding(new Insets(25, 25, 25, 25));

                            // Title
                            Text systemInfoSceneTitle = new Text("System Information");
                            systemInfoSceneTitle.setFont(Font.font("Helvetica Neue", FontWeight.NORMAL, 20));
                            // systemInfoGrid.add(systemInfoSceneTitle, 0, 0, 2, 1);
                            systemInfoGrid.add(systemInfoSceneTitle, 0, 0);

                            // Current number of customers
                            Text numCustomers = new Text("Current number of customers: " + userStore.getNumCustomers());
                            numCustomers.setFont(Font.font("Helvetica Neue", FontWeight.NORMAL, 16));
                            systemInfoGrid.add(numCustomers, 0, 1);

                            // Current number of banned customers
                            Text numBannedCustomers = new Text("Current number of banned customers: " + userStore.getNumBannedCustomers());
                            numBannedCustomers.setFont(Font.font("Helvetica Neue", FontWeight.NORMAL, 16));
                            systemInfoGrid.add(numBannedCustomers, 0, 2);

                            // Current number of providers
                            Text numProviders = new Text("Current number of providers: " + userStore.getNumProviders());
                            numProviders.setFont(Font.font("Helvetica Neue", FontWeight.NORMAL, 16));
                            systemInfoGrid.add(numProviders, 0, 3);

                            // Current number of banned providers
                            Text numBannedProviders = new Text("Current number of banned providers: " + userStore.getNumBannedProviders());
                            numBannedProviders.setFont(Font.font("Helvetica Neue", FontWeight.NORMAL, 16));
                            systemInfoGrid.add(numBannedProviders, 0, 4);

                            // Back button
                            Button backBtn = new Button("Back");
                            HBox hbBackBtn = new HBox(10);
                            hbBackBtn.setAlignment(Pos.CENTER);
                            hbBackBtn.getChildren().add(backBtn);
                            systemInfoGrid.add(backBtn, 0, 5);

                            primaryStage.setScene(new Scene(systemInfoGrid, 375, 200));
                            primaryStage.setMinWidth(425);
                            primaryStage.setMinHeight(275);
                            primaryStage.show();

                            backBtn.setOnAction(backEvent -> {
                                primaryStage.setScene(mainMenuScene);
                                primaryStage.show();
                            });
                        });

                        reviewsBtn.setOnAction(reviewsEvent -> {
                            // TODO: Show list of customers
                            // TODO: Implement back button
                        });

                        customersBtn.setOnAction(customersEvent -> {
                            ObservableList<User> customers = FXCollections.observableList(userStore.getCustomers());

                            // username, first name, last name, banned, ban button
                            // Username column
                            TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
                            usernameColumn.setMinWidth(100);
                            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

                            // First name column
                            TableColumn<User, String> firstnameColumn = new TableColumn<>("First name");
                            firstnameColumn.setMinWidth(100);
                            firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

                            // Last name column
                            TableColumn<User, String> lastnameColumn = new TableColumn<>("Last name");
                            lastnameColumn.setMinWidth(100);
                            lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

                            // Banned column
                            TableColumn<User, Boolean> bannedColumn = new TableColumn<>("Banned");
                            bannedColumn.setMinWidth(50);
                            bannedColumn.setCellValueFactory(new PropertyValueFactory<>("banned"));

                            TableView<User> table = new TableView<>();
                            table.setItems(customers);
                            table.getColumns().addAll(usernameColumn, firstnameColumn, lastnameColumn, bannedColumn);
                            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

                            // Back button
                            Button backBtn = new Button("Back");
                            HBox hbBackBtn = new HBox(10);
                            hbBackBtn.setAlignment(Pos.CENTER);
                            hbBackBtn.getChildren().add(backBtn);

                            VBox vBox = new VBox();
                            vBox.getChildren().addAll(table);
                            vBox.getChildren().add(hbBackBtn);

                            Scene customersScene = new Scene(vBox);
                            primaryStage.setScene(customersScene);
                            primaryStage.setMinWidth(425);
                            primaryStage.setMinHeight(275);
                            primaryStage.show();

                            table.setOnMouseClicked(mouseEvent -> {
                                if (mouseEvent.getClickCount() == 2) {
                                    String userToBan = table.getSelectionModel().getSelectedItem().getUsername();

                                    // Ensure the user can't ban themselves
                                    if (!usernameTF.getText().equalsIgnoreCase(userToBan)) {
                                        userStore.setBanned(table.getSelectionModel().getSelectedItem().getUsername());

                                        try {
                                            // Write the newly created structsss
                                            userStore.exportAccount();

                                            // Refresh the table
                                            customers.removeAll();
                                            FXCollections.copy(customers, userStore.getCustomers());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });

                            backBtn.setOnAction(backEvent -> {
                                primaryStage.setScene(mainMenuScene);
                                primaryStage.show();
                            });
                        });

                        providersBtn.setOnAction(providersEvent -> {
                            ObservableList<User> providers = FXCollections.observableList(userStore.getProviders());

                            // Company name
                            TableColumn<User, String> companyColumn = new TableColumn<>("Company name");
                            companyColumn.setMinWidth(100);
                            companyColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

                            // First name column
                            TableColumn<User, String> firstnameColumn = new TableColumn<>("First name");
                            firstnameColumn.setMinWidth(100);
                            firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

                            // Last name column
                            TableColumn<User, String> lastnameColumn = new TableColumn<>("Last name");
                            lastnameColumn.setMinWidth(100);
                            lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

                            // Banned column
                            TableColumn<User, Boolean> bannedColumn = new TableColumn<>("Banned");
                            bannedColumn.setMinWidth(50);
                            bannedColumn.setCellValueFactory(new PropertyValueFactory<>("banned"));

                            TableView<User> table = new TableView<>();
                            table.setItems(providers);
                            table.getColumns().addAll(companyColumn, firstnameColumn, lastnameColumn, bannedColumn);
                            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

                            // Back button
                            Button backBtn = new Button("Back");
                            HBox hbBackBtn = new HBox(10);
                            hbBackBtn.setAlignment(Pos.CENTER);
                            hbBackBtn.getChildren().add(backBtn);

                            VBox vBox = new VBox();
                            vBox.getChildren().addAll(table);
                            vBox.getChildren().add(hbBackBtn);

                            Scene customersScene = new Scene(vBox);
                            primaryStage.setScene(customersScene);
                            primaryStage.setMinWidth(425);
                            primaryStage.setMinHeight(275);
                            primaryStage.show();

                            table.setOnMouseClicked(mouseEvent -> {
                                if (mouseEvent.getClickCount() == 2) {
                                    String userToBan = table.getSelectionModel().getSelectedItem().getUsername();

                                    // Ensure the admin can't ban themselves
                                    if (!usernameTF.getText().equalsIgnoreCase(userToBan)) {
                                        userStore.setBanned(table.getSelectionModel().getSelectedItem().getUsername());

                                        try {
                                            // Write the newly created structsss
                                            userStore.exportAccount();

                                            // Refresh the table
                                            providers.removeAll();
                                            FXCollections.copy(providers, userStore.getProviders());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });

                            backBtn.setOnAction(backEvent -> {
                                primaryStage.setScene(mainMenuScene);
                                primaryStage.show();
                            });
                        });

                        signoutBtn.setOnAction(signoutEvent -> {
                            primaryStage.setScene(loginScene);
                            primaryStage.show();
                        });
                    } else {
                        actiontarget.setFill(Color.FIREBRICK);
                        actiontarget.setText("Not an admin");
                    }
                } else {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Incorrect password");
                }
            } else {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("That username doesn't exist");
            }
        });

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}
