/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.APIs.SecurityAPIs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author User
 */
public class Encryption {

    /**
     *Encrypt the String entered in parameter text.
     * @param text
     * @return byte[] The encrypted text represented in an array of bytes
     */
    public static byte[] encrypt_AES(String text) {
        try {
            Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv_param_spec = get_iv_parameter_spec("src/main/java/key_file/iv_param_spec.txt");
            SecretKey secret_key = get_secret_key("src/main/java/key_file/secret_key.txt");
            
            encrypt.init(Cipher.ENCRYPT_MODE, secret_key, iv_param_spec);
            byte[] byte_text = text.getBytes("UTF-8");            
            byte[] encrypted_text = encrypt.doFinal(byte_text);
            return encrypted_text;
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
        }
        return null;
    }

    private static SecretKey get_secret_key(String file_location) throws IOException {
        byte[] byte_key = Files.readAllBytes(Paths.get(file_location));
        SecretKey secret_key = new SecretKeySpec(byte_key, "AES");
        return secret_key;
    }
    
    private static IvParameterSpec get_iv_parameter_spec(String file_location) throws IOException {
        byte[] byte_iv = Files.readAllBytes(Paths.get(file_location));
        IvParameterSpec iv_param_spec = new IvParameterSpec(byte_iv);
        return iv_param_spec;
    }

}
