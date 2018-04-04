/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.APIs.JSON_APIs;

import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import model.APIs.SecurityAPIs.Encryption;

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
     * Read the Users ID object in JSON file and return the hash code of user ID
     *
     * @param reader
     * @return
     * @throws IOException
     */
    public static List<Byte> read_hash_user_id(JsonReader reader) throws IOException {
        List<Byte> hash_user_id = new ArrayList<>();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equalsIgnoreCase("User ID")) {
                hash_user_id = read_byte_array(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return hash_user_id;
    }

    /**
     *Read the Users ID object in JSON file from input stream and return the hash code of user ID
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static List<Byte> read_JSON_user_data_file() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        List<Byte> hash_user_id = null;
        String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TravelBusTicketing" + File.separator + "User.json";
        InputStream fis = new FileInputStream(path);
        JsonReader reader = new JsonReader(new InputStreamReader(fis, "UTF-8"));
        hash_user_id = read_hash_user_id(reader);
        return hash_user_id;
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


