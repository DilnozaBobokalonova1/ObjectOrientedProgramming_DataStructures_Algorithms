package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class AnagramUtil {
	
	public static String sort(String inputString) {
		char[] myChars = inputString.toLowerCase().toCharArray();
		for (int i = 1; i < inputString.length(); i++) {
			for(int j = i; j > 0; j--) {
				if(myChars[j] < myChars[j-1]) {
					char temp = myChars[j];
					myChars[j] = myChars[j-1];
					myChars[j-1] = temp;
				}
			}
		}
		return String.valueOf(myChars);
	}
	
	public static boolean areAnagrams(String string1, String string2) {
	
		if(sort(string1).compareTo(sort(string2)) != 0) {
			return false;
		}
		return true;
	}
	
	public static void insertionSort(String[] inputList) {
		OrderStrings comparator = new OrderStrings();
		for (int i = 1; i < inputList.length; i++) {
			for (int j = i; j > 0; j--) {
				if(comparator.compare(inputList[j], inputList[j-1]) < 0) {
					String temp = inputList[j];
					inputList[j] = inputList[j-1];
					inputList[j-1] = temp;
				}
			}
		}
	}
	
	public static String[] getLargestAnagramGroup(String[] inputList) {
		
		insertionSort(inputList);
		int count = 1;
		int max = 0;
		int end = 0;
		String[] empty = {};
		for (int i = 1; i < inputList.length; i++) {
				if(areAnagrams(inputList[i], inputList[i-1])) {
					count++;
				} else {
					if(count > max) {
						max = count;
						end = i-1;
					}
					
					count = 1;
				}
		}
		
		if (max==1) {
			return empty;
		}
		
		String[] theAnagram = new String[max];
		
		for (int j = 0; j < max; j++) {
			theAnagram[j] = inputList[end];
			end--;
		}
		return theAnagram;
	}
	
	public static String[] getLargestAnagramGroup(String filename) {
		String[] anotherOne = readFile(filename);
		String[] anotherTwo = getLargestAnagramGroup(anotherOne);
		return anotherTwo;
	}
	
	public static String[] readFile(String filename)
	{
		ArrayList<String> results = new ArrayList<String>();
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while(input.ready())
			{
				results.add(input.readLine());
			}
			input.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		String[] retval = new String[1];
		return results.toArray(retval);
	}
}