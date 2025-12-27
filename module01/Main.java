public class Main {
	public static void main(String[] args) {
		System.out.println("===== VULNERABILITY SCANNER =====\n");

		Target localhost = new Target();
		localhost.scanPorts();
		localhost.detectVulnerabilities();
		localhost.generatereport();

		Target router = new Target("192.168.1.1", "Router", "Linux");
		router.scanPorts();
		router.detectVulnerabilities();
		router.generatereport();

		Target webserver = new Target("8.8.8.8", "Google DNS", "Unknown");
		webserver.scanPorts();
		webserver.detectVulnerabilities();
		webserver.generatereport();

		Exploit eternalblue = new Exploit("EternalBlue", "HIGH", 445, "CVE-2017-0144", 0.85);
		eternalblue.getDescription();
		eternalblue.execute(router);

		Exploit sshbrute = new Exploit("SSH Bruteforce", "MEDIUM", 22, "CVE-2023-1234", 0.60);
		sshbrute.execute(webserver);

		System.out.println("\n===== PATCHING =====");
		if (!router.getVulns().isEmpty()) {
			router.patchVulnerability(router.getVulns().get(0));
			router.generatereport();
		}

		System.out.println("\n===== TESTING COPY CONSTRUCTOR =====");
		Target copy = new Target(router);
		copy.getVulns().add("TEST VULN");
		System.out.println("Original: " + router.getVulns().size() + " vulns");
		System.out.println("Copy: " + copy.getVulns().size() + " vulns");

		System.out.println("\n===== TESTING EQUALS =====");
		Target t1 = new Target("192.168.1.1", "Test1", "Linux");
		Target t2 = new Target("192.168.1.1", "Test2", "Windows");
		System.out.println("t1.equals(t2): " + t1.equals(t2));

		System.out.println("\n===== STATISTICS =====");
		System.out.println("Total targets scanned: " + Target.getTotalTargets());
		System.out.println("Total exploits loaded: " + Exploit.getTotalExploits());
	}
}