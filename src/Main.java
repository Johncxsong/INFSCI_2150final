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
        StringBuilder hexString = new StringBuilder();

        for(byte b: digest){
            System.out.print(b+ " ");
        }
        System.out.println();

    }

    public void encodingMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());

        byte[] digest = md.digest();
        // output
        StringBuilder hexString = new StringBuilder();

        for(byte b: digest){
            System.out.print(b+ " ");
        }

    }


}