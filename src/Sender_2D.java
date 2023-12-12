import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class Sender_2D {

    public void encryption() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException {
        System.out.println("Sender is running");


        // use RSA technique to encrypt message/

        // get public key from receiver
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("./receiverPub.xx"));
        RSAPublicKey receiverPubKey = (RSAPublicKey) in.readObject();
        in.close();


        // get own private key
        ObjectInputStream in1 = new ObjectInputStream(new FileInputStream("./senderPri.xx"));
        RSAPrivateKey priKey =(RSAPrivateKey) in1.readObject();
        in1.close();


        // Confidentiality or Integrity/Authtication
        System.out.println("This is sender, please make a selection for encryption: ");
        System.out.println("Enter 1 --> confidentiality");
        System.out.println("Enter 2---> Integrity/Authentication");
        Scanner flag = new Scanner(System.in);
        // selection send to receiver so that receiver knows
        int flagVal = flag.nextInt();
        flag.close();

        // communicate with in port 7999.
        String message = "This is Information Privacy Final Project";
        String host = "localhost";
        int port = 7999;
        Socket s = new Socket(host,port);


        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        out.writeInt(flagVal);

        // Encryption================
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        // confidentiality
        if(flagVal == 1){
            // encrypt with the receiver's public key
            cipher.init(Cipher.ENCRYPT_MODE, receiverPubKey);
            byte[] enMessage = cipher.doFinal(message.getBytes());

            // send out
            out.writeObject(enMessage);

        }else if(flagVal==2){
            // Integrity/ Authentication
            cipher.init(Cipher.ENCRYPT_MODE,priKey);
            byte[] enMessage = cipher.doFinal(message.getBytes());

            out.writeObject(enMessage);

        }else{
            System.out.println("Please enter 1 or 2!");
        }

        out.flush();
        out.close();
        s.close();


    }
}
