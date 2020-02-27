package P_ExtentReports01;

public class TestExtentReportsMAIN {

	//Constructeur
	public TestExtentReportsMAIN() {}

	
	public static void main(String[] args) throws InterruptedException {
	
		TestExtentReportsPOM testCase001 = new TestExtentReportsPOM	();
		testCase001.lancer_lebrowser("https://intra.extia.fr/login/");
		testCase001.fermer();
											}

}
