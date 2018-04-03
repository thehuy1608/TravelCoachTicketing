/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.APIs.JSON_APIs;

import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.APIs.SecurityAPIs.Encryption;
import model.database.DAO.UsersDAO;
import model.database.POJO.Users;
import model.database.hibernate.HibernateUtil;

/**
 * Write temporary JSON file that will store the shared data among scenes of
 * Application like UserID.
 *
 * @author User
 */
public class WriteTempJSONFile {

    /**
     * Make a directory in User Document directory to store JSON file.
     */
    public static void make_temp_dir() {
        String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TravelBusTicketing";
        File documents_dir = new File(path);
        if (documents_dir.exists()) {
            System.out.println("Directory has already existed");
        } else if (documents_dir.mkdirs()) {
            System.out.println("Directory was created successfully.");
        } else {
            System.out.println("Error");
        }
    }
    
    /**
     *Write array byte to JSON writer stream
     * @param writer
     * @param array
     */
    public static void write_byte_array(JsonWriter writer, byte[] array) throws IOException {
        writer.beginArray();
        for (int i = 0; i < array.length; i++) {
            writer.value(array[i]);
        }
        writer.endArray();
    }

    /**
     * Write User object to the JSON writer stream.
     *
     * @param writer
     * @param user
     */
    public static void write_user_object(JsonWriter writer, Users user) {
        try {
            writer.beginObject();
            String user_id_string = Integer.toString(user.getUserId());
            byte[] hash_user_id_string = Encryption.encrypt_AES(user_id_string);
            writer.name("User ID");
            write_byte_array(writer, hash_user_id_string);
            if (user.getIsFacebookLogin() > 0) {
                writer.name("Account Type").value("Facebook account");
            }
            if (user.getIsGoogleLogin() > 0) {
                writer.name("Account Type").value("Google account");
            }
            if (user.getIsFacebookLogin() == 0 && user.getIsGoogleLogin() == 0) {
                writer.name("Account Type").value("Normal account");
            }
            writer.endObject();
        } catch (IOException ex) {
            Logger.getLogger(WriteTempJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write User object to the output stream
     *
     * @param out
     * @param user
     */
    public static void write_JSON_user_data_stream(OutputStream out, Users user) {
        try (JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"))) {
            writer.setIndent("    ");
            writer.setSerializeNulls(true);
            writer.setHtmlSafe(true);
            write_user_object(writer, user);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WriteTempJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WriteTempJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write User object to the JSON file.
     *
     * @param user
     */
    public static void write_JSON_user_data_file(Users user) {
        try {
            String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TravelBusTicketing" + File.separator + "User.json";
            File json_file = new File(path);
            Files.deleteIfExists(json_file.toPath());
            if (json_file.createNewFile()) {
                FileOutputStream fos = new FileOutputStream(json_file);
                write_JSON_user_data_stream(fos, user);
                System.out.println("Data has been added.");
            } else {
                System.out.println("Unknown Error");
            }
        } catch (IOException ex) {
            Logger.getLogger(WriteTempJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        Users user = UsersDAO.get_user_by_user_id(2);
        write_JSON_user_data_file(user);
        HibernateUtil.getSessionFactory().close();
    }
}
