package fileProcessing;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FileRow {

	private List<BigInteger> elementsOnRow = new ArrayList<BigInteger>();

	public List<BigInteger> getElementsOnRow() {
		return elementsOnRow;
	}

	public void setElementsOnRow(List<BigInteger> elementsOnRow) {
		this.elementsOnRow = elementsOnRow;
	}

	@Override
	public String toString() {
		return "FileRow [elementsOnRow=" + elementsOnRow + "]";
	}



}
