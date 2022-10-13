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
    
    static Path inputFilePath = Path.of("src/input.txt");
    static Path configPath =  Path.of("src/config.txt");
    static Path outputFilePath = Path.of("src/output.txt");
    
    
	public static void main(String[] args) throws Exception{
		 
        
        
        String config = Files.readString(configPath);
        String contents = Files.readString(inputFilePath);
   
        String[] contentsAry = contents.split(config);
        StringBuffer constraintVariable = generateAllConstraint(contentsAry);
        generateMemberVariable(contentsAry);
        
        
  
	}
	
	public static StringBuffer generateAllConstraint(String []contentsAry ) throws IOException {
		//public static final String LABEL_DOCTOR_PROFILE = "label_doctor_profile";
		StringBuffer sb = new StringBuffer();
		for(String s : contentsAry) {
        	s = s.trim();
        	String CONSTRAINT = commonPublic+""+label+"_"+s.toUpperCase().replaceAll(" ", "_")+" = \"" + label.toLowerCase()+"_"+s.toLowerCase().replaceAll(" ", "_")+"\";";
        	System.out.println(""+CONSTRAINT);
        	sb.append(CONSTRAINT+"\n");
        }
		return sb;
	}

	public static StringBuffer generateMemberVariable(String []contentsAry ) throws IOException {
		//private var labelDoctorsConsultationTimeIs: String? = ""
		StringBuffer sb = new StringBuffer();
		for(String s : contentsAry) {
        	s = s.trim().toLowerCase();
        	s = toCamelCase(s);
        	
        	String memberVariable = commonPrivate+""+label.toLowerCase()+s+":String? = \"\";";
        	System.out.println(""+memberVariable+" ");
        	
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
