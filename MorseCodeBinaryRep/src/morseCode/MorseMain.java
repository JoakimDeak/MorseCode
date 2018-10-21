package morseCode;

import java.io.IOException;

public class MorseMain {
	
	public MorseMain() {
		
		
	}
	
	public void run() {
		
		Converter converter = new Converter();
		converter.initAlphabet();
		try {
			
			converter.convertFileTo("shortTest.txt", "shortTestMorse");
			converter.convertFileFrom("shortTestMorse.txt", "shortTestBackToNormal");
		} catch (IOException e) {
			
			System.out.println("File not found");
		}
	}
	
	public static void main(String[] args) {
		
		MorseMain main = new MorseMain();
		main.run();
	}
}
