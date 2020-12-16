import Intefaces.CheckerMin;

public class CheckerSecondeSwapedCharIndexIsLess implements CheckerMin {
	// Check if Index of swapped char is smaller

	@Override
	public boolean isLess(int minCheckedInteger, int maxCheckedInteger) {
		return minCheckedInteger < maxCheckedInteger;
	}

}
