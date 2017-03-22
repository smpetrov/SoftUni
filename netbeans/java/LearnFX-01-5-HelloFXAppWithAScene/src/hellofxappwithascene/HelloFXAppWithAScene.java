package hellofxappwithascene;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloFXAppWithAScene extends Application {
    
    @Override
    public void start(Stage stage) {
        Text msg = new Text("Hello JavaFX!");
        VBox root = new VBox();
        root.getChildren().add(msg);
        
        Button exitBtn = new Button();
        exitBtn.setText("Exit");
        
        //Using a lambda expression
        exitBtn.setOnAction(e->Platform.exit());
        
        /*
        //Using an anonymous class
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        */
        root.getChildren().add(exitBtn);
                
        
        
        Scene scene = new Scene(root,300,50);
        stage.setScene(scene);
        stage.setTitle("Hello JavaFX Application with a Scene.");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
