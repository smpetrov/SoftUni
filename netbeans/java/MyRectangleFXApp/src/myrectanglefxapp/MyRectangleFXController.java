/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrectanglefxapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
/**
 *
 * @author admin
 */
public class MyRectangleFXController implements Initializable {
    
    private RotateTransition rt;
    
    @FXML
    private Rectangle myrectangle;
    @FXML
    private StackPane stackpane;
    @FXML
    private void handleMouseClick(MouseEvent evt){
        rt.play();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myrectangle.setStrokeWidth(5.0);
        myrectangle.setStroke(Color.GOLDENROD);
        
        //Create and configure RotyateTransition rt
        rt = new RotateTransition(Duration.millis(3000),stackpane);
        rt.setToAngle(180);
        rt.setFromAngle(0);
        rt.setAutoReverse(true);
        rt.setCycleCount(4);
    }    
    
}
