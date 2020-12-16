import Intefaces.Convertor;

public class ConvertCharactersArrayToString implements Convertor{
//convert array of characters to string 
	@Override
	public String convert(char[] SrtingCharcters) {
		StringBuilder convertor = new StringBuilder();
		convertor.append(SrtingCharcters);
		return convertor.toString();
	}

}
