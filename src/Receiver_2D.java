import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Receiver_2D {

    public void decryption() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {


        // get sender's public key  & own private key
        ObjectInputStream readK = new ObjectInputStream(new FileInputStream("./senderPub.xx"));
        RSAPublicKey senderPubKey = (RSAPublicKey) readK.readObject();
        readK.close();


        // own private key
        ObjectInputStream readK1 = new ObjectInputStream(new FileInputStream("./receiverPri.xx"));
        RSAPrivateKey priKey = (RSAPrivateKey) readK1.readObject();
        readK1.close();


        // communication
        int port = 7999;
        ServerSocket server = new ServerSocket(port);
        Socket s = server.accept();

        // get sender's flag value
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        int flag = in.readInt();

        // decryption
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        System.out.println("this is starting for reciever");

        if(flag==1){
            // confidentiality--decryption
            byte[] cipherText = (byte[]) in.readObject();
            cipher.init(Cipher.DECRYPT_MODE,priKey);
            byte[] text = cipher.doFinal(cipherText);
            String message = new String(text);
            System.out.println("Confidential Message received: "+ message);


        }else{

            // Integrity-- decryption
            byte[] cipherText = (byte[]) in.readObject();
            cipher.init(Cipher.DECRYPT_MODE,senderPubKey);
            byte[] text = cipher.doFinal(cipherText);
            String message = new String(text);
            System.out.println("Integrity/Authenticated Message received: "+ message);

        }


        in.close();

    }

}
