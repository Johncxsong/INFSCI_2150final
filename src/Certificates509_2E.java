import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class Certificates509_2E extends Thread{
    // Every x.509certificate contains a data section and a signature section.

    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, CertificateException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        // 1. generate x.509 certification
        // keytool -genkey -keyalg RSA -alias John -keystore keystore.jks
        // keytool -export -alias John -storepass 123456 -file server.cer -keystore keystore.jks

        // 3. co-current launch server
        Certificates509_2E server = new Certificates509_2E();
        server.start();

        // 2. start client to send message and check key
        Client_2E client = new Client_2E();
        client.sending();



    }


    @Override
    public void run(){
        Server_2E server = new Server_2E();
        try {
            server.receiving();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnrecoverableKeyException e) {
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