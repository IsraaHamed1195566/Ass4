import Intefaces.CheckerMin;
import Intefaces.Convertor;
import Intefaces.IncreasingValue;
import Intefaces.PrintResultedString;
import Intefaces.ReturnValue;
import Intefaces.SwappingCharacters;
//this class for modifications values as needed
public class ModifiedIndexValue extends CombinString {
	private IncreasingValue _increasingValueByOne;
	private ReturnValue _returnSwapedCharIndexToOne;
	private ReturnValue _returnWeightIndextoZero;
	private SwappingCharacters _swappingCharacters;
	private int secondeSwapedCharIndex;

	ModifiedIndexValue(String string) {
		//inializations
		super(string);
		_increasingValueByOne = new IncreasingIndexByOne();
		_returnSwapedCharIndexToOne = new ReturnSwapedCharIndexToOne();
		_returnWeightIndextoZero = new ReturnWeightIndextoZero();
		_swappingCharacters = new SwapArrayCharacters();
		secondeSwapedCharIndex = 1;

	}

	//get the value of second Swapped  Char Index from controler array for increasing
	public int getWeightIndex() {
		return super.getSwapingControler()[secondeSwapedCharIndex];
	}
	//set increased value at  second Swapped  Char Index
	private void setWeightIndex(int weightIndex) {
		int[] SwapingControlerAfterIncresingWeight = super.getSwapingControler().clone();
		SwapingControlerAfterIncresingWeight[secondeSwapedCharIndex] = weightIndex;
		super.setSwapingControler(SwapingControlerAfterIncresingWeight);
	}

	public int getSwapedCharIndex() {
		return secondeSwapedCharIndex;
	}

	private void setSwapedCharIndex(int swapedCharIndex) {
		secondeSwapedCharIndex = swapedCharIndex;
	}

	// Upper bound index. i.e: if string is "abc" then index i could be at "c"
	public int getSecondeSwapedCharIndex() {
		return secondeSwapedCharIndex;
	}
	//increasing value of Weight Index by 1  and 
	//return second Swapped Char Index to One value thats mean assign 1 to it
	public void increasingWeightIndex_SwapedCharIndexToOne() {
		setWeightIndex(_increasingValueByOne.increaseWeightIndex(getWeightIndex()));
		setSwapedCharIndex(_returnSwapedCharIndexToOne.returnToInitialValue());
	}
	//increasing second Swapped Char Index  by one and
	//return value of Weight Index to Zero value thats mean assign 0 to it
	public void zeroWeightIndex_IncreasingSwapedCharIndex() {
		setWeightIndex(_returnWeightIndextoZero.returnToInitialValue());
		setSwapedCharIndex(_increasingValueByOne.increaseSwapedCharIndex(getSwapedCharIndex()));
	}

	public void swapCharacters() {
		setStringCharactersArray(_swappingCharacters.swapping(getStringCharactersArray(), getFirstSwapedCharIndex(),
				getSecondeSwapedCharIndex()));
	}

	// Lower bound index. i.e: if string is "abc" then firstSwapedCharIndex will
	// always be 0.
	private int getFirstSwapedCharIndex() {
		if (getSecondeSwapedCharIndex() % 2 == 0)
			return 0;
		return super.getSwapingControler()[getSecondeSwapedCharIndex()];

	}



}
