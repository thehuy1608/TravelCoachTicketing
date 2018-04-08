/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.stage_controller.StageController;
import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import model.APIs.JSON_APIs.DAO.UserData;
import model.APIs.JSON_APIs.WriteTempJSONFile;
import model.APIs.SecurityAPIs.Encryption;

/**
 *
 * @author User
 */
public class ApplicationInitialization extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {        
        if (make_data_directory()) {
            create_initial_JSON_file();
        }
        StageController login_stage = new StageController();
        login_stage.configure_stage(primaryStage, "/view/fxml/sign_up/sign_up_step1.fxml", "Minh Nhut Corporation", 1200, 800);

        primaryStage.show();
    }
    
    //Create a local directory in Documents to store JSON file when user starts application.
    //Returns true if the directory has already existed or created successfully. Otherwise false.
    private static boolean make_data_directory() {
        String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TravelBusTicketing";
        File documents_dir = new File(path);
        if (documents_dir.exists()) {
            return true;
        } else {
            return documents_dir.mkdirs();
        }
    }
    
    private static void create_initial_JSON_file() {
        boolean is_logged_in = false;
        byte[] hash_user_id = Encryption.encrypt_AES("0");
        String user_type = "Guest";
        String current_scene = "index";
        UserData user_data = new UserData(is_logged_in, hash_user_id, user_type, current_scene);
        
        WriteTempJSONFile.write_JSON_user_data_file(user_data); 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
