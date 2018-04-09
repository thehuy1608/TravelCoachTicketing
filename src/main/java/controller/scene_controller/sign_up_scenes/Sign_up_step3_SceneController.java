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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.APIs.ValidateAPIs.ValidateInput;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Sign_up_step3_SceneController implements Initializable {

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
    private TextField txtAddress;
    @FXML
    private RadioButton rdoMale;
    @FXML
    private ToggleGroup rdoSex;
    @FXML
    private RadioButton rdoFemale;
    @FXML
    private DatePicker txtBirthday;
    @FXML
    private AnchorPane background;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private JFXButton btnToStep2_Side;
    @FXML
    private JFXButton btnToStep4_Side;
    @FXML
    private Label lblFirstNameMessage;
    @FXML
    private TextField txtLastName;
    @FXML
    private Label lblLastNameMessage;
    @FXML
    private Label lblBirthdayMessage;
    @FXML
    private Label lblPhoneNumberMessage;
    @FXML
    private Label lblAddressMessage;
    @FXML
    private ImageView iconWarningFirstName;
    @FXML
    private ImageView iconWarningLastName;
    @FXML
    private ImageView iconWarningBirthday;
    @FXML
    private ImageView iconWarningPhoneNumber;
    @FXML
    private ImageView iconWarningAddress;
    @FXML
    private Label lblGenderMessage;
    @FXML
    private ImageView iconWarningGender;
    @FXML
    private JFXButton btnToStep4;
    @FXML
    private JFXButton btnToStep2;
    @FXML
    private JFXDrawer drawer;

    private LoadingAnchorPane loading_anchor_pane;
    private final ValidateInput validation = new ValidateInput();
    private final String date_pattern = "dd/MM/yyyy";

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
        set_hand_cursor_for_button(btnToStep4);
        set_hand_cursor_for_button(btnToStep2);
        set_hand_cursor_for_button(btnToStep4_Side);
        set_hand_cursor_for_button(btnToStep2_Side);
        rdoMale.setCursor(Cursor.HAND);
        rdoFemale.setCursor(Cursor.HAND);

        //Set dd-mm-yyyy date format for birthday date picker
        txtBirthday.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern(date_pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return date_formatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, date_formatter);
                } else {
                    return null;
                }
            }
        });

        //Add focus listener to FirstName Textfield to validate FirstName field when its focus property is lost.
        txtFirstName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                //TO-DO when first name field is lost focused
                String first_name = txtFirstName.getText().trim();
                validate_first_name_field(first_name);
            } else {
                //TO-DO when first name field is focused
                iconWarningFirstName.setVisible(false);
                lblFirstNameMessage.setText("Họ và tên lót không được chứa kí tự đặc biệt.");
                lblFirstNameMessage.setTextFill(Color.web("#c5bebe"));
            }
        });

        //Add focus listener to LastName Textfield to validate LastName field when its focus property is lost.
        txtLastName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                //TO-DO when last name field is lost focused
                String last_name = txtLastName.getText().trim();
                validate_last_name_field(last_name);
            } else {
                //TO-DO when last name field is focused
                iconWarningLastName.setVisible(false);
                lblLastNameMessage.setText("Tên không được chứa kí tự đặc biệt.");
                lblLastNameMessage.setTextFill(Color.web("#c5bebe"));
            }
        });

        //Add focus listener to Birthday Textfield to validate Birthday field when its focus property is lost.
        txtBirthday.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                //TO-DO when birthday field is lost focused
                LocalDate local_date = txtBirthday.getValue();
                String birthday = "";
                if (local_date != null) {
                    birthday = local_date.getDayOfMonth() + "/" + local_date.getMonthValue() + "/" + local_date.getYear();
                }
                validate_birthday_field(birthday);
            } else {
                //TO-DO when birthday field is focused
                iconWarningBirthday.setVisible(false);
                lblBirthdayMessage.setText("Ngày sinh có định dạng:   dd/mm/yyyy");
                lblBirthdayMessage.setTextFill(Color.web("#c5bebe"));
            }
        });

        //Add focus listener to PhoneNumber TextField to validate Phone Number field when its focus property is lost.
        txtPhoneNumber.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                //TO-DO when phone number field is lost focused
                String phone_number = txtPhoneNumber.getText().trim();
                validate_phone_number_field(phone_number);
            } else {
                //TO-DO when phone number field is focused
                iconWarningPhoneNumber.setVisible(false);
                lblPhoneNumberMessage.setText("Số điện thoại không được chứa chữ cái và kí tự đặc biệt.");
                lblPhoneNumberMessage.setTextFill(Color.web("#c5bebe"));
            }
        });

        //Add focus listener to Address TextField to validate Address field when its focus property is lost.
        txtAddress.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                //TO-DO when address field is lost focused
                String address = txtAddress.getText().trim();
                validate_address_field(address);
            } else {
                //TO-DO when address field is focused
                iconWarningAddress.setVisible(false);
                lblAddressMessage.setText("Địa chỉ không được phép để trống và chứa kí tự đặc biệt.");
                lblAddressMessage.setTextFill(Color.web("#c5bebe"));
            }
        });

        //Hide the error message when the gender is chosen
        rdoMale.onMouseClickedProperty().set((EventHandler) (Event event) -> {
            //TO-DO when one gender radio button is checked
            iconWarningGender.setVisible(false);
            lblGenderMessage.setText("");
            lblGenderMessage.setTextFill(Color.web("#c5bebe"));
        });
        
        rdoFemale.onMouseClickedProperty().set((EventHandler) (Event event) -> {
            //TO-DO when one gender radio button is checked
            iconWarningGender.setVisible(false);
            lblGenderMessage.setText("");
            lblGenderMessage.setTextFill(Color.web("#c5bebe"));
        });
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

    //Button action for btnToStep4, btnToStep4_Side
    //Check if the form is filled with valid input first. If true, go to step 4. If false, prompt error text
    @FXML
    private void go_to_step_4_button_action() {
        String first_name = txtFirstName.getText();
        String last_name = txtLastName.getText();
        String address = txtAddress.getText();
        String phone_number = txtPhoneNumber.getText();
        String birthday = "";
        boolean is_selected_male = rdoMale.isSelected();
        boolean is_selected_female = rdoFemale.isSelected();

        LocalDate local_date = txtBirthday.getValue();
        if (local_date != null) {
            birthday = local_date.getDayOfMonth() + "/" + local_date.getMonthValue() + "/" + local_date.getYear();
        }

        validate_first_name_field(first_name);
        validate_last_name_field(last_name);
        validate_address_field(address);
        validate_phone_number_field(phone_number);
        validate_birthday_field(birthday);
        if (is_selected_female == false && is_selected_male == false) {
            validate_gender_field(false);
        }

        boolean is_valid_first_name = validation.check_name(first_name);
        boolean is_valid_last_name = validation.check_name(last_name);
        boolean is_valid_address = validation.check_address(address);
        boolean is_valid_phone_number = validation.check_phone(phone_number);
        boolean is_valid_birthday = validation.check_date(birthday);
        boolean is_valid_gender = is_selected_female == true || is_selected_male == true;

        if (is_valid_first_name && is_valid_last_name && is_valid_address && is_valid_phone_number && is_valid_birthday && is_valid_gender) {
            //TO-DO when all of the data input is valid
            try {
                Stage current_stage = (Stage) btnHome.getScene().getWindow();
                StageController home_stage = new StageController();
                home_stage.configure_stage(current_stage, "/view/fxml/sign_up/sign_up_step4.fxml", "Minh Nhut Corporation", 1200, 800);
                current_stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //TO-DO when something went wrong with data input
        }

    }

    //Validate Fucntion
    private void validate_first_name_field(String first_name) {
        //Check if the input text of first name field is valid or not.
        if (validation.check_name(first_name)) {
            //TO-DO when text in first name field is valid.
            iconWarningFirstName.setVisible(false);
            lblFirstNameMessage.setText("");
            lblFirstNameMessage.setTextFill(Color.web("#c5bebe"));
        } else {
            //TO-DO when text in first name field is invalid.
            iconWarningFirstName.setVisible(true);
            lblFirstNameMessage.setText("Họ và tên lót không hợp lệ.");
            lblFirstNameMessage.setTextFill(Color.web("#ff0000"));
        }
    }

    private void validate_last_name_field(String last_name) {
        //Check if the input text of last name field is valid or not
        if (validation.check_name(last_name)) {
            //TO-DO when text in last name field is valid.
            iconWarningLastName.setVisible(false);
            lblLastNameMessage.setText("");
            lblLastNameMessage.setTextFill(Color.web("#c5bebe"));
        } else {
            //TO-DO when text in last name field is invalid.
            iconWarningLastName.setVisible(true);
            lblLastNameMessage.setText("Tên không hợp lệ.");
            lblLastNameMessage.setTextFill(Color.web("#ff0000"));
        }
    }

    private void validate_gender_field(boolean is_checked_gender) {
        //Check if user chooses gender or not
        if (is_checked_gender) {
            //TO-DO when one gender radio button is checked
            iconWarningGender.setVisible(false);
            lblGenderMessage.setText("");
            lblGenderMessage.setTextFill(Color.web("#c5bebe"));
        } else {
            //TO-DO when user hasn't checked the gender field yet
            iconWarningGender.setVisible(true);
            lblGenderMessage.setText("Bạn chưa chọn giới tính.");
            lblGenderMessage.setTextFill(Color.web("#ff0000"));
        }
    }

    private void validate_birthday_field(String birthday) {
        //Check if the input text of birthday field is valid or not
        if (validation.check_date(birthday)) {
            //TO-DO when text in birthday field is valid.
            iconWarningBirthday.setVisible(false);
            lblBirthdayMessage.setText("");
            lblBirthdayMessage.setTextFill(Color.web("#c5bebe"));
        } else {
            //TO-DO when text in birthday field is invalid.
            iconWarningBirthday.setVisible(true);
            lblBirthdayMessage.setText("Ngày sinh không hợp lệ.");
            lblBirthdayMessage.setTextFill(Color.web("#ff0000"));
        }
    }

    private void validate_address_field(String address) {
        //Check if the input text of address field is valid or not
        if (validation.check_address(address)) {
            //TO-DO when text in address field is valid.
            iconWarningAddress.setVisible(false);
            lblAddressMessage.setText("");
            lblAddressMessage.setTextFill(Color.web("#c5bebe"));
        } else {
            //TO-DO when text in address field is invalid.
            iconWarningAddress.setVisible(true);
            lblAddressMessage.setText("Địa chỉ không hợp lệ.");
            lblAddressMessage.setTextFill(Color.web("#ff0000"));
        }
    }

    private void validate_phone_number_field(String phone_number) {
        //Check if the input text of phone number field is valid or not
        if (validation.check_phone(phone_number)) {
            //TO-DO when text in address field is valid.
            iconWarningPhoneNumber.setVisible(false);
            lblPhoneNumberMessage.setText("");
            lblPhoneNumberMessage.setTextFill(Color.web("#c5bebe"));
        } else {
            //TO-DO when text in address field is invalid.
            iconWarningPhoneNumber.setVisible(true);
            lblPhoneNumberMessage.setText("Số điện thoại không hợp lệ.");
            lblPhoneNumberMessage.setTextFill(Color.web("#ff0000"));
        }
    }

}
