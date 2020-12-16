import Intefaces.IncreasingValue;

public class IncreasingIndexByOne implements IncreasingValue {
//increasing an index by 1
	@Override
	public int increaseWeightIndex(int weightIndex) {
		return weightIndex+1;
	}

	@Override
	public int increaseSwapedCharIndex(int swapedCharIndex) {
		return swapedCharIndex+1;
	}

	
}
