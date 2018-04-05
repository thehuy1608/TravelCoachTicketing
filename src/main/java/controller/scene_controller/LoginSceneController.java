/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.scene_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.stage_controller.StageController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.APIs.JSON_APIs.DAO.UserData;
import model.APIs.JSON_APIs.WriteTempJSONFile;
import model.APIs.SecurityAPIs.Encryption;
import model.APIs.ValidateAPIs.ValidateInput;
import model.database.DAO.LoginInfoDAO;
import model.database.POJO.Users;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LoginSceneController implements Initializable {

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
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private Hyperlink lblForgot;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private AnchorPane background;
    @FXML
    private Label lblUsernameMessage;
    @FXML
    private Label lblPasswordMessage;
    @FXML
    private JFXDrawer drawer;

    private final ValidateInput validation = new ValidateInput();
    private LoadingAnchorPane loading_anchor_pane;
    private long is_valid_login = 0;
    private final Border error_border = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT));

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
            VBox box = FXMLLoader.load(getClass().getResource("/view/fxml/sidemenu_notlogin.fxml"));
            drawer.setSidePane(box);
        } catch (IOException e) {
        }

        //Set login button disabled, until user fill valid input in textfields.
        btnLogin.setDisable(true);

        //Set hand cursor when mouse enters button
        set_hand_cursor_for_button(btnMenu);
        set_hand_cursor_for_button(btnHome);
        set_hand_cursor_for_button(btnAbout);
        set_hand_cursor_for_button(btnTicketing);
        set_hand_cursor_for_button(btnSearch);
        set_hand_cursor_for_button(btnHelp);
        set_hand_cursor_for_button(btnLogin);

        //Add a loading pane and bring it to the very back of application
        loading_anchor_pane = new LoadingAnchorPane();
        try {
            loading_anchor_pane.configure_pane(1200, 800, "#000000", 0.8);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootPane.getChildren().add(loading_anchor_pane);
        loading_anchor_pane.toBack();

        //Add validate to Username/Email and Password textfield
        /**
         * ALLOW UPPER , LOWER CASE NUMBER -,_ RANGE 6-20 AND EMAIL
         */
        txtPassword.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String password = txtPassword.getText();

            if (password.length() >= 8) {
                lblPasswordMessage.setText("");
                btnLogin.setDisable(false);
            } else {
                lblPasswordMessage.setText("Mật khẩu chứa ít nhất 8 kí tự, bao gồm chữ cái và số.");
                btnLogin.setDisable(true);
            }
        });

        txtUsername.focusedProperty().addListener((new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    //TO-DO when username field is lost focused
                    String username = txtUsername.getText().trim();

                    if (validation.check_username(username) || validation.check_mail(username)) {
                        lblUsernameMessage.setText("");
                        txtUsername.setBorder(Border.EMPTY);
                    } else if (username.isEmpty()) {
                        lblUsernameMessage.setTextFill(Color.web("#ff0000"));
                        lblUsernameMessage.setText("Tên đăng nhập/Email không được phép để trống");
                        btnLogin.setDisable(true);
                        txtUsername.setBorder(error_border);
                    } else {
                        lblUsernameMessage.setTextFill(Color.web("#ff0000"));
                        lblUsernameMessage.setText("Tên đăng nhập/Email không được phép chứa\nkhoảng cách và kí tự đặc biệt.");
                        btnLogin.setDisable(true);
                        txtUsername.setBorder(error_border);
                    }
                } else {
                    //TO-DO when username field is gain focused
                    txtUsername.setBorder(Border.EMPTY);
                    lblUsernameMessage.setTextFill(Color.web("#c5bebe"));
                    lblUsernameMessage.setText("Tên đăng nhập/Email nằm trong khoảng 6-20 kí tự\nbao gồm chữ cái và số.");
                }
            }
        }));

        /**
         * Password length > 8 must content number, upper and lower case
         *
         */
        txtPassword.focusedProperty().addListener((new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    //TO-DO when password field is lost focused
                    lblPasswordMessage.setText("");
                } else {
                    //TO-DO when password field is focused
                    lblPasswordMessage.setText("Mật khẩu chứa ít nhất 8 kí tự, bao gồm chữ cái và số.");
                }
            }
        }));
    }

    //Set hand cursor for all button
    private void set_hand_cursor_for_button(JFXButton button) {
        button.setOnMouseEntered((Event event) -> {
            button.setCursor(Cursor.HAND);
        });
    }

    @FXML
    private void login_button_action() {
        //Get the login information input
        String login_name = txtUsername.getText();
        String login_password = txtPassword.getText();
        //Display a loading screen     
        loading_anchor_pane.toFront();
        //Running background process (database connection) in another thread
        Task check_login_task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                //Set the cursor to type WAIT
                Scene current_scene = btnLogin.getScene();
                current_scene.setCursor(Cursor.WAIT);

                //Check user's login info
                is_valid_login = LoginInfoDAO.check_login(login_name, login_password);
                if (is_valid_login > 0) {
                    Users user = LoginInfoDAO.get_user_by_login_name_and_password(login_name, login_password);
                    boolean is_logged_in = true;
                    byte[] hash_user_id = Encryption.encrypt_AES(user.getUserId().toString());
                    String account_type = "Normal Account";
                    if (user.getIsFacebookLogin() > 0) {
                        account_type = "Facebook Account";
                    } else if (user.getIsGoogleLogin() > 0) {
                        account_type = "Google Account";
                    }
                    String next_scene = "home";

                    UserData user_data = new UserData(is_logged_in, hash_user_id, account_type, next_scene);
                    WriteTempJSONFile.write_JSON_user_data_file(user_data);
                }
                return null;
            }
        };

        Thread thread = new Thread(check_login_task);
        thread.setDaemon(true);
        thread.start();

        check_login_task.setOnSucceeded((Event event) -> {
            loading_anchor_pane.toBack();
            Stage current_stage = (Stage) btnLogin.getScene().getWindow();
            //If the login is valid, redirect to the home page, otherwise display an error message.
            if (is_valid_login > 0) {
                try {
                    StageController home_stage = new StageController();
                    home_stage.configure_stage(current_stage, "/view/fxml/home.fxml", "Minh Nhut Corporation", 1200, 800);
                    current_stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                //Reset the Login UI Form, but keep the username.
                btnLogin.getScene().setCursor(Cursor.DEFAULT);
                txtUsername.setText(login_name);
                txtPassword.setText("");

                //Display an alert dialog.
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("LỖI XÁC THỰC");
                alert.setHeaderText("Tên đăng nhập hoặc mật khẩu không đúng");
                alert.setContentText("Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.");
                alert.showAndWait();
            }
        });
    }

    @FXML
    private void home_button_action() {
        try {
            Stage current_stage = (Stage) btnLogin.getScene().getWindow();
            StageController home_stage = new StageController();
            home_stage.configure_stage(current_stage, "/view/fxml/home.fxml", "Minh Nhut Corporation", 1200, 800);
            current_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void toggle_side_menu() {
        drawer.toggle();
    }

}
