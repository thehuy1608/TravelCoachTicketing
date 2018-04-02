/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.scene_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //Add side-menu
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/view/fxml/sidemenu_login.fxml"));
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
