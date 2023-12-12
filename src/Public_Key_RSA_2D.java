import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Public_Key_RSA_2D extends Thread{

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeySpecException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        // 2D-- Public key System

        // 1. sender and receiver generated their own pub and private key
        Publick_key_2D keyPair = new Publick_key_2D();
        keyPair.sender();
        keyPair.receiver();

        System.out.println("Finished generate key");


        // 3. co-current start receiver to receive message
        // need multi-threads
        Public_Key_RSA_2D receiver = new Public_Key_RSA_2D();
        receiver.start();


        // 2. start sender to send message
        Sender_2D sender2D = new Sender_2D();
        sender2D.encryption();


    }

    @Override
    public void run(){
        Receiver_2D receiver2D = new Receiver_2D();
        try {
            receiver2D.decryption();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }


    }






}
