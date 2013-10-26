package firstset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Problem_AS {
	int numberOfPaths = 0;
	String shortestPath = "";
	static char startNode = 'F';
	static int size=0;
	static List<String> paths = new LinkedList<String>();
	static int startNodeInt = 0;
	
	public static void main(String[] args){
		boolean printAnswer = true;
		String input = "";
		LinkedList<String> list = new LinkedList<String>();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
			while((input=br.readLine())!=null){
				list.add(input);
			}
		}
		catch(Exception e){
			
		}
		List<Character> chars = new LinkedList<Character>();
		for (int i = 0; i<list.size(); i++){
			String[] newChar = list.get(i).split(" ");
			for (int j = 0; j<newChar.length; j++){
                char[] aChar = newChar[j].toCharArray();
				if (!chars.contains(aChar[0])){
					chars.add(aChar[0]);
				}
			}
		}
		
		char[] charMap = new char[chars.size()]; 
		for (int i = 0; i<chars.size(); i++){
			charMap[i] = chars.get(i);
		}
		Map <Character, Integer> kCharVInt = new HashMap<Character, Integer>();
		for (int i = 0; i<chars.size(); i++){
			kCharVInt.put(chars.get(i), i);
		}
		
		for(int i=0; i<chars.size(); i++){
			System.out.println(kCharVInt.get(charMap[i]) + " " + charMap[i]);
		}
		
		size = chars.size();
		boolean[][] bool = new boolean[size][size];
		
		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				bool[i][j] = false;
			}
		}
		
		char endNode = list.get(0).split(" ")[0].toCharArray()[0];
		
		for (int i = 1; i<list.size(); i++){
			char firstChar = list.get(i).split(" ")[0].toCharArray()[0];
			char secondChar = list.get(i).split(" ")[1].toCharArray()[0];
			bool[kCharVInt.get(firstChar)][kCharVInt.get(secondChar)] = true;
			bool[kCharVInt.get(secondChar)][kCharVInt.get(firstChar)] = true;
		}
		
		/*
		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				System.out.println(bool[i][j]);
			}
		}
		*/
		
		startNodeInt = kCharVInt.get(startNode);
		
		if (kCharVInt.get(endNode) == null){
			System.out.println("No Route Available from "+ startNode +" to "+ endNode);
		}
		else{
			String usedNode = "";
			searchForPaths(bool, kCharVInt.get(startNode), kCharVInt.get(endNode), usedNode);
		}
		
		System.out.println(charMap[kCharVInt.get(startNode)]);
		
		String shortestPath = "";
		int minPathIndex = 0;
		int smallestPathSize = 10000;
		for (int i=0; i<paths.size(); i++){
			if (paths.get(i).length()<smallestPathSize){
				smallestPathSize = paths.get(i).length();
				minPathIndex = i;
			}
		}
		shortestPath = paths.get(minPathIndex);
		for (int i = 0; i<shortestPath.length(); i++){
			char[] theInts = shortestPath.toCharArray();
			shortestPath = "";
			for (int j = 0; j<theInts.length; j++){
				String dummyString = "";
				dummyString += theInts[j];
				shortestPath += (charMap[Integer.parseInt(dummyString)]);
			}
		}
	}
	public static void searchForPaths(boolean[][] bool, int curNode, int endNode, String usedNode){
		for(int i = 0; i<size; i++){
			if (bool[curNode][i]){
				if (i==endNode){
					if (paths.contains(usedNode)){
						
					}
					else{
						usedNode += Integer.toString(i);
						paths.add(usedNode);
						System.out.println(usedNode);
					}
				}
				else{
					if (i!=curNode && !usedNode.contains(Integer.toString(i)) && i!=startNodeInt){
						searchForPaths(bool, i, endNode, usedNode);
						usedNode += Integer.toString(i);
					}
				}
			}
		}
	}
}

