import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Date;
import java.util.Random;

public class ProtectedClient
{
	public void sendAuthentication(String user, String password, OutputStream outStream) throws IOException, NoSuchAlgorithmException 
	{
		DataOutputStream out = new DataOutputStream(outStream);

		// IMPLEMENT THIS FUNCTION.

		// input random number, timestamp, user name
		Random num = new Random();
		double q1 = num.nextDouble();
		long t1 = System.currentTimeMillis();

		// digest algorithm 1
		byte[] digest1 = Protection.makeDigest(user,password,t1,q1);


		// input random 2, timestamp 2
		double q2 = num.nextDouble();
		long t2 = System.currentTimeMillis();

		// digest algorithm 2
		byte[] digest2 = Protection.makeDigest(digest1,t2,q2);

		// write to output
		out.writeUTF(user);
		out.writeDouble(q1);
		out.writeLong(t1);
		out.writeDouble(q2);
		out.writeLong(t2);

		out.writeInt(digest2.length);
		out.write(digest2);


		out.flush();
	}

	public static void main(String[] args) throws Exception 
	{
		System.out.println(args[0]);

		// set to localhost, since testing in local machine.
		String host = "localhost";
		int port = 7999;
		String user = "George";
		String password = "abc123";
		Socket s = new Socket(host, port);

		ProtectedClient client = new ProtectedClient();
		client.sendAuthentication(user, password, s.getOutputStream());

		s.close();
	}
}