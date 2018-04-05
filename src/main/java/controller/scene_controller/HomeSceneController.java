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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loading_anchor_pane = new LoadingAnchorPane();
        try {
            loading_anchor_pane.configure_pane(1200, 800, "#000000", 0.8);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootPane.getChildren().add(loading_anchor_pane);
        loading_anchor_pane.toFront();

//        Thread javafx_application_thread = Thread.currentThread();
//        try {
//            javafx_application_thread.wait();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(HomeSceneController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //Add side-menu
//        try {
//            VBox box = FXMLLoader.load(getClass().getResource("/view/fxml/sidemenu_login.fxml"));
//            drawer.setSidePane(box);
//        } catch (IOException e) {
//        }
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
        set_hand_cursor_for_button(btnOrderNow);
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
