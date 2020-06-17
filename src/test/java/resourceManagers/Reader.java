package resourceManagers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Reader {

	
	
	public static void main(String args[]) throws IOException {

		removeSpacesFromFeatureFileName();
//		updateStepDefinitionMethod("CustomAntisenseLNAGapmeRStepDefinition.java");
	
	}
	public String readFile() throws Throwable {

		try(BufferedReader br = new BufferedReader(new FileReader("Blank.txt"))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String output = sb.toString();
			return output;
		}
	}

	
	
	public static void updateStepDefinitionMethod(String FileName) throws IOException {
		String FolderPath=System.getProperty("user.dir") + File.separator + "src"+ File.separator + "test"+ File.separator + "java"+ File.separator + "stepDefinitions";
		String FullFilePath=FolderPath + File.separator +FileName;
		
		File fileToBeModified = new File(FullFilePath);
		String newContent = "";
		BufferedReader reader = new BufferedReader(new FileReader(FullFilePath));

		String line = reader.readLine();

		while (line != null)
		{
			System.out.println(line);
			if (line.contains("public void")) {
				String FirstChar =line.substring(0,line.indexOf("void")+5) ;
				String UpdateTExtLine=line.substring(line.indexOf("void")+5,line.length());
				String temp="";
				String[] Words=UpdateTExtLine.split("_");
				for (int i=0;i<Words.length; i++ ) {
					if (i==0) {
						temp=temp+ Words[i].substring(0,Words[i].length());
					}
					else
						temp=temp+Words[i].substring(0,1).toUpperCase()+ Words[i].substring(1,Words[i].length());
				}
				line=FirstChar+temp;	
				System.out.println(line);

			}
			newContent = newContent + line + System.lineSeparator();
			line = reader.readLine();
		}

		FileWriter writer = new FileWriter(fileToBeModified);
		writer.write(newContent);
		reader.close();
		writer.close();
	}
	
	public static void removeSpacesFromFeatureFileName() {
		String folderPath=System.getProperty("user.dir")+File.separator+"src" +File.separator+ "test" +  File.separator + "resources";
		
		File folder = new File(folderPath);
		
		File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
            	String FileName=listOfFiles[i].getName();
            	String newFileName=FileName.replaceAll(" ", "_");
            	newFileName=newFileName.replaceAll("_-_", "-");
            	newFileName=newFileName.replaceAll("__", "_");
                File f = new File(folderPath + File.separator + FileName); 
                f.renameTo(new File(folderPath + File.separator + newFileName));
            }
        }

        System.out.println("conversion is done");
		
	}
}
