import Intefaces.CheckerMin;

public class CheckerWeightIndexIsLess implements CheckerMin {
	// Check if Index of weight is smaller
	@Override
	public boolean isLess(int minCheckedInteger, int maxCheckedInteger) {
		return minCheckedInteger < maxCheckedInteger;
	}

}