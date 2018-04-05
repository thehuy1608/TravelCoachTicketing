/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.scene_controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Sidemenu_loginSceneController implements Initializable {

    @FXML
    private Label lblUsername;
    @FXML
    private Label lblProfile;
    @FXML
    private Label lblTripHistory;
    @FXML
    private Label lblPromotion;
    @FXML
    private Label lblHelp;
    @FXML
    private Label lblSignOut;

    public void set_username_label(String username) {
        this.lblUsername.setText(username);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
