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

	public void swapElementsOnRow(int index1, int index2) throws Exception {

		if (FileParser.between(index1, 1, this.elementsOnRow.size())
				&& FileParser.between(index2, 1, this.elementsOnRow.size())) {
			BigInteger temp = this.elementsOnRow.get(index1 - 1);
			this.elementsOnRow.set(index1 - 1, this.elementsOnRow.get(index2 - 1));
			this.elementsOnRow.set(index2 - 1, temp);
		} else {
			throw new Exception("Number index not found.");
		}
	}
	

}
