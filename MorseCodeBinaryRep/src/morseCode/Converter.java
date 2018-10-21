package morseCode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Converter {

	private String[] alphabet;

	public Converter() {

		this.alphabet = new String[37];
	}

	public void initAlphabet() {

		try {

			Scanner sc = new Scanner(new File("morseAlphabet.txt"));

			for (int i = 0; i < alphabet.length; i++) {

				this.alphabet[i] = sc.nextLine();
			}
			sc.close();
		} catch (IOException e) {

			System.out.println("File not found");
		}
	}

	public String convert(char letter) {

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
			return alphabet[26];
		case "digits":
			return alphabet[letterInt - 21];
		case "letters":
			return alphabet[letterInt - 65];
		default: // symbols without morse code representation
			return "";
		}
	}
	
	public void convertFile(String inputFilename, String outputFilename) throws IOException {
		
		Scanner sc = new Scanner(new FileReader(new File(inputFilename)));
		outputFilename += ".txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFilename)));
		
		String outputString = "";
		
		while(sc.hasNext()) {
			
			String thisLine = sc.nextLine();
			
			for(int i = 0; i < thisLine.length(); i++) {
				
				char currentChar = thisLine.charAt(i);
				
				outputString += convert(currentChar);
			}
		}
		
		writer.write(outputString);
		
		sc.close();
		writer.close();
	}
}
