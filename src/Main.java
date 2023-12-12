import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.security.MessageDigest;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        // create object
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your input to encode: ");

        String in = input.nextLine();

        Main encode = new Main();
        System.out.println("The encoding in SHA-256: ");
        encode.encodingSHA(in);
        System.out.println("The encoding in MD5: ");
        encode.encodingMD5(in);


    }

    public void encodingSHA(String input) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(input.getBytes());

        byte[] digest = md.digest();

        // output
        StringBuilder hexString = new StringBuilder(2*digest.length);

        for(byte b: digest){
            hexString.append(String.format("%02X",b));
        }

        System.out.println(hexString.toString());

    }

    public void encodingMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());

        byte[] digest = md.digest();

        // output
        StringBuilder hexString = new StringBuilder(2*digest.length);

        for(byte b: digest){
            hexString.append(String.format("%02X",b));
        }

        System.out.println(hexString.toString());

    }


}