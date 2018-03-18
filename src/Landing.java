import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Landing extends Application {
    private final int WIDTH = 800;
    private final int HEIGHT = 500;

    public void start(Stage primaryStage) throws Exception {
        Button provider = new Button("I am a Provider");
        Button customer = new Button("I am a Customer");
        Button admin = new Button("Administration");
        Label label = new Label("Who are you?");

        customer.setOnAction(e ->{
            try {
                new CustomerMain().start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //todo add electric main
        provider.setOnAction(e -> {
            try {
                new ElectricMain().start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //todo add admin main
        admin.setOnAction(e->{
            try {
               new AdminMain().start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label,customer, provider,admin);

        Scene landing = new Scene(layout, WIDTH, HEIGHT);
        primaryStage.setScene(landing);
        primaryStage.show();
    }


}
