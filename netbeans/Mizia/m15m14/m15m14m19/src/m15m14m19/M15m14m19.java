/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m15m14m19;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class M15m14m19 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        FileChooser selectedFiles = new FileChooser();
        selectedFiles.setTitle("Избери Файла за модифициране");
        
        Button btn = new Button();
        btn.setText("Модифициране на файловете /премахване *M15*M14/");
        
        btn.setText("Модифициране на Файловете /премахване *M15*M14/");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                List<File> list = selectedFiles.showOpenMultipleDialog(primaryStage);
                if (list != null) {
                    for (File file : list){
                        List<String> listLines = new ArrayList<>();
                        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                            String lineOrg;
                            String lineUp;
                            String lineMod;
                            while ((lineOrg = reader.readLine()) != null){
                                lineUp = lineOrg.toUpperCase();
                                lineMod = lineUp.replaceAll("\\*M15\\*M14\\*M19", "*M19");
                                listLines.add(lineMod);
                            }
                            
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                        
                        try (FileWriter writer = new FileWriter(file.getAbsolutePath())){
                            for (String line : listLines){
                                writer.write(line);
                            }
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    JOptionPane.showMessageDialog(null,"Модифицирането приключи !");
                    System.exit(0);
                }
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        
        Scene scene = new Scene(root, 400, 350);
        
        primaryStage.setTitle("Модифициране на кътерски файлове:");
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
