import java.io.*;
import java.security.*;

public class Protection
{
	public static byte[] makeBytes(long t, double q) 
	{    
		try 
		{
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			DataOutputStream dataOut = new DataOutputStream(byteOut);
			dataOut.writeLong(t);
			dataOut.writeDouble(q);
			return byteOut.toByteArray();
		}
		catch (IOException e) 
		{
			return new byte[0];
		}
	}	
 // step 2
	public static byte[] makeDigest(byte[] mush, long t2, double q2) throws NoSuchAlgorithmException 
	{
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(mush);
		md.update(makeBytes(t2, q2));
		return md.digest();
	}
	
// step 1
	public static byte[] makeDigest(String user, String password,
									long t1, double q1)
			throws NoSuchAlgorithmException, IOException {

		// convert to byte []  by using same format as MakeBytes() method.
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		DataOutputStream dataOut = new DataOutputStream(byteOut);
		// write encode String type.
		dataOut.writeUTF(user);
		dataOut.writeUTF(password);

		byte[] in = byteOut.toByteArray();


		return makeDigest(in,t1,q1);
	}
}