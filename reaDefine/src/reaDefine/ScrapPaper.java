package reaDefine;

import java.io.*;
import java.util.*;

public class ScrapPaper {

	public static void main(String[] args) throws FileNotFoundException {
		
		File inputFile = new File("definedWords.txt");
		Scanner in = new Scanner(inputFile);
		PrintWriter out = new PrintWriter("testBank.txt");
		
		//Creates a new ArrayList<String> for the test bank and adds all of the words to the text bank while also numbering them
		ArrayList<String> testBank = new ArrayList<String>();
		int qNumber = 1;
		
		while (in.hasNextLine()) {
			testBank.add(in.nextLine());
			out.println(qNumber + ". " + in.nextLine());
			qNumber++;
		}
		
		in.close();
		out.close();
		
		Random wordIndexRandomizer = new Random();
		Random correctChoiceRandomizer = new Random();

		
		String wholeWord = "";
		int wordIndex = wordIndexRandomizer.nextInt(qNumber);
		
		wholeWord = testBank.get(wordIndex);
		
		//System.out.println(wholeWord);
		
		String word = wholeWord.substring(0, wholeWord.indexOf("-"));
		String definition = wholeWord.substring(wholeWord.indexOf("-")+2);
	
		//System.out.println("The word is " + word);
		//System.out.println("The definition is " + definition);
		
		String[][] choices = new String[4][2];
		int correctChoice = correctChoiceRandomizer.nextInt(4);
				
		choices[correctChoice][0] = definition;
		choices[correctChoice][1] = "correct";		
		
		String[] wrongChoices = getWrongChoices(testBank, correctChoice);
		
		/*
		for (int k = 0; k < wrongChoices.length; k++) {
			System.out.println(wrongChoices[k]);
		}
		*/
		
		for (int i = 0; i < 4; i++) {
				if (choices[i][0] == null) {
					choices[i][0] = wrongChoices[i];
					choices[i][1] = "wrong";
				}
		}
		
		
		char[] abcd = {'a', 'b', 'c', 'd'};
		
		
		System.out.println("Define " + word);
		for (int i = 0; i < 4; i++) {
			System.out.println(abcd[i] + ". " + choices[i][0]);
			
		}
		
		System.out.println("What is your answer? Enter only the letter.");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
				
		while (!input.equals("a") && !input.equals("b") && !input.equals("c") && !input.equals("d")) {
			System.out.println("Sorry, your answer is invalid. Try again and only enter the letter.");
			input = sc.nextLine();
		}
		
		int numInput = 0;
		
		if (input.equals("a")) {
			numInput = 0;
		} else if (input.equals("b")) {
			numInput = 1;
		} else if (input.equals("c")) {
			numInput = 2;
		} else if (input.equals("d")) {
			numInput = 3;
		}
				
		if (choices[numInput][1] == "correct") {
			System.out.println("That's correct!");
		} else {
			System.out.println("Sorry, the correct answer choice is " + abcd[correctChoice] + ".");
		}
		
		sc.close();
	}
	
	private static String[] getWrongChoices(ArrayList<String> testBank, int correctChoice) {
		
		//removes the correct choice from options for wrong choices
		testBank.remove(correctChoice);
		
		//initialize a String[] for the wrong choices that are randomly generated to populate the choices array later
		String[] wrongChoices = new String[4];
		
		//randomly choose the first wrong answer, populate it into wrong choices array, and then remove it from the test bank
		Random wrong1Randomizer = new Random();
		int wrong1Index = wrong1Randomizer.nextInt(testBank.size());
		String wrongDef1 = testBank.get(wrong1Index).substring(testBank.get(wrong1Index).indexOf("-")+2);
		wrongChoices[0] = wrongDef1;
		testBank.remove(wrong1Index);
		
		Random wrong2Randomizer = new Random();
		int wrong2Index = wrong2Randomizer.nextInt(testBank.size());
		String wrongDef2 = testBank.get(wrong2Index).substring(testBank.get(wrong2Index).indexOf("-")+2);
		wrongChoices[1] = wrongDef2;
		testBank.remove(wrong2Index);
		
		Random wrong3Randomizer = new Random();
		int wrong3Index = wrong3Randomizer.nextInt(testBank.size());
		String wrongDef3 = testBank.get(wrong3Index).substring(testBank.get(wrong3Index).indexOf("-")+2);
		wrongChoices[2] = wrongDef3;
		testBank.remove(wrong3Index);
		
		Random wrong4Randomizer = new Random();
		int wrong4Index = wrong4Randomizer.nextInt(testBank.size());
		String wrongDef4 = testBank.get(wrong4Index).substring(testBank.get(wrong4Index).indexOf("-")+2);
		wrongChoices[3] = wrongDef4;
		testBank.remove(wrong4Index);
		
		return wrongChoices;
	}

}
