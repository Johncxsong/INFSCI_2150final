import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;

public class Server_2E {

    public void receiving() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, NoSuchPaddingException, ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        // server alias and password
        String alias = "John";
        String password ="123456";

        // 1. open socket and receive message
        int port = 7999;
        ServerSocket server = new ServerSocket(port);
        Socket s = server.accept();

        ObjectInputStream in = new ObjectInputStream(s.getInputStream());

        // 2. retrieve server's private key.
        KeyStore priKey = KeyStore.getInstance("jks");
        priKey.load(new FileInputStream("./keystore.jks"), password.toCharArray());

        PrivateKey key = (PrivateKey) priKey.getKey(alias,password.toCharArray());


        // 3. decrypt message
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] enMessage = (byte[]) in.readObject();

        cipher.init(Cipher.DECRYPT_MODE,key);

        byte[] text = cipher.doFinal(enMessage);
        String message = new String(text);
        System.out.println("=======================Server Side==============");
        System.out.println("Message received: "+ message);


        // 4. close
        server.close();
        in.close();
        s.close();

    }
}
