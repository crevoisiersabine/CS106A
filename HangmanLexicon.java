/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.util.*;
import java.io.*;

public class HangmanLexicon {
	
	
	public HangmanLexicon() {
		
		BufferedReader rd = null;
		
		while(rd == null){
			try{
				rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			}
			
			catch (IOException ex){
				throw new ErrorException(ex);
			}
		}
		
		wordList = new ArrayList<String>();
		
		try{
			while(true){
				String line = rd.readLine();
				if(line == null){
					break;
				}
				wordList.add(line);
			}
			rd.close();
		}
			
		catch (IOException e){
			throw new ErrorException(e);
		}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		
		String word = wordList.get(index);
		
//		switch (index) {
//			case 0: return "BUOY";
//			case 1: return "COMPUTER";
//			case 2: return "CONNOISSEUR";
//			case 3: return "DEHYDRATE";
//			case 4: return "FUZZY";
//			case 5: return "HUBBUB";
//			case 6: return "KEYHOLE";
//			case 7: return "QUAGMIRE";
//			case 8: return "SLITHER";
//			case 9: return "ZIRCON";
//			default: throw new ErrorException("getWord: Illegal index");
//		}
		
		return word;
		
	}
	
	private ArrayList<String> wordList;
}
