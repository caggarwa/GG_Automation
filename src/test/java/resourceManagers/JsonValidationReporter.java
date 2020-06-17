package resourceManagers;

public class JsonValidationReporter {
	public static StringBuffer sb;
	
	public JsonValidationReporter() {
		sb= new StringBuffer("**JSON Validation Report**");
		sb.append(System.getProperty("line.separator"));
		sb.append("==============================");
	}
	
	public static void addStep(String Message) {
		sb.append(System.getProperty("line.separator"));
		sb.append(Message);
	}
}
