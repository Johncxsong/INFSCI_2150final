import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class CipherServer
{
	public static void main(String[] args) throws Exception 
	{
		System.out.println(args[0]);

		int port = 7999;
		ServerSocket server = new ServerSocket(port);
		Socket s = server.accept();

		// YOU NEED TO DO THESE STEPS:
		// -Read the key from the file generated by the client.
		BufferedReader br = new BufferedReader(new FileReader("./key.txt"));
		String keyC = br.readLine();
		System.out.println("Server side: "+keyC);

		byte[] decodeKey = Base64.getMimeDecoder().decode(keyC);
		SecretKey key = new SecretKeySpec(decodeKey,0, decodeKey.length, "DES");

		// -Use the key to decrypt the incoming message from socket s.
		// use same encryption algorithm to decipher it

		ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		byte[] enMessage = (byte[]) in.readObject();

		// since we get key and enMessage
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);

		String message = new String(cipher.doFinal(enMessage));


		// -Print out the decrypt String to see if it matches the orignal message.
		System.out.println("Decrypted message: "+ message);
	}
}