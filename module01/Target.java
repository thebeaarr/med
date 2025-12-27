
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;

public class Target {
	private String ipAddress;
	private String hostname;
	private String os;
	private ArrayList<Integer> openPorts;
	private ArrayList<String> version_servers;
	private ArrayList<String> vulnerabilities;
	private String risklevel;
	private LocalDate lastScanDate;
	private static int totalTargets;

	public Target(String ipAddress, String hostname, String os) {
		this.ipAddress = ipAddress;
		this.hostname = hostname;
		this.os = os;
		this.risklevel = "LOW";
		this.lastScanDate = LocalDate.now();
		this.vulnerabilities = new ArrayList<>();
		this.openPorts = new ArrayList<>();
		this.version_servers = new ArrayList<>();
		totalTargets++;
	}

	public Target() {
		try {
			InetAddress local = InetAddress.getLocalHost();
			this.ipAddress = local.getHostAddress();
			this.hostname = local.getHostName();
			this.os = System.getProperty("os.name");
		} catch (Exception e) {
			this.ipAddress = "127.0.0.1";
			this.hostname = "unknown";
			this.os = "unknown";
		}
		this.risklevel = "LOW";
		this.lastScanDate = LocalDate.now();
		this.vulnerabilities = new ArrayList<>();
		this.openPorts = new ArrayList<>();
		this.version_servers = new ArrayList<>();
		totalTargets++;
	}

	public Target(Target obj) {
		this.ipAddress = obj.ipAddress;
		this.hostname = obj.hostname;
		this.os = obj.os;
		this.risklevel = obj.risklevel;
		this.lastScanDate = obj.lastScanDate;
		this.version_servers = new ArrayList<>(obj.version_servers);
		this.vulnerabilities = new ArrayList<>(obj.vulnerabilities);
		this.openPorts = new ArrayList<>(obj.openPorts);
		totalTargets++;
	}

	public ArrayList<String> getVulns() {
		return this.vulnerabilities;
	}

	public ArrayList<Integer> getOpenport() {
		return this.openPorts;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getHostname() {
		return hostname;
	}

	public String getOs() {
		return os;
	}

	public String getRisklevel() {
		return risklevel;
	}

	public static int getTotalTargets() {
		return totalTargets;
	}

	public void scanPorts() {
		int[] commonPorts = {21, 22, 23, 25, 53, 80, 110, 143, 443, 445, 993, 995, 3306, 3389, 5432, 5900, 8080, 8443};
		System.out.println("scanning " + hostname + " ...");
		
		for (int port : commonPorts) {
			try {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(ipAddress, port), 200);
				socket.setSoTimeout(200);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String banner = in.readLine();
				openPorts.add(port);
				if (banner != null && !banner.isEmpty())
					version_servers.add("Port " + port + ": " + banner);
				socket.close();
			} catch (Exception e) {
			}
		}
		this.lastScanDate = LocalDate.now();
		System.out.println("Found " + openPorts.size() + " open ports ");
	}

	public void detectVulnerabilities() {
		vulnerabilities.clear();
		for (int port : this.openPorts) {
			switch (port) {
				case 21:
					vulnerabilities.add("FTP - Unencrypted file transfer");
					break;
				case 22:
					vulnerabilities.add("SSH - Weak authentication detected");
					break;
				case 23:
					vulnerabilities.add("Telnet - Insecure protocol in use");
					break;
				case 445:
					vulnerabilities.add("SMB - EternalBlue vulnerability");
					break;
				case 3389:
					vulnerabilities.add("RDP - Remote Desktop exposed");
					break;
				case 3306:
					vulnerabilities.add("MySQL exposed without firewall");
					break;
				case 5432:
					vulnerabilities.add("PostgreSQL exposed to network");
					break;
			}
		}
		calculateLVL();
	}

	public void calculateLVL() {
		int riskc = this.vulnerabilities.size();
		if (riskc == 0)
			risklevel = "LOW";
		else if (riskc <= 2)
			risklevel = "MEDIUM";
		else if (riskc <= 4)
			risklevel = "HIGH";
		else
			risklevel = "CRITICAL";
	}

	public void generatereport() {
		System.out.println("===== SCAN REPORT =====");
		System.out.println("Target : [" + hostname + "][" + ipAddress + "]");
		System.out.println("OS :[" + os + "]");
		System.out.println("Open Ports : " + openPorts);
		
		if (!version_servers.isEmpty()) {
			System.out.println("Service Versions:");
			for (String banner : version_servers)
				System.out.println("  " + banner);
		}
		
		System.out.println("Vulnerabilities Found !");
		for (String vuln : this.vulnerabilities)
			System.out.println("  - " + vuln);
		System.out.println("risk level : " + risklevel);
		System.out.println("last scan date : " + lastScanDate);
		System.out.println("   =======================");
	}

	public void patchVulnerability(String vuln) {
		if (this.vulnerabilities.remove(vuln)) {
			calculateLVL();
			System.out.println(vuln + " patched!!");
		} else {
			System.out.println(vuln + " not found");
		}
	}

	@Override
	public String toString() {
		return "[Target] : " + this.ipAddress + " [risk level ] : " + this.risklevel;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Target))
			return false;
		Target other = (Target) obj;
		return this.ipAddress != null && this.ipAddress.equals(other.ipAddress);
	}
}
