import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class Publick_key_2D {

    // generate sender Public and private key
    public void sender() throws NoSuchAlgorithmException, IOException {

        KeyPairGenerator keyG = KeyPairGenerator.getInstance("RSA");

        keyG.initialize(1024,new SecureRandom());

        KeyPair keys = keyG.generateKeyPair();
        RSAPublicKey senderPub = (RSAPublicKey) keys.getPublic();
        RSAPrivateKey senderPri = (RSAPrivateKey) keys.getPrivate();

        // save public for sender
        savePub(senderPub,"senderPub.xx");

        // save private for sender
        savePri(senderPri,"senderPri.xx");

    }

    public void savePub(RSAPublicKey key1, String file) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(key1);
        out.close();
    }
    public void savePri(RSAPrivateKey key1, String file) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(key1);
        out.close();

    }

    // generate Receiver Public and private key
    public void receiver() throws NoSuchAlgorithmException, IOException {

        KeyPairGenerator keyG = KeyPairGenerator.getInstance("RSA");

        keyG.initialize(1024,new SecureRandom());

        KeyPair keys = keyG.generateKeyPair();
        RSAPublicKey receiverPub = (RSAPublicKey) keys.getPublic();
        RSAPrivateKey receiverPri = (RSAPrivateKey) keys.getPrivate();

        // save public for sender
        savePub(receiverPub,"receiverPub.xx");

        // save private for sender
        savePri(receiverPri,"receiverPri.xx");



    }
}
