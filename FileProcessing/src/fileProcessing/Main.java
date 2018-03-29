package fileProcessing;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {

	public static void main(String[] args) throws Exception {
		try {
			System.out.println("Specify the path to the file:");
			FileParser fileParser = new FileParser();
			Scanner input = new Scanner(System.in);
			
			String filePath = input.nextLine();
			
			fileParser.tryLoadFile(filePath);

			fileParser.loadFileAsObjects();

			String commandList = ("1. Switch entire line from the file with another line. \n"
					+ "2. Switch TWO elements from the file. \n" + "3. Insert a new number at a wanted position. \n"
					+ "4. Get a number from the file. \n" + "5. Modify a number in the file. \n"
					+ "6. Remove a number at wanted position. \n \n" + "0. Exit.\n \n"
					+ "Choose a command from the list (0-6) : ");
			System.out.print(commandList);

			int option = input.nextInt();
			while (option < 0 || option > 6) {
				System.out.print("Please enter a valid command number (0-6): ");
				option = input.nextInt();
			}

			while (option != 0) {

				switch (option) {
				case 1: {
					try {
						System.out.print("The first row you'd like to switch: ");
						int row1 = input.nextInt();
						System.out.print("The second row you'd like to switch: ");
						int row2 = input.nextInt();
						fileParser.swapRows(row1, row2);
						fileParser.writeDataToFile();
						System.out.println("Done! \n");
						break;
					} catch (InputMismatchException e) {
						System.out.println("Indexes must be whole numbers.");
						return;
					}
				}
				case 2: {
					try {
						System.out.print("Line index of the first number: ");
						int row1 = input.nextInt();
						System.out.print("Number index in line of the first number: ");
						int index1 = input.nextInt();
						System.out.println("Line index of the second number: ");
						int row2 = input.nextInt();
						System.out.print("Number index in line of the second number: ");
						int index2 = input.nextInt();
						if (row1 == row2) {
							fileParser.getRowAtPosition(row1).swapElementsOnRow(index1, index2);
							fileParser.writeDataToFile();
							System.out.println("Done! \n");
							break;
						} else {
							fileParser.swapElementsFromDiffRows(row1, index1, row2, index2);
							fileParser.writeDataToFile();
							System.out.println("Done! \n");
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Indexes must be whole numbers.");
						return;
					}

				}
				case 3: {
					try {
						System.out.print("Line index: ");
						int row1 = input.nextInt();
						System.out.print("Number index in line: ");
						int index1 = input.nextInt();
						System.out.println("Enter number to be inserted: ");
						BigInteger newBigInt = new BigInteger(input.next());
						fileParser.getRowAtPosition(row1).getElementsOnRow().add(index1 - 1, newBigInt);
						fileParser.writeDataToFile();
						System.out.println("Done! \n");
						break;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Indexes must be whole numbers.");
						return;
					}
				}
				case 4: {
					try {
						System.out.print("Line index: ");
						int row1 = input.nextInt();
						System.out.print("Number index in line: ");
						int index1 = input.nextInt();
						System.out.println(fileParser.getRowAtPosition(row1).getElementsOnRow().get(index1));
						fileParser.writeDataToFile();
						System.out.println("Done! \n");
						break;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Indexes must be whole numbers.");
						return;
					}

				}
				case 5: {
					try {
						System.out.print("Line index: ");

						int row1 = input.nextInt();
						System.out.print("Number index in line: ");
						int index1 = input.nextInt();
						System.out.println("Enter new number value: ");
						BigInteger newBigInt = new BigInteger(input.next());
						fileParser.getRowAtPosition(row1).getElementsOnRow().set(index1 - 1, newBigInt);
						fileParser.writeDataToFile();
						System.out.println("Done! \n");
						break;
					} catch (InputMismatchException | NumberFormatException e) {
						if(e instanceof NumberFormatException) {
							System.out.println("Invalid input. Whole number expected.");
							return;
						}
						System.out.println("Invalid input. Indexes must be whole numbers.");
						return;
					}
				}
				case 6: {
					try {
						System.out.print("Line index: ");
						int row1 = input.nextInt();
						System.out.print("Number index in line: ");
						int index1 = input.nextInt();
						fileParser.getRowAtPosition(row1).getElementsOnRow().remove(index1 - 1);
						fileParser.writeDataToFile();
						System.out.println("Done! \n");
						break;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Indexes must be whole numbers.");
						return;
					}
				}
				}
				System.out.println(commandList);
				option = input.nextInt();
				while (option < 0 || option > 6) {
					System.out.print("Please enter a valid command number (0-6): ");
					option = input.nextInt();
				}
			}
			input.close();
		} catch (Exception e) {
			if (e instanceof InputMismatchException) {
				System.out.println("Invalid input. Integer expected.");
				return;
			}
			if (e instanceof FileNotFoundException) {
				System.out.println("Invalid input. String expected.");
				return;
			}
			System.out.println(e.getMessage());

			return;
		}

	}
}
