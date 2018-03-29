package fileProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {

	private File inputFile;
	private List<FileRow> rows = new ArrayList<>();

	public void tryLoadFile(String filePath) throws Exception {
		this.inputFile = new File(filePath);
		if (!this.inputFile.exists()) {
			throw new Exception("File does not exist");
		}

	}

	public void loadFileAsObjects() throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
		String line;
		int lineCount = 0;
		while ((line = bufferedReader.readLine()) != null) {
			lineCount++;

			if (line.startsWith(" ") || line.startsWith("	"))

				throw new NumberFormatException("Line " + lineCount + " starts with whitespace.");

			FileRow row = new FileRow();

			
			String[] splittedRowAsStrings = line.split("\\s+");

			
			for (String eachElement : splittedRowAsStrings) {

				if (eachElement == null || eachElement.isEmpty()) {
					continue;
				}
				if (eachElement.startsWith("0")) {
					throw new NumberFormatException("Numbers can not start with 0. (On line " + lineCount+")");
				}
				try {
				BigInteger number = new BigInteger(eachElement);
				
				row.getElementsOnRow().add(number);}
				catch (NumberFormatException e) {
					throw new NumberFormatException("Invalid number on line "+lineCount);}
			}

			if (!row.getElementsOnRow().isEmpty()) {
				rows.add(row);
			} else {
				throw new Exception("Line " + lineCount + " is empty!");
			}

		}
		bufferedReader.close();

	}

	public void writeDataToFile() throws FileNotFoundException, IOException {
		/// this will autoclose the file if exception occurs since java8
		try (BufferedWriter outputStream = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(inputFile)))) {
			for (FileRow eachRow : rows) {
				StringBuilder line = new StringBuilder();
				line.append(eachRow.getElementsOnRow().get(0));
				for (int i = 1; i < eachRow.getElementsOnRow().size(); i++) {
					
						line.append(" ");
					
					line.append(eachRow.getElementsOnRow().get(i));
				}
				

				outputStream.write(line.toString());
				outputStream.newLine();
				line.replace(0, line.length() - 1, "");
			}
		}
	}

	public FileRow getRowAtPosition(int position) throws Exception {
		
		//Checks if the provided indexes correspond to existing elements. If so, runs the if-body.
		if (FileParser.between(position, 1, this.rows.size())) {
			return this.rows.get(position - 1);
		} else {
			throw new IndexOutOfBoundsException("Line index not found.");
		}
	}

	public List<FileRow> getRows() {
		return rows;
	}

	public void setRows(List<FileRow> rows) {
		this.rows = rows;
	}

	
	public void swapRows(int row1, int row2) throws Exception {
		int javaRow1 = row1 - 1;
		int javaRow2 = row2 - 1;
		//Checks if the provided indexes correspond to existing elements. If so, runs the if-body.
		if (FileParser.between(row1, 1, this.rows.size()) && FileParser.between(row2, 1, this.rows.size())) {
			FileRow tempRow = this.rows.get(javaRow1);
			this.rows.set(javaRow1, this.rows.get(javaRow2));
			this.rows.set(javaRow2, tempRow);
		} else {
			throw new IndexOutOfBoundsException("Line index not found.");
		}
	}

	public void swapElementsFromDiffRows(int row1, int index1, int row2, int index2) throws Exception {

	
		BigInteger tempBigInt=this.getElement(row1-1, index1-1);
		this.setElement(row1-1, index1-1, this.getElement(row2-1, index2-1));
		this.setElement(row2-1, index2-1, tempBigInt);
	}
	
	public static boolean between(int variable, int lowerBound, int upperBound) {
		if (variable >= lowerBound && variable <= upperBound)
			return true;
		return false;

	}

	public void setElement(int row, int index, BigInteger number) {
		if (FileParser.between(row, 1, this.rows.size()) && FileParser.between(index, 1, this.getSizeOf(row))) {
			this.rows.get(row).getElementsOnRow().set(index, number);
		} else
			throw new IndexOutOfBoundsException("Index not found!");
	}

	public BigInteger getElement(int row, int index) {
		if (FileParser.between(row, 0, this.rows.size()-1) && FileParser.between(index, 0, this.getSizeOf(row)-1)) {
			return (this.rows.get(row).getElementsOnRow().get(index));
		} else
			throw new IndexOutOfBoundsException("Index not found!");
	}

	public void addElement(int row, int index, BigInteger number) throws IndexOutOfBoundsException {
		if (FileParser.between(row, 0, this.rows.size()-1) && FileParser.between(index, 0, this.getSizeOf(row)-1)) {
			this.rows.get(row).getElementsOnRow().add(index, number);
		} else
			throw new IndexOutOfBoundsException("Index not found!");
	}

	public void removeElement(int row, int index) throws IndexOutOfBoundsException {
		if (FileParser.between(row, 0, this.rows.size()-1) && FileParser.between(index, 0, this.getSizeOf(row)-1)) {
			this.rows.get(row).getElementsOnRow().remove(index);
		} else
			throw new IndexOutOfBoundsException("Index not found!");
	}

	public int getSizeOf(int row) throws IndexOutOfBoundsException {
		if (FileParser.between(row, 0, this.rows.size()-1)) {
			return (this.rows.get(row).getElementsOnRow().size());
		} else
			throw new IndexOutOfBoundsException("Index not found!");

	}
}
