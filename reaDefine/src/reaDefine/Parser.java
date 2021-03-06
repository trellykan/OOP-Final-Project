package reaDefine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Parser {
	
	private BufferedReader reader;
	
	// Retrieves all the unique words from the input file by removing duplicates
	public Set<String> getUniqueWords(String filename) throws IOException {
		String line = null;
		Set<String> uniqueWords = new HashSet<>();
		try {
			reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				String [] words = line.toLowerCase()
							.replaceAll("[^a-zA-Z ]", "") //Fix regex to take in single quote
							.trim().split(" ");
				uniqueWords.addAll(Arrays.asList(words));
			}
		} catch (IOException e) {
			System.out.println("File Not Found!");
		}
		reader.close();
		return uniqueWords;
	}

	// Removes all stop words listed in the 1-1000.txt (most common English words) text file
	public void removeCommonWords(Set<String> words) throws IOException{
		File stopwords = new File("1-1000.txt");
		reader = new BufferedReader(new FileReader(stopwords));
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (words.contains(line)) {
				words.remove(line);
			}
		}
		reader.close();
	}
}
