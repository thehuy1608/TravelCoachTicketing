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
import javax.crypto.CipherOutputStream;
import model.APIs.JSON_APIs.DAO.UserData;
import model.APIs.SecurityAPIs.Encryption;

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
    private static void make_temp_dir() {
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
     * Write array byte to JSON writer stream
     *
     * @param writer
     * @param array
     * @throws java.io.IOException
     */
    public static void write_byte_array(JsonWriter writer, byte[] array) throws IOException {
        writer.beginArray();
        for (int i = 0; i < array.length; i++) {
            writer.value(array[i]);
        }
        writer.endArray();
    }

    /**
     * Write UserData object to the JSON writer stream.
     *
     * @param writer
     * @param user_data
     */
    public static void write_user_data_object(JsonWriter writer, UserData user_data) {
        try {
            writer.beginObject();
            writer.name("is_logged_in").value(user_data.get_logged_in());
            writer.name("user_id");
            if (user_data.get_user_id() == null) {
                writer.nullValue();
            } else {
                write_byte_array(writer, user_data.get_user_id());
            }

            writer.name("user_type").value(user_data.get_user_type());
            writer.name("current_scene").value(user_data.get_current_scene());
            writer.endObject();
        } catch (IOException ex) {
            Logger.getLogger(WriteTempJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write User object to the output stream
     *
     * @param out
     * @param user_data
     */
    public static void write_JSON_user_data_stream(OutputStream out, UserData user_data) {
        try (JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"))) {
            writer.setIndent("    ");
            writer.setSerializeNulls(true);
            writer.setHtmlSafe(true);
            write_user_data_object(writer, user_data);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WriteTempJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WriteTempJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write User object to the JSON file with AES encryption.
     *
     * @param user_data
     */
    public static void write_JSON_user_data_file(UserData user_data) {
        try {
            String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TravelBusTicketing" + File.separator + "User.json";
            File json_file = new File(path);
            Files.deleteIfExists(json_file.toPath());
            if (json_file.createNewFile()) {               
                FileOutputStream fos = new FileOutputStream(json_file);                
                CipherOutputStream cos = new CipherOutputStream(fos, Encryption.get_encrypt_cipher());
                write_JSON_user_data_stream(cos, user_data);
                System.out.println("Data has been added.");
            } else {
                System.out.println("Unknown Error");
            }
        } catch (IOException ex) {
            Logger.getLogger(WriteTempJSONFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
