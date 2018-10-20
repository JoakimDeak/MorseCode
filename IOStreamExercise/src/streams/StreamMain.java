package streams;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StreamMain {

	public static void main(String[] args) throws IOException {

		// writeToFile();
		// System.out.println(readFile());
		// secretCode();
		// csvCounter();
		// csvMultiplication();
		// csvMean();
		// FransiscoSolution();
//		pokemonInfo();
		exposeTheConspiracy();
	}

	public static void writeToFile() throws IOException {

		// creates new file and writes to it
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("outputFile.txt")));
		writer.write("Hello World");
		writer.newLine();
		writer.write("Hello again");
		writer.close();
	}

	public static String readFile() throws IOException {

		// reads from existing file
		String fileName = "inputFile.txt";
		File inputFile = new File(fileName);

		Scanner fileReader = new Scanner(inputFile);

		String output = "";
		// reads sequentially
		while (fileReader.hasNextLine()) {

			output += fileReader.nextLine();
			output += "\n";
		}

		fileReader.close();

		return output;
	}

	public static void secretCode() throws IOException {

		Scanner sc = new Scanner(System.in);
		FileWriter writer = new FileWriter(new File("secretCode.txt"));

		System.out.println("What is the secret code?");
		String code = sc.nextLine();
		writer.write(code);

		writer.close();
		sc.close();
	}

	public static void csvCounter() throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("counting.csv")));

		int columns = 3;

		for (int i = 1; i <= 104; i++) {

			writer.write(i);
			writer.write(";");

			if (i % columns == 0) {

				writer.newLine();
			}
		}

		writer.close();
	}

	public static void csvMultiplication() throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("multiplication.csv")));

		for (int i = 1; i <= 10; i++) {

			for (int j = 1; j <= 10; j++) {

				writer.write(j * i);
				writer.write(";");
			}

			writer.newLine();
		}

		writer.close();
	}

	// bad solution, very slow
	public static void csvMean() throws IOException {

		Scanner reader = new Scanner(new File("largeCSV.csv"));

		String row = "";

		while (reader.hasNextLine()) {

			row += reader.nextLine();
		}

		ArrayList<String> list = new ArrayList<>(Arrays.asList(row.split(";")));

		long sum = 0;

		for (String number : list) {

			int currentNumber = Integer.parseInt(number);
			sum += currentNumber;
		}

		double mean = sum / list.size();

		System.out.println(mean);

		reader.close();
	}

	// much better
	public static void FransiscoSolution() throws IOException {

		// : Defines the name of the text file
		String fileName = "largeCSV.csv";

		// : Creates a new file
		File file = new File(fileName);

		// : Creates a file reader
		Scanner fileReader = new Scanner(file);

		// : All of the numbers together are saved here
		// : we use a long , because it can hold a large size.
		long GrandTotall = 0;
		int number_of_numbers = 0;

		while (fileReader.hasNext() == true) {

			String row = fileReader.nextLine();
			String temp = "";

			for (int i = 0; i < row.length(); i++) {

				// : Get's the values from the row, by checking for a semicolon.
				if (row.charAt(i) != ';') {
					temp += row.charAt(i);
				} else {
					number_of_numbers++;
					GrandTotall += Long.valueOf(temp);
					temp = "";
				}

			}

		}
		fileReader.close();
		// : Calculates the averge
		long average = GrandTotall / number_of_numbers;

		System.out.println("The average out of all numbers is: " + average);
	}

	public static void pokemonInfo() throws IOException {

		Scanner reader = new Scanner(new File("pokedex.csv"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("TransportationVehicle.csv")));
		ArrayList<String> attributes = new ArrayList<>(Arrays.asList(reader.nextLine().split(";")));
		System.out.println(attributes);

		while (reader.hasNextLine()) {

			String pokeInfo = reader.nextLine();
			String name = "";
			String hp = "";
			String type1 = "";
			String type2 = "";
			String speed = "";
			int column = 1;

			for (int i = 0; i < pokeInfo.length(); i++) {
				char currentChar = pokeInfo.charAt(i);

				if (currentChar == ';') {

					column++;
				} else {

					switch (column) {

					case 3:
						name += currentChar;
						break;
					case 4:
						hp += currentChar;
						break;
					case 9:
						speed += currentChar;
						break;
					case 11:
						type1 += currentChar;
						break;
					case 12:
						type2 += currentChar;
						break;
					}
				}
			}
			
			if(type2.equals("Flying")) {
				
				writer.write(name + ";");
				writer.write(hp + ";");
				writer.write(speed + ";");
				writer.write(type1 + ";");
				writer.write(type2 + ";");
				writer.newLine();
			}
			
			System.out.println("Name: " + name + ", Health: " + Integer.valueOf(hp));
		}

		reader.close();
		writer.close();
	}
	
	public static void exposeTheConspiracy() throws IOException {
		
		Scanner reader = new Scanner(new File("Financial records.csv"));
		reader.nextLine(); // first line is not a country
		double highestProfit = 0;
		String countryHighestProfit = "";
		
		while(reader.hasNextLine()) {
			
			String info = reader.nextLine();
			String stringProfit = "";
			double intProfit = 0;
			String name = "";
			
			int column = 1;
			
			for(int i = 0; i < info.length(); i++) {
				
				char currentChar = info.charAt(i);
				if(currentChar == ';') {
					
					column++;
				} else if(column == 2) {
					
					name += currentChar; 
				} else if(column == 12) {
					
					stringProfit += currentChar;
				}
			}
			stringProfit = stringProfit.replaceAll("\\,", "");
			stringProfit = stringProfit.replace(" ", "");
			stringProfit = stringProfit.replace("$", "");
			System.out.println(name);
			intProfit = Integer.parseInt(stringProfit.substring(0, stringProfit.length() - 1));
			if(intProfit > highestProfit) {
				
				highestProfit = intProfit;
				countryHighestProfit = name;
			}
			
		}
		
		System.out.println(countryHighestProfit + "is the country with the highest profit with a profit of: " + highestProfit);
		
		reader.close();
	}
}
