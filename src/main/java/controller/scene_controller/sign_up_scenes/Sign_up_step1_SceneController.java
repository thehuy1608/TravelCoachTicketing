/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.scene_controller.sign_up_scenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import controller.scene_controller.LoadingAnchorPane;
import controller.scene_controller.LoginSceneController;
import controller.stage_controller.StageController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Sign_up_step1_SceneController implements Initializable {

    @FXML
    private AnchorPane background;
    @FXML
    private JFXButton btnMenu;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnAbout;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnHelp;
    @FXML
    private JFXButton btnDirectlySignUp;
    @FXML
    private JFXButton btnGGSignUp;
    @FXML
    private JFXButton btnFBSignUp;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton btnTicketing;

    private LoadingAnchorPane loading_anchor_pane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //Add loading anchor pane
        loading_anchor_pane = new LoadingAnchorPane();
        try {
            loading_anchor_pane.configure_pane(1200, 800, "#000000", 0.8);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootPane.getChildren().add(loading_anchor_pane);
        loading_anchor_pane.toBack();
        loading_anchor_pane.setCursor(Cursor.WAIT);

        //Add non-login side menu
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/view/fxml/sidemenu_notlogin.fxml"));
            drawer.setSidePane(box);
        } catch (IOException e) {
        }

        //Set hand cursor when mouse enters button
        set_hand_cursor_for_button(btnMenu);
        set_hand_cursor_for_button(btnHome);
        set_hand_cursor_for_button(btnAbout);
        set_hand_cursor_for_button(btnTicketing);
        set_hand_cursor_for_button(btnSearch);
        set_hand_cursor_for_button(btnHelp);
        set_hand_cursor_for_button(btnDirectlySignUp);
        set_hand_cursor_for_button(btnGGSignUp);
        set_hand_cursor_for_button(btnFBSignUp);
    }

    //Set hand cursor for all button
    private void set_hand_cursor_for_button(JFXButton button) {
        button.setOnMouseEntered((Event event) -> {
            button.setCursor(Cursor.HAND);
        });
    }

    //Button action for btnMenu
    //Toggle side menu when click on btnMenu
    @FXML
    private void toggle_side_menu() {
        drawer.toggle();
    }

    //Button action for btnHome
    //Display home scene when click on btnHome on the horizontal menu bar
    @FXML
    private void home_button_action() {
        try {
            Stage current_stage = (Stage) btnHome.getScene().getWindow();
            StageController home_stage = new StageController();
            home_stage.configure_stage(current_stage, "/view/fxml/home.fxml", "Minh Nhut Corporation", 1200, 800);
            current_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Button action for btnDirectlySignUp.
    //Display sign_up_step2 scene.
    @FXML
    private void directly_sign_up_button_action() {

        Stage current_stage = (Stage) btnHome.getScene().getWindow();
        try {
            StageController stage_controller = new StageController();
            stage_controller.configure_stage(current_stage, "/view/fxml/sign_up/sign_up_step2.fxml", "Minh Nhut Corporation", 1200, 800);
            current_stage.show();
            System.out.println("123");
        } catch (IOException ex) {
            Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
