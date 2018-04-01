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
			UserInput userIn = new UserInput(input);
			
			userIn.showMenu();
			//0. Exit.
			while (userIn.getOption() != 0) {

				switch (userIn.getOption()) {
				//1. Switch entire line from the file with another line.
				case 1: {
					userIn.askLineSwap();
					fileParser.swapRows(userIn.getIndex(), userIn.getLine());
					
					break;
				}
				//2. Switch TWO elements from the file.
				case 2: {

					UserInput secondElem = new UserInput(input);
					userIn.askForElement();
					secondElem.askForElement();

					fileParser.swapElementsFromDiffRows(userIn.getLine(), userIn.getIndex(), secondElem.getLine(),
							secondElem.getIndex());
					
					secondElem = null;
					break;

				}
				//3. Insert a new number at a wanted position.
				case 3: {

					userIn.askForElement();
					userIn.askForBigInt();
					fileParser.addElement(userIn.getLine(), userIn.getIndex(), userIn.getBigInt());
					
					break;

				}

				case 4: {
					//4. Get a number from the file.
					userIn.askForElement();
					System.out.println(fileParser.getElement(userIn.getLine() , userIn.getIndex()));
					break;
				}
				case 5: {
					//5. Modify a number in the file.
					userIn.askForElement();
					userIn.askForBigInt();
					fileParser.setElement(userIn.getLine(), userIn.getIndex(), userIn.getBigInt());
					break;

				}
				case 6: {
					//6. Remove a number at wanted position.
					userIn.askForElement();
					fileParser.removeElement(userIn.getLine(), userIn.getIndex() );
					break;

				}
				}
				userIn.showMenu();

			}
			
		} catch (

		Exception e) {
			if (e instanceof InputMismatchException) {
				System.out.println("Invalid input. Integer expected.");
				return;
			}
			if (e instanceof FileNotFoundException) {
				System.out.println("File not found.");
				return;
			}
			System.out.println(e.getMessage());

			return;
		}

	}
}
