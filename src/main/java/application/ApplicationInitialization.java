/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.stage_controller.StageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class ApplicationInitialization extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {        
        StageController login_stage = new StageController();
        login_stage.configure_stage(primaryStage, "/view/fxml/login.fxml", "Minh Nhut Corporation", 1200, 800);

        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
