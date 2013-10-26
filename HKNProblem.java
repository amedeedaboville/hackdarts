import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class ProblemHKN {
	public static void main (String[] args){
		String input = "";
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			input = br.readLine();
		}catch(Exception e){}
		int lowerBound = Integer.valueOf(input.split(",")[0]);
		int upperBound = Integer.valueOf(input.split(",")[1]);
		
		List<String> usedNums = new LinkedList<String>();
		int totalNums = 0;
		
		for (int i=lowerBound; i<=upperBound; i++){
			String string = Integer.toBinaryString(i);
			int length = string.length();
			char[] chars = string.toCharArray();
			for (int j = 0; j<length/2; j++){
				if (chars[j]!=chars[length-j-1]){
					j=length/2;
				}
			}
		}
	}
}

