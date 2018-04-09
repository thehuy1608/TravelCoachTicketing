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
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.APIs.ValidateAPIs.ValidateInput;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Sign_up_step2_SceneController implements Initializable {

    @FXML
    private AnchorPane rootPane;
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
    private TextField txtAccount;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton btnToStep1_Side;
    @FXML
    private JFXButton btnToStep3_Side;
    @FXML
    private JFXButton btnToStep3;
    @FXML
    private JFXButton btnToStep1;
    @FXML
    private AnchorPane background;
    @FXML
    private Label lblUsernameMessage;
    @FXML
    private Label lblPasswordMessage;
    @FXML
    private Label lblConfirmPasswordMessage;
    @FXML
    private Label lblEmailMessage;
    @FXML
    private ImageView iconWarningUsername;
    @FXML
    private ImageView iconWarningPassword;
    @FXML
    private ImageView iconWarningConfirmPassword;
    @FXML
    private ImageView iconWarningEmail;

    private LoadingAnchorPane loading_anchor_pane;
    private final ValidateInput validation = new ValidateInput();

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
        set_hand_cursor_for_button(btnToStep3);
        set_hand_cursor_for_button(btnToStep1);
        set_hand_cursor_for_button(btnToStep3_Side);
        set_hand_cursor_for_button(btnToStep1_Side);

        //Add focus listener to Username Textfield to validate Username field when its focus property is lost.
        txtAccount.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                //TO-DO when account field is lost focused
                String account_text = txtAccount.getText().trim();
                validate_username_field(account_text);
            } else {
                //TO-DO when account field is focused
                iconWarningUsername.setVisible(false);
                lblUsernameMessage.setText("Tên tài khoản chứa 6-20 kí tự, bao gồm số và chữ cái.");
                lblUsernameMessage.setTextFill(Color.web("#c5bebe"));
            }
        });

        //Add focus listener to Email Textfield to validate when its focus property is lost
        txtEmail.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                //TO-DO when email field is lost focused
                String email = txtEmail.getText().trim();
                validate_email_field(email);
            } else {
                //TO-DO when email field is focused
                iconWarningEmail.setVisible(false);
                lblEmailMessage.setText("Email không được chứa khoảng trắng và bắt đầu bằng số.");
                lblEmailMessage.setTextFill(Color.web("#c5bebe"));
            }
        });

        //Add focus listener to Password field to validate when its focus property is lost
        txtPassword.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                //TO-DO when password field is lost focused
                String password = txtPassword.getText();
                validate_password_field(password);
            } else {
                //TO-DO when password field is focused
                iconWarningPassword.setVisible(false);
                lblPasswordMessage.setText("Mật khẩu chứa 8-30 kí tự, bao gồm số và chữ cái.");
                lblPasswordMessage.setTextFill(Color.web("#c5bebe"));
            }
        });

        //Add focus listener to Confirm Password Field to validate when its focus property is lost
        txtConfirmPassword.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                //TO-DO when confirm password field is lost focused
                String password = txtPassword.getText();
                String confirm_password = txtConfirmPassword.getText();
                validate_confirm_password_field(password, confirm_password);
            } else {
                //TO-DO when confirm password field is focused
                iconWarningConfirmPassword.setVisible(false);
                lblConfirmPasswordMessage.setText("Phải chứa mật khẩu trùng với ô mật khẩu.");
                lblConfirmPasswordMessage.setTextFill(Color.web("#c5bebe"));
            }
        });
    }

    //Set hand cursor for all button
    private void set_hand_cursor_for_button(JFXButton button) {
        button.setOnMouseEntered((Event event) -> {
            button.setCursor(Cursor.HAND);
        });
    }

    //Button action for btnToStep1, btnToStep1_Side
    //Back To Step 1
    @FXML
    private void back_to_step_1_button_action() {
        try {
            Stage current_stage = (Stage) btnHome.getScene().getWindow();
            StageController home_stage = new StageController();
            home_stage.configure_stage(current_stage, "/view/fxml/sign_up/sign_up_step1.fxml", "Minh Nhut Corporation", 1200, 800);
            current_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Button action for btnToStep3, btnToStep3_Side
    //Go To Step 3
    @FXML
    private void go_to_step_3_button_action() {
        String username = txtAccount.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirm_password = txtConfirmPassword.getText();

        boolean is_valid_username = validation.check_username(username);
        boolean is_valid_email = validation.check_mail(email);
        boolean is_valid_password = validation.check_password(password);
        boolean is_valid_confirm_password = password.equals(confirm_password);

        validate_username_field(username);
        validate_email_field(email);
        validate_password_field(password);
        validate_confirm_password_field(password, confirm_password);

        if (is_valid_username && is_valid_email && is_valid_password && is_valid_confirm_password) {
            //TO-DO when all of the data input is valid.
            try {
                Stage current_stage = (Stage) btnHome.getScene().getWindow();
                StageController home_stage = new StageController();
                home_stage.configure_stage(current_stage, "/view/fxml/sign_up/sign_up_step3.fxml", "Minh Nhut Corporation", 1200, 800);
                current_stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //TO-DO when something went wrong with data input
        }
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

    //Validate Function
    private void validate_username_field(String account_text) {
        //Check if the input text of account field is valid or not.
        if (validation.check_username(account_text)) {
            //TO-DO when text in account field is valid.
            iconWarningUsername.setVisible(false);
            lblUsernameMessage.setText("");
            lblUsernameMessage.setTextFill(Color.web("#c5bebe"));
        } else {
            //TO-DO when text in account field is invalid.
            iconWarningUsername.setVisible(true);
            lblUsernameMessage.setText("Tên tài khoản chỉ được chứa số và chữ cái, phải có ít nhất 6 kí tự.");
            lblUsernameMessage.setTextFill(Color.web("#ff0000"));
        }
    }

    private void validate_email_field(String email) {
        //Check if the input text of email field is valid or not.
        if (validation.check_mail(email)) {
            //TO-DO when text in email field is valid.
            iconWarningEmail.setVisible(false);
            lblEmailMessage.setText("");
            lblEmailMessage.setTextFill(Color.web("#c5bebe"));
        } else {
            //TO-DO when text in email field is invalid.
            iconWarningEmail.setVisible(true);
            lblEmailMessage.setText("Email không hợp lệ.");
            lblEmailMessage.setTextFill(Color.web("#ff0000"));
        }
    }

    private void validate_password_field(String password) {
        //Check if the input text of password field is valid or not.
        if (validation.check_password(password)) {
            //TO-DO when text in password field is valid.
            iconWarningPassword.setVisible(false);
            lblPasswordMessage.setText("");
            lblPasswordMessage.setTextFill(Color.web("#c5bebe"));
        } else {
            //TO-DO when text in password field is invalid.
            iconWarningPassword.setVisible(true);
            lblPasswordMessage.setText("Mật khẩu chỉ được chứa số và chữ cái, phải có 8-30 kí tự.");
            lblPasswordMessage.setTextFill(Color.web("#ff0000"));
        }
    }

    private void validate_confirm_password_field(String password, String confirm_password) {
        //Check if the input text of confirm password field is valid or not.
        if (confirm_password.equals(password)) {
            //TO-DO when text in confirm password field is valid.
            iconWarningConfirmPassword.setVisible(false);
            lblConfirmPasswordMessage.setText("");
            lblConfirmPasswordMessage.setTextFill(Color.web("#c5bebe"));
        } else {
            //TO-DO when text in confirm password field is invalid.
            iconWarningConfirmPassword.setVisible(true);
            lblConfirmPasswordMessage.setText("Phải chứa mật khẩu trùng với ô mật khẩu.");
            lblConfirmPasswordMessage.setTextFill(Color.web("#ff0000"));
        }
    }
}
