import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryption_2C_generatingDES {

    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {

        // -Generate a DES key.
//		KeyGenerator keyG = KeyGenerator.getInstance("DES");
//		SecretKey key = keyG.generateKey();

        // -Store it in a file.
        // encode the key to avoid issue and save in file
        // use Base64 to save key

        KeyGenerator keyG = KeyGenerator.getInstance("DES");
        SecretKey key = keyG.generateKey();
        String encodeKey = Base64.getEncoder().encodeToString(key.getEncoded());
        PrintWriter w = new PrintWriter("key.txt");
        w.println(encodeKey);
        System.out.println(encodeKey);
        System.out.println(key.toString());
        w.close();
    }
}
