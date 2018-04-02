/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package key_file;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author User
 */
@Deprecated
public class KeyGeneration {

    /**
     * @param args the command line arguments
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidKeyException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.io.UnsupportedEncodingException
     * @throws javax.crypto.IllegalBlockSizeException
     * @throws javax.crypto.BadPaddingException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
//        byte[] iv = new byte[128 / 8];
//        SecureRandom secureRandom = new SecureRandom();
//
//        secureRandom.nextBytes(iv);
//        IvParameterSpec ivParamSpec = new IvParameterSpec(iv);
//        
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        SecretKey secretKey = keyGen.generateKey();
//        
//        try {
//            String iv_file = "src/key_file/iv_param_spec.txt";
//            String sec_file = "src/key_file/secret_key.txt";
//            FileOutputStream fos = new FileOutputStream(iv_file);
//            fos.write(iv);
//            fos.close();        
//            FileOutputStream fos1 = new FileOutputStream(sec_file);
//            fos1.write(secretKey.getEncoded());
//            System.out.println(Arrays.toString(iv));
//            byte[] test = Files.readAllBytes(Paths.get(iv_file));
//            System.out.println("Decrypt");
//            System.out.println(Arrays.toString(test));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        
//        
//        Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        encrypt.init(Cipher.ENCRYPT_MODE, secretKey, ivParamSpec);
//        
//        Cipher decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        decrypt.init(Cipher.DECRYPT_MODE, secretKey, ivParamSpec);
//        
//        String plainText = "Test encrypted password";
//        byte[] input = plainText.getBytes("UTF-8");
//        byte[] encoded = encrypt.doFinal(input);
//        String test = new String(decrypt.doFinal(encoded));
//        
//        System.out.println(Arrays.toString(encoded));
//        System.out.println(test);
    }

}
