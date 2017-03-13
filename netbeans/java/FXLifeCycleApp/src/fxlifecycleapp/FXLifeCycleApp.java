
package fxlifecycleapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class FXLifeCycleApp extends Application {
    
    public FXLifeCycleApp(){
        String name = Thread.currentThread().getName();
        System.out.println("FXLifeCycleApp() constructor: " + name);;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init(){
        String name = Thread.currentThread().getName();
        System.out.println("unit() method: " + name);;
    }
    
    @Override
    public void start(Stage stage) {
        String name = Thread.currentThread().getName();
        System.out.println("start() method: " + name);;
        
        Scene scene = new Scene(new Group(), 200, 200);
        stage.setScene(scene);
        stage.setTitle("JavaFX Application Life Cycle!");
        stage.show();
    }

    @Override
    public void stop(){
        String name = Thread.currentThread().getName();
        System.out.println("stop() method: " + name);;

    }
    
}
