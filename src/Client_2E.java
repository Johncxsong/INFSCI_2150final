import com.sun.security.jgss.GSSUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

public class Client_2E {

    public void sending() throws IOException, CertificateException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 1. get server certificate
        FileInputStream content = new FileInputStream("./server.cer");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate certificate = (X509Certificate) cf.generateCertificate(content);
        content.close();

        // 2. print out the representation.
        System.out.println("==========Client Side================");
        System.out.println("The server certification: ");
        System.out.println(certificate.toString());


        // 3. validation
        Date now = new Date();
        try{
            certificate.checkValidity(now);
            System.out.println("Certification is valid-->"+"[Time check: "+now.toString()+" ]");

        }catch(CertificateException e){
            System.out.println("Expired on-->"+certificate.getNotAfter());
        }

        try{
            certificate.verify(certificate.getPublicKey());
        }catch(InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException e){
            System.out.println("public key is not valid");
        }
        System.out.println("=======================================\n\n");
        System.out.println();

        // 4. send message to server
        String host = "localhost";
        int port = 7999;
        Socket s = new Socket(host,port);

        String Message = "This is Final project through X.509";

        // 4.1 get server's public key and Encryption
        RSAPublicKey pubKey = (RSAPublicKey) certificate.getPublicKey();

        // encrypt
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] enMessage = cipher.doFinal(Message.getBytes());

        // 4.2 send message through socket port 7999
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        out.writeObject(enMessage);

        // 5. close and finished
        out.flush();
        out.close();
        s.close();
    }
}
