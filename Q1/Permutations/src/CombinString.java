
public class CombinString {
	private String string;
	private char[] stringCharactersArray;

	public void setStringCharactersArray(char[] stringCharactersArray) {
		this.stringCharactersArray = stringCharactersArray;
	}

	private final int lenghtStringCharactersArray;
	private int[] swapingControler;

	public CombinString(String string) {
		setString(string);
		// convert string to one dim array characters
		stringCharactersArray = string.toCharArray();
		lenghtStringCharactersArray = stringCharactersArray.length;

		// Weight index control array initially all zeros. Of course, same size of the
		// char array.
		swapingControler = new int[lenghtStringCharactersArray];
	}

	public char[] getStringCharactersArray() {
		return stringCharactersArray;
	}

	public String getString() {
		return string;
	}

	public int getLenghtStringCharactersArray() {
		return lenghtStringCharactersArray;
	}

	public int[] getSwapingControler() {
		return swapingControler;
	}

	public void setSwapingControler(int[] swapingControler) {
		this.swapingControler = swapingControler;
	}

	// there is no String Permutations if there is no characters
	public void setString(String string) {
		try {
			if (string.length() <= 0)
				throw new IllegalArgumentException();
			this.string = string;
		} catch (IllegalArgumentException e) {
			System.out.println(" your input is Empty");
			System.exit(1);
		}
	}

}
