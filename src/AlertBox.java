import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AlertBox {

    public static void display(String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Alert!");
        window.setMinWidth(250);

        Label label1 = new Label(message);
        Button okay = new Button("Okay");
        okay.setOnAction(e -> window.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label1, okay);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}

class ReviewBox{
    public static void display(User provider, boolean isProvider, String username){
        ArrayList<Comments> reviews = provider.getReviews();
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Reviews for :" + provider.getFirstName());
        window.setMinWidth(250);

        //Button close = new Button("Close");
        Button leave = new Button("Leave A review");
        TextField words = new TextField();

        ScrollPane scp = new ScrollPane();
        VBox layout = new VBox(20);

        layout.getChildren().addAll(words, leave);

        leave.setOnAction(e->{
            provider.addReview(words.getText(), username);
        });

        if(reviews != null) {
            for (Comments review : reviews) {
                layout.getChildren().add(new Label(review.getUserName() + ": " + review.getComment()));
                layout.getChildren().add(new Label("        " + provider.getUsername() + ": " + review.getReply()));
                if (isProvider) {
                    Button temp = new Button("Reply");
                    TextField temp2 = new TextField();
                    layout.getChildren().add(temp2);
                    temp.setOnAction(e -> {
                        review.setReply(temp2.getText());
                    });
                }
            }
        }
        scp.setContent(layout);
        Scene scene = new Scene(scp, 500, 800);

        window.setScene(scene);
        window.showAndWait();
    }
}