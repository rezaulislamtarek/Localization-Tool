import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainClass {
	
	static String commonPublic = "public static final String ";
	static String commonPrivate = "private var ";
    static String label = "LABEL";
    static String dotLine = "--------------------------------------------------------------------------------------------";
    
    static Path inputFilePath = Path.of("src/input.txt");
    static Path configPath =  Path.of("src/config.txt");
    static Path outputFilePath = Path.of("src/output.txt");
    
    
	public static void main(String[] args) throws Exception{
		 
        String config = Files.readString(configPath);
        String contents = Files.readString(inputFilePath);
   
        String[] contentsAry = contents.split(config);
        StringBuffer constraintVariable = generateAllConstraint(contentsAry);
        StringBuffer memberVariable = generateMemberVariable(contentsAry);
        StringBuffer assignVariable = generateAssignedMemberVariable(contentsAry);
        StringBuffer keys = generateAllKeys(contentsAry,config);
        
        constraintVariable.append("\n\n"+dotLine+"\n\n"+memberVariable+"\n\n"+dotLine+"\n\n").append(assignVariable+"\n\n"+dotLine+"\n\n").append(keys);
        Files.writeString(outputFilePath, constraintVariable);
        System.out.println("Your Output file is ready to use.");
  
	}
	
	public static StringBuffer generateAllConstraint(String []contentsAry ) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(""+dotLine+"\n\n");
		for(String s : contentsAry) {
        	s = s.trim();
        	String CONSTRAINT = commonPublic+""+label+"_"+s.toUpperCase().replaceAll(" ", "_")+" = \"" + label.toLowerCase()+"_"+s.toLowerCase().replaceAll(" ", "_")+"\";";
        	sb.append(CONSTRAINT+"\n");
        }
		return sb;
	}

	public static StringBuffer generateMemberVariable(String []contentsAry ) throws IOException {
		StringBuffer sb = new StringBuffer();
		for(String s : contentsAry) {
        	s = s.trim().toLowerCase();
        	s = toCamelCase(s);
        	String memberVariable = commonPrivate+""+label.toLowerCase()+s+":String? = \"\";";
        	sb.append(memberVariable+"\n");	
        }
		return sb;
	}
	
	public static StringBuffer generateAssignedMemberVariable(String []contentsAry ) throws IOException {
		StringBuffer sb = new StringBuffer();
		for(String s : contentsAry) {
        	s = s.trim().toLowerCase();
        	String cs = s;
        	s = toCamelCase(s);
        	
        	String memberVariable = label.toLowerCase()+s+" = getSingleLocale(LocalisationConstants."+ label+"_"+cs.toUpperCase().replaceAll(" ", "_")+");";
        	sb.append(memberVariable+"\n");
        }
		
		return sb;
	}
	
	public static StringBuffer generateAllKeys(String []contentsAry , String config) throws IOException {
		StringBuffer sb = new StringBuffer();
		for(String s : contentsAry) {
        	s = s.trim();
        	String Keys =  label.toLowerCase()+"_"+s.toLowerCase().replaceAll(" ", "_")+config;
        	sb.append(Keys+"\n");
        }
		return sb;
	}

	static String toCamelCase(String s){
		   String[] parts = s.split(" ");
		   String camelCaseString = "";
		   for (String part : parts){
		      camelCaseString = camelCaseString + toProperCase(part);
		   }
		   return camelCaseString;
		}
	
	static String toProperCase(String s) {
	    return s.substring(0, 1).toUpperCase() +
	               s.substring(1).toLowerCase();
	}

}
