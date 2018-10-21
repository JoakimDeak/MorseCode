package morseCode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Converter {

	private String[] morseAlphabet;
	private String[] normalAlphabet;

	public Converter() {

		this.morseAlphabet = new String[37];
		this.normalAlphabet = new String[37];
	}

	public void initAlphabet() {

		try {

			Scanner sc = new Scanner(new File("alphabet.txt"));

			for (int i = 0; i < morseAlphabet.length; i++) {

				this.morseAlphabet[i] = sc.nextLine();
			}
			
			for(int i = 0; i < normalAlphabet.length; i++) {
				
				this.normalAlphabet[i] = sc.nextLine();
			}
			sc.close();
		} catch (IOException e) {

			System.out.println("File not found");
		}
	}

	public String convertTo(char letter) {

		letter = Character.toUpperCase(letter);
		String group = "";

		int letterInt = (int) letter;

		if (letterInt == 32) {

			group = "space";
		} else if (letterInt >= 48 && letterInt <= 57) {

			group = "digits";
		} else if (letterInt >= 65 && letterInt <= 90) {
			
			group = "letters";
		}

		switch (group) {

		case "space":
			return morseAlphabet[26];
		case "digits":
			return morseAlphabet[letterInt - 21];
		case "letters":
			return morseAlphabet[letterInt - 65];
		default: // symbols without morse code representation
			return "";
		}
	}
	
	public String convertFrom(String morseLetter) {
		
		for(int i = 0; i < morseAlphabet.length; i++) {
			
			if(morseLetter.equals(morseAlphabet[i])) {
				
				return normalAlphabet[i];
			}
		}
		
		return ""; // practically unreachable, only there to prevent compilation error
	}
	
	public void convertFileTo(String inputFilename, String outputFilename) throws IOException {
		
		Scanner sc = new Scanner(new FileReader(new File(inputFilename)));
		outputFilename += ".txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFilename)));
		
		String outputString = "";
		
		while(sc.hasNext()) {
			
			String thisLine = sc.nextLine();
			
			for(int i = 0; i < thisLine.length(); i++) {
				
				char currentChar = thisLine.charAt(i);
				
				outputString += convertTo(currentChar);
			}
		}
		
		writer.write(outputString);
		
		sc.close();
		writer.close();
	}
	
	public void convertFileFrom(String inputFilename, String outputFilename) throws IOException {
		
		Scanner sc = new Scanner(new  FileReader(new File(inputFilename)));
		outputFilename += ".txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFilename)));
		
		String outputString = "";
		
		while(sc.hasNext()) {
			String thisLine = sc.nextLine();
			int counter = 0; // counts number of 0s in a row
			int endOfLastWord = 0;
			
			for(int i = 0; i < thisLine.length(); i++) {
				
				char currentChar = thisLine.charAt(i);
				
				String currentWord = "";
				
				if(currentChar == '0') {
					
					counter++;
				} else {
					
					counter = 0;
				}
				
				if(counter == 3) { // end of character
					
					currentWord = thisLine.substring(endOfLastWord, i + 1);
					if(currentWord.contains("1")) {
						
						outputString += convertFrom(currentWord);
						endOfLastWord = i + 1;
					}
				}
				
				if(counter == 7) { // end of word
					
					currentWord = thisLine.substring(endOfLastWord, i + 1);
					if(!currentWord.contains("1")) {
						
						outputString += convertFrom(currentWord);
						endOfLastWord = i + 1;
					}
				}
			}
		}
		
		writer.write(outputString);
		
		sc.close();
		writer.close();
	}
}
