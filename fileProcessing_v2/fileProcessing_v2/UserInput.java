package fileProcessing;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
	private Scanner input;

	private int row;
	private int index;
	private BigInteger bigInt;
	private int option;

	private String menuList = ("1. Switch entire line from the file with another line. \n"
			+ "2. Switch TWO elements from the file. \n" + "3. Insert a new number at a wanted position. \n"
			+ "4. Get a number from the file. \n" + "5. Modify a number in the file. \n"
			+ "6. Remove a number at wanted position. \n \n" + "0. Exit.\n \n"
			+ "Choose a command from the list (0-6) : ");

	public UserInput(Scanner input2) {

		this.input = input2;
	}

	public UserInput() {

	}
	
	public Scanner getInput() {
		return input;
	}

	public void setInput(Scanner input) {
		this.input = input;
	}

	public void askForElement() {

		System.out.print("Line index: ");
		this.row = this.input.nextInt();
		System.out.print("Number index in line: ");
		this.index = this.input.nextInt();

	}

	public void askForBigInt() {
		System.out.print("Enter number to be inserted: ");
		this.bigInt = this.input.nextBigInteger();
	}
	public void setLine(int line) {
		this.row=line;
	}

	public int getLine() {
		return this.row;
	}
	public void setIndex(int index) {
		this.index=index;
	}
	public int getIndex() {
		return this.index;
	}
	public void setBigInt(BigInteger bigInt) {
		this.bigInt=bigInt;
	}
	public BigInteger getBigInt() {
		return this.bigInt;
	}
	public void setOption(int option) {
		this.option=option;
	}
	public int getOption() {
		return this.option;
	}

	public void showMenu() {
		System.out.print(this.menuList);
		this.option = this.input.nextInt();
		while (option < 0 || option > 6) {
			System.out.print("Please enter a valid command number (0-6): ");
			this.option = this.input.nextInt();
		}
	}

	public void askLineSwap() {
		// Using the index field as second row field.
		System.out.print("The first row you'd like to switch: ");
		this.row = this.input.nextInt();
		System.out.print("The second row you'd like to switch: ");
		this.index = this.input.nextInt();
	}

}
