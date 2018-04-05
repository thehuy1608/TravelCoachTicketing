/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.APIs.JSON_APIs;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import model.APIs.JSON_APIs.DAO.UserData;

/**
 * Read temporary JSON file that will store the shared data among scenes of
 * Application like UserID.
 *
 * @author User
 */
public class ReadTempJSONFile {

    /**
     * Read byte array from JSON input stream
     *
     * @param reader
     * @return
     * @throws IOException
     */
    public static List<Byte> read_byte_array(JsonReader reader) throws IOException {
        List<Byte> byte_array = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            byte_array.add(Byte.parseByte(reader.nextString()));
        }
        reader.endArray();
        return byte_array;
    }

    /**
     * Read the UserData object in JSON file input stream
     *
     * @param reader
     * @return
     * @throws IOException
     */
    public static UserData read_user_data_object(JsonReader reader) throws IOException {
        boolean is_logged_in = false;
        byte[] user_id = null;
        String user_type = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            JsonToken token = reader.peek();
            if (token == JsonToken.NULL) {
                reader.skipValue();
            } else {
                switch (name) {
                    case "User ID":
                        user_id = convert_byte_list_to_byte_array(read_byte_array(reader));
                        break;
                    case "Is Logged In":
                        is_logged_in = reader.nextBoolean();
                        break;
                    case "Account Type":
                        user_type = reader.nextString();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
        }
        UserData user_data = new UserData(is_logged_in, user_id, user_type);
        return user_data;
    }

    /**
     * Read the Users ID object in JSON file from input stream and return the
     * hash code of user ID
     *
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static UserData read_JSON_user_data_file() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TravelBusTicketing" + File.separator + "User.json";
        InputStream fis = new FileInputStream(path);
        JsonReader reader = new JsonReader(new InputStreamReader(fis, "UTF-8"));
        UserData user_data = read_user_data_object(reader);
        return user_data;
    }

    //Simple function to convert an primtive type list to array.
    private static byte[] convert_byte_list_to_byte_array(List<Byte> byte_list) {
        byte[] byte_array = new byte[byte_list.size()];
        for (int i = 0; i < byte_list.size(); i++) {
            byte_array[i] = byte_list.get(i);
        }
        return byte_array;
    }
}
