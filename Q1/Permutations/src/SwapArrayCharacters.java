import Intefaces.SwappingCharacters;

public class SwapArrayCharacters implements SwappingCharacters {


	@Override
	public char[] swapping(char[]stringCharactersArray ,int firstSwapedCharIndex,int secondeSwapedCharIndex) {
		char handledSwapedCharIndex = stringCharactersArray[secondeSwapedCharIndex];
		stringCharactersArray[secondeSwapedCharIndex] = stringCharactersArray[firstSwapedCharIndex];
		stringCharactersArray[firstSwapedCharIndex] = handledSwapedCharIndex;
		return stringCharactersArray;		
	}

}
