
import java.time.LocalDate;

public class NetworkDevice
{
	protected  String ipAddress ;
	protected  String macAddress ;
	protected  String hostname ;
	protected  String deviceType; 
	protected  Boolean isOnline;
	protected  long uptime ;
	protected  int packetsSent ;
	protected  int packetsReceived;
	protected  long bytesTransferred;
	protected  LocalDate lastSeen ;

	// constuctor 

	// methods 
	public void sendPacket(NetworkPacket packet, NetworkDevice destination)
	{
		
	}
	public void receivePacket(NetworkPacket packet)
	{

	}
	public void ping(String ipAddress)
	{

	}
	public void getStatistics()
	{

	}


	public void displayInfo()
	{

	}
}