package reaDefine;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Driver {
	
	public static void main (String [] args) throws IOException, SAXException, ParserConfigurationException {
//		System.out.print("Input a text file: ");
//		Scanner sc = new Scanner(System.in);
		Parser parser = new Parser();
		Set<String> uniqueWords = parser.getUniqueWords("input.txt");
		parser.removeCommonWords(uniqueWords);
		
		HttpUtility util = new HttpUtility();
		
		PrintWriter out = new PrintWriter("definedWords.txt");
		for (String word : uniqueWords) {
			String xml = util.get(word);
			System.out.println(word + " - " + util.xmlDef(xml));
			out.println(word + " - " + util.xmlDef(xml));
		}
		out.close();
		
		
		
		
		
//		sc.close();
	}
}
