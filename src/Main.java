import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Main {
	
	public static void main (String [] args) throws IOException, SAXException, ParserConfigurationException {
//		System.out.print("Input a text file: ");
//		Scanner sc = new Scanner(System.in);
		Parser parser = new Parser();
		Set<String> uniqueWords = parser.getUniqueWords("input.txt");
		parser.removeCommonWords(uniqueWords);
		
		HttpUtility util = new HttpUtility();
		
		for (String word : uniqueWords) {
			String xml = util.get(word);
			System.out.println(word + " - " + util.xmlDef(xml));
		}	
//		sc.close();
	}
}
