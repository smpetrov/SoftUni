
package fxparamapp;

import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FXParamApp extends Application {
    
    @Override
    public void start(Stage stage) {
        //get application parameters
        Parameters p = this.getParameters();
        Map<String,String> namedParams = p.getNamed();
        List<String> unnamedParams = p.getUnnamed();
        List<String> rawParams = p.getRaw();
        
        String paramStr = "Named Parameters: " + namedParams + "\n" +
                "UnnamedParams: " + unnamedParams + "\n" +
                "Raw Parameters: " + rawParams;
        
        TextArea ta = new TextArea(paramStr);
        Group root = new Group(ta);
        stage.setScene(new Scene(root));
        stage.setTitle("Application Parameters");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
