import Intefaces.PrintResultedString;

public class PrintString implements PrintResultedString {

	//print the send string 
	@Override
	public void print(String givenString) {
		System.out.println(givenString);
		
	}

}
