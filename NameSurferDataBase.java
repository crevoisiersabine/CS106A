import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import acm.util.ErrorException;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		
		BufferedReader rd = null;
		
		while(rd == null){
			
			try{
				rd = new BufferedReader(new FileReader(filename));
			}
			
			catch (IOException ex){
				throw new ErrorException(ex);
			}
		}
		
		nameList = new ArrayList<String>();
		
		try{
			while(true){
				String line = rd.readLine();
				if(line == null){
					break;
				}
				nameList.add(line);
			}
			rd.close();
		}
			
		catch (IOException e){
			throw new ErrorException(e);
		}
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		NameSurferEntry entry = null;
		// You need to turn this stub into a real implementation //
		for(int i = 0; i < nameList.size(); i++){
			StringTokenizer tokenizer = new StringTokenizer(nameList.get(i));
			String first = tokenizer.nextToken();
			if(name.equals(first)){
				entry = new NameSurferEntry(nameList.get(i)); 
			}
		}
		return entry;
	}
	
	//Instance Variables
	private ArrayList<String> nameList;
}

