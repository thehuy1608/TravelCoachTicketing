/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.scene_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.APIs.JSON_APIs.DAO.UserData;
import model.APIs.JSON_APIs.ReadTempJSONFile;
import model.APIs.SecurityAPIs.Encryption;

/**
 * FXML Controller class
 *
 * @author User
 */
public class HomeSceneController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private JFXButton btnMenu;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnAbout;
    @FXML
    private JFXButton btnTicketing;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnHelp;
    @FXML
    private AnchorPane contentP;
    @FXML
    private Hyperlink lblMore;
    @FXML
    private JFXButton btnOrderNow;
    @FXML
    private JFXDrawer drawer;

    private LoadingAnchorPane loading_anchor_pane;
    private UserData user_data;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //Add loading anchor pane to home scene and bring it to front while processing data from JSON file
        loading_anchor_pane = new LoadingAnchorPane();
        try {
            loading_anchor_pane.configure_pane(1200, 800, "#000000", 0.8);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootPane.getChildren().add(loading_anchor_pane);
        loading_anchor_pane.toFront();

        Task load_user_data_after_login = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                //Read the data from JSON file and add necessary data to Scene
                user_data = ReadTempJSONFile.read_JSON_user_data_file();
                return true;
            }
        };

        Thread thread = new Thread(load_user_data_after_login);
        thread.setDaemon(true);
        thread.start();

        load_user_data_after_login.setOnSucceeded((Event event) -> {
            //Add different side menu based on whether user is logged in or not
            if (user_data.get_logged_in()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/sidemenu_login.fxml"));
                    VBox box = loader.load();
                    Sidemenu_loginSceneController controller = loader.getController();
                    controller.set_username_label(Encryption.decrypt_AES(user_data.get_user_id()));
                    drawer.setSidePane(box);
                } catch (IOException e) {
                }
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/sidemenu_notlogin.fxml"));
                    VBox box = loader.load();
                    drawer.setSidePane(box);
                } catch (IOException e) {
                }
            }
            //Set hand cursor when mouse enters button
            set_hand_cursor_for_button(btnMenu);
            set_hand_cursor_for_button(btnHome);
            set_hand_cursor_for_button(btnAbout);
            set_hand_cursor_for_button(btnTicketing);
            set_hand_cursor_for_button(btnSearch);
            set_hand_cursor_for_button(btnHelp);
            set_hand_cursor_for_button(btnOrderNow);
            //Remove loading screen
            loading_anchor_pane.toBack();
        });

    }

    private void set_hand_cursor_for_button(JFXButton button) {
        button.setOnMouseEntered((Event event) -> {
            button.setCursor(Cursor.HAND);
        });
    }

    @FXML
    private void toggle_side_menu() {
        drawer.toggle();
    }

}
