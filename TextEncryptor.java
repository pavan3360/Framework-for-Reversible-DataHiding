/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;
/**
 *
 * @author bala
 */
public class TextEncryptor {
    
     private static final String ALGO = "AES";
    /*private static final byte[] keyValue =
        new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',

'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };*/

     
     public static String encryptB(byte[] Data,String keys) throws Exception {
        Key key = generateKey(keys);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data);
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

   public static String encrypt(String Data,String keys) throws Exception {
        Key key = generateKey(keys);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData,String keys) throws Exception {
        Key key = generateKey(keys);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
        //return decValue;
    }
    private static Key generateKey(String keys) throws Exception {
        
        int tofill = 16-keys.length();
        for (int i=0;i<tofill;i++)
        {
            keys = keys + "A";
        }
        byte [] keyValue = keys.getBytes();
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    
     public static void main(String[] args) throws Exception {

        String password = "mypasswomypasswo";
        String cont = "Bala is working";
        String passwordEnc = TextEncryptor.encrypt("Bala is working",password);
        String passwordDec = TextEncryptor.decrypt(passwordEnc,password);

        System.out.println("Plain Text : " + cont);
        System.out.println("Encrypted Text : " + passwordEnc);
        System.out.println("Decrypted Text : " + passwordDec);
    }

    
}
