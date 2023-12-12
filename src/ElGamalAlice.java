import java.io.*;
import java.net.*;
import java.security.*;
import java.math.BigInteger;

public class ElGamalAlice
{
	private static BigInteger computeY(BigInteger p, BigInteger g, BigInteger d)
	{
		// IMPLEMENT THIS FUNCTION;
		// y = g ^ d mod p.  -- public key
		return g.modPow(d,p);
	}

	private static BigInteger computeK(BigInteger p)
	{

		// IMPLEMENT THIS FUNCTION;
		// generate a random number k  in 1<k<p-1;

		BigInteger k = new BigInteger(1024, 16, new SecureRandom());

		if(k.compareTo(BigInteger.ONE) > 0 && k.compareTo(p.subtract(BigInteger.ONE))<0){
			return k;
		}else{
			// if k is not in the range, regenerate.
			return computeK(p);
		}


	}
	
	private static BigInteger computeA(BigInteger p, BigInteger g, BigInteger k)
	{
		// IMPLEMENT THIS FUNCTION;

		return g.modPow(k,p);
	}

	private static BigInteger computeB(	String message, BigInteger d, BigInteger a, BigInteger k, BigInteger p)
	{
		// IMPLEMENT THIS FUNCTION;
		// h = hash(M)
		//   s = (h — xr) * k^-1 mod (p-1)
		BigInteger h = k.modInverse(p.subtract(BigInteger.ONE));

		BigInteger result = new BigInteger(message.getBytes()).subtract(d.multiply(a)).multiply(h).mod(p.subtract(BigInteger.ONE));

		return result;

	}

	public static void main(String[] args) throws Exception
	{
		System.out.println(args[0]);
		String message = "The quick brown fox jumps over the lazy dog.";

		String host = "localhost";
		int port = 7999;
		Socket s = new Socket(host, port);
		ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

		// You should consult BigInteger class in Java API documentation to find out what it is.
		// reference: https://medium.com/@shayanmakwana10/elgamal-digital-signature-scheme-860cfb177388

		// p -- a large prime number
		// g -- a generator of the multiplicative group modulo p.
		// y = g ^ d mod p.
		BigInteger y, g, p; // public key
		BigInteger d; // private key

		int mStrength = 1024; // key bit length
		SecureRandom mSecureRandom = new SecureRandom(); // a cryptographically strong pseudo-random number

		// Create a BigInterger with mStrength bit length that is highly likely to be prime.
		// (The '16' determines the probability that p is prime. Refer to BigInteger documentation.)
		p = new BigInteger(mStrength, 16, mSecureRandom);
		
		// Create a randomly generated BigInteger of length mStrength-1
		g = new BigInteger(mStrength-1, mSecureRandom);
		d = new BigInteger(mStrength-1, mSecureRandom);

		y = computeY(p, g, d);

		// At this point, you have both the public key and the private key. Now compute the signature.
		// Signing
		// 1. generate a random number k  in 1<k<p-1;
		BigInteger k = computeK(p);
		BigInteger a = computeA(p, g, k);
		BigInteger b = computeB(message, d, a, k, p);

		// send public key
		os.writeObject(y);
		os.writeObject(g);
		os.writeObject(p);

		// send message
		os.writeObject(message);
		
		// send signature
		os.writeObject(a);
		os.writeObject(b);
		
		s.close();
	}
}