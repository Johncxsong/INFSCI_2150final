import java.io.*;
import java.net.*;
import java.security.*;
import java.math.BigInteger;

public class ElGamalBob
{
	private static boolean verifySignature(	BigInteger y, BigInteger g, BigInteger p, BigInteger a, BigInteger b, String message)
	{
		// IMPLEMENT THIS FUNCTION;
		/*
		b. Calculate h = hash(M).
		c. Calculate v1 = (y^r * r^s) mod p.
		d. Calculate v2 = g^h mod p.
		e. If v1 = v2, the signature is valid. Otherwise, the signature is invalid.
		 */

		BigInteger left = (y.modPow(a,p)).multiply(a.modPow(b,p)).mod(p);
		BigInteger right = g.modPow(new BigInteger(message.getBytes()),p);

		return left.equals(right)? true:false;
	}

	public static void main(String[] args) throws Exception 
	{
		System.out.println(args[0]);
		int port = 7999;
		ServerSocket s = new ServerSocket(port);
		Socket client = s.accept();
		ObjectInputStream is = new ObjectInputStream(client.getInputStream());

		// read public key
		BigInteger y = (BigInteger)is.readObject();
		BigInteger g = (BigInteger)is.readObject();
		BigInteger p = (BigInteger)is.readObject();

		// read message
		String message = (String)is.readObject();

		// read signature
		BigInteger a = (BigInteger)is.readObject();
		BigInteger b = (BigInteger)is.readObject();

		boolean result = verifySignature(y, g, p, a, b, message);

		System.out.println(message);

		if (result == true)
			System.out.println("Signature verified.");
		else
			System.out.println("Signature verification failed.");

		s.close();
	}
}