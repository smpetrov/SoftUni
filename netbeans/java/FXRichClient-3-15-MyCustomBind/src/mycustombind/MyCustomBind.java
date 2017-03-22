/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycustombind;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class MyCustomBind extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        DropShadow dropShadow = new DropShadow(10.0,Color.rgb(150,50,50,0.688));
        dropShadow.setOffsetX(4);
        dropShadow.setOffsetY(6);
        
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setEffect(dropShadow);
             
        Rectangle rectangle = new Rectangle(100,50,Color.LEMONCHIFFON);

        Text text = new Text();
        text.setFont(Font.font("Tahoma",FontWeight.BOLD,10));
        
        stackPane.getChildren().addAll(rectangle,text);
        
        final Scene scene = new Scene(stackPane, 400, 200,Color.LIGHTBLUE);
        primaryStage.setTitle("Custom Binding");
        
        rectangle.widthProperty().bind(scene.widthProperty().divide(2));
        rectangle.heightProperty().bind(scene.heightProperty().divide(2));
        
        DoubleBinding opacityBinding = new DoubleBinding(){
            {
                //List the dependencies with super.bind()
                super.bind(scene.widthProperty(),scene.heightProperty());
            }
            @Override
            protected double computeValue(){
                //Return the computed value
                double opacity = (scene.getWidth()+scene.getHeight())/1000;
                return (opacity > 1.0) ? 1.0:opacity;
            }
        };
        
        //Create binding object that return compute value 
        //that are not one of the standard types
        ObjectBinding<Color> colorBinding = new ObjectBinding<Color>(){
            {
                super.bind(rectangle.opacityProperty());
            }
            @Override
            protected Color computeValue(){
                if (rectangle.getOpacity() != 0){
                    return Color.rgb((int)(255*rectangle.getOpacity()),
                                     (int)(255*rectangle.getOpacity()),
                                     (int)(255*rectangle.getOpacity()));
                } else {
                    return Color.GREY;
                }
            }
        };
        
        scene.fillProperty().bind(colorBinding);
        
        rectangle.opacityProperty().bind(opacityBinding);
        text.textProperty().bind(Bindings.format("opacity= %.2f",opacityBinding));
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
