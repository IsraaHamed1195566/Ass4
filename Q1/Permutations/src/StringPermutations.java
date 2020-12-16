import Intefaces.CheckerMin;
import Intefaces.Convertor;
import Intefaces.PrintResultedString;

public class StringPermutations extends ModifiedIndexValue {

	public static void main(String[] args) {
		StringPermutations stringPermutations = new StringPermutations("098");
		stringPermutations.stringPermutations();
	}

	public StringPermutations(String string) {
		super(string);

	}

	public void stringPermutations() {
		// Print initial string, as only the alterations will be printed later
		printInitialString();
		// continuous in loop while the swapped character is last one
		while (isLessSecondeSwapedCharIndex()) {
			// if the weight index is bigger or the same
			// it means that we have already switched between these characters
			if (isLessWeightIndex()) {
				// swapping characters
				swapCharacters();
				// print resulted word
				printResultedString();
				// Adding 1 to the specific weight that relates to the char array.
				// if SwapedCharIndex was 2 (for example), after the swap we now need to swap
				// for SwapedCharIndex=1
				increasingWeightIndex_SwapedCharIndexToOne();
			} else {
				// Weight index will be zero because one iteration before, it was 1 (for
				// example)
				// to indicate that char array stringCharactersArray[SwapedCharIndex] swapped.
				// SwapedCharIndex will have the option to go forward in the char array for
				// "longer swaps"
				zeroWeightIndex_IncreasingSwapedCharIndex();
			}
		}
	}

	private void printInitialString() {
		PrintResultedString _printResultedString = new PrintString();
		_printResultedString.print(getString());

	}

	private void printResultedString() {
		PrintResultedString _printResultedString = new PrintString();
		//return resulted array of character after swapping to string for printing
		Convertor _convertCharactersArrayToString = new ConvertCharactersArrayToString();
		_printResultedString.print(_convertCharactersArrayToString.convert(getStringCharactersArray()));

	}

	// return if the upper bound index is less
	// than length of created characters array
	private boolean isLessSecondeSwapedCharIndex() {
		CheckerMin _checkerMin;
		_checkerMin = new CheckerSecondeSwapedCharIndexIsLess();
		return _checkerMin.isLess(getSecondeSwapedCharIndex(), getLenghtStringCharactersArray());
	}

	// return if the upper bound index is less
	// than length of created characters array
	private boolean isLessWeightIndex() {
		CheckerMin _checkerMin;
		_checkerMin = new CheckerWeightIndexIsLess();
		return _checkerMin.isLess(getWeightIndex(), getSecondeSwapedCharIndex());
	}

}
