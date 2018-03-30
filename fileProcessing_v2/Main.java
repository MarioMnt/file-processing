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

			while (userIn.getOption() != 0) {

				switch (userIn.getOption()) {
				case 1: {
					userIn.askLineSwap();
					fileParser.swapRows(userIn.getIndex(), userIn.getLine());
					fileParser.writeDataToFile();
					break;
				}
				case 2: {

					UserInput secondElem = new UserInput(input);
					userIn.askForElement();
					secondElem.askForElement();

					fileParser.swapElementsFromDiffRows(userIn.getLine(), userIn.getIndex(), secondElem.getLine(),
							secondElem.getIndex());
					fileParser.writeDataToFile();
					secondElem = null;
					break;

				}
				case 3: {

					userIn.askForElement();
					userIn.askForBigInt();
					fileParser.addElement(userIn.getLine() - 1, userIn.getIndex() - 1, userIn.getBigInt());
					fileParser.writeDataToFile();
					break;

				}

				case 4: {

					userIn.askForElement();
					System.out.println(fileParser.getElement(userIn.getLine() - 1, userIn.getIndex() - 1));
					break;
				}
				case 5: {

					userIn.askForElement();
					userIn.askForBigInt();
					fileParser.setElement(userIn.getLine() - 1, userIn.getIndex() - 1, userIn.getBigInt());
					fileParser.writeDataToFile();
					break;

				}
				case 6: {

					userIn.askForElement();
					fileParser.removeElement(userIn.getLine() - 1, userIn.getIndex() - 1);
					fileParser.writeDataToFile();
					break;

				}
				}
				userIn.showMenu();

			}
			input.close();
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
