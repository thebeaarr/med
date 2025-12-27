import java.net.InetAddress;
import java.net.UnknownHostException;
public class Hostname
{
	public static void main(String[] args)
	{
		try
		{
			InetAddress server = InetAddress.getByName("google.com");
			System.out.println(server.getHostAddress());
			System.out.println(server.getHostName());
			System.out.println(server.getAddress().toString());
		}
		catch (UnknownHostException e)
		{
			System.out.println("Host not found");
		}
	}
}