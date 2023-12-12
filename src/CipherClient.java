import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class CipherClient
{
	public static void main(String[] args) throws Exception
	{
		System.out.println(args[0]);

		String message = "The quick brown fox jumps over the lazy dog.";
		String host = "localhost";
		int port = 7999;
		Socket s = new Socket(host, port);

		// YOU NEED TO DO THESE STEPS:

		// step 1 ---Generate DES key
		// step 2 --- Store at file

		// I did this in seperating function.  Since IntellIDEA cannot current process client and server, there is minor time difference
		// this could cause DES key dismatch.
		// thus, client will generate a key first to save file
		// then read from same file to get DES KEY.

		BufferedReader br = new BufferedReader(new FileReader("./key.txt"));
		String keyC = br.readLine();
		System.out.println("Client side:"+keyC);
		byte[] decodeKey = Base64.getMimeDecoder().decode(keyC);
		SecretKey key = new SecretKeySpec(decodeKey,0, decodeKey.length, "DES");


		// -Use the key to encrypt the message above and send it over socket s to the server.
		// cipher( transformation);
		// transformation--> "algorithm/model/padding" | "algorithm"
		// DES + Electronic Codebook-- Block cipher encryption
		byte[] bMessage = message.getBytes();
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE,key);
		byte[] enMessage = cipher.doFinal(bMessage);

		System.out.println("Sending the Message....");

		// send to server
		ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		out.writeObject(enMessage);

		s.close();
	}

}