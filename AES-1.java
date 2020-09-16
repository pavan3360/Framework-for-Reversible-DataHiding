import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static byte[] getFile(String filename) {

        File f = new File(filename);
        InputStream is = null;
        try {
            is = new FileInputStream(f);
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        byte[] content = null;
        try {
            content = new byte[is.available()];
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            is.read(content);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return content;
    }

    public static byte[] encryptFile(Key key, byte[] content) {
        Cipher cipher;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;

    }

    public static byte[] decryptFile(Key key, byte[] textCryp) {
        Cipher cipher;
        byte[] decrypted = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypted = cipher.doFinal(textCryp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decrypted;
    }

    public static void saveFile(String filename,byte[] bytes) throws IOException {

        FileOutputStream fos = new FileOutputStream(filename);
        fos.write(bytes);
        fos.close();

    }
    
    public static void decryptI(String imgname,String decimagename,String keytest) throws Exception
    {
        
        try
        {
           Key key = new SecretKeySpec(keytest.getBytes(), "AES");
           
           byte [] encon = getFile(imgname);
           byte[] decrypted = decryptFile(key, encon);
           //System.out.println(decrypted);

           saveFile(decimagename,decrypted);
           System.out.println("Done");
        
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        
        
        
    }

    public static void encryptI(String imagename,String enimagename,String keytest)
    {
        try
        {
        Key key = new SecretKeySpec(keytest.getBytes(), "AES");
        
          
        byte[] content = getFile(imagename);
          
        byte[] encrypted = encryptFile(key, content);  
        
        saveFile(enimagename,encrypted);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
      
    public static void main(String args[])
            throws NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException {

        /*
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        Key key = keyGenerator.generateKey();
        System.out.println(key.toString());
        
        String keyFile = "AESkey";
        
        try
        {
            FileOutputStream out = new FileOutputStream(keyFile);
            byte[] keyb = key.getEncoded();
            out.write(keyb);
            out.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        */
        
        
        //String keyFile = "AESkey";
        //byte[] keyb = Files.readAllBytes(Paths.get(keyFile));
        //SecretKeySpec key = new SecretKeySpec(keyb, "AES");
        
        String keytest = "test1234test1234";
        Key key = new SecretKeySpec(keytest.getBytes(), "AES");
        
          
        byte[] content = getFile("hiddenimg.png");
        //System.out.println(content);
        
        
        
        

        byte[] encrypted = encryptFile(key, content);
        System.out.println(encrypted);
        
        saveFile("encrypted.jpg",encrypted);
        

        byte [] encon = getFile("encrypted.jpg");
        byte[] decrypted = decryptFile(key, encon);
        System.out.println(decrypted);

        saveFile("decrypted.jpg",decrypted);
        System.out.println("Done");
        
    }

}