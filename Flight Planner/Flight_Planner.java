/*
 * File: Data_Structures.java
 * ---------------------------------
 */

import acm.util.*;

import java.util.*;
import java.io.*;

import acm.program.*;

public class Data_Structures extends ConsoleProgram {

	public void run(){
		
		rd = Read_file("flights.txt");
		Setup();
		while(true){
			city = readLine("Enter the starting city:");
			cities.add(city);
			Check_valid(city);
			if(count != 1){
				println("This is not a valid city choice, please enter a valid choice:");
			}
			else{
				break;
			}
		}
		Find_Destinations(city);
	}
	
	private void Setup(){
		println("Welcome to Flight Planner!");
		println("Here is a list of the cities in our database:");
		Read_file(rd);
		Iterator <String> i = destinations.keySet().iterator();
		while (i.hasNext()){
			println(i.next());
		}
		println("Let's plan a round-trip route!");
	}
	
	private int Check_valid(String city){
		count = 0;
		Iterator <String> i = destinations.keySet().iterator();
		while (i.hasNext()){
			if(city.equals(i.next())){
				count = 1;
			}
		}
		return count;
	}
	
	private BufferedReader Read_file(String file){
		
		BufferedReader rd = null;
		
		while(rd == null){
			
			try{
				rd = new BufferedReader(new FileReader(file));
			}
			
			catch (IOException ex){
				throw new ErrorException(ex);
			}
		}
		
		return rd;
	}
	
	private void Read_file(BufferedReader rd){
		try{
			while(true){
				String line = rd.readLine();
				if(line == null){
					break;
				}
				if(line.length() != 1){
					line1 = line.substring(0, line.indexOf("-") - 1);
					String temp_line = line.substring(line.indexOf(">") + 2);
					temp_line = temp_line.substring(0, temp_line.length() - 1);
					line2.add(temp_line);
				}
				else{
					destinations.put(line1, (ArrayList) line2.clone());
					for(int i = 0; i < line2.size(); i++){
						line2.clear();
					}
				}
			}
			destinations.put(line1, (ArrayList) line2.clone());
			rd.close();
		}
			
		catch (IOException e){
			throw new ErrorException(e);
		}
	}
	
	private void Find_Destinations(String city){
		Iterator <String> i = destinations.keySet().iterator();
		ArrayList<String> Current_List = new ArrayList<String>();
		while (i.hasNext()){
			String current = i.next();
			if(current.equals(city)){
				println("From " + city + " you can fly directly to:");
				for(int j = 0; j < destinations.get(current).size(); j++){
					println(destinations.get(current).get(j));
					Current_List.add(destinations.get(current).get(j));
				}
			}
		}
		city_previous = city;
		Next_loop(Current_List);
	}
	
	private void Next_loop(ArrayList<String> Current_List){
		count = 0;
		while(count == 0){
			city = readLine("Where do you want to go to from " + city_previous + " ?");
			cities.add(city);
			for(int k = 0; k < Current_List.size(); k++){
				if(city.equals(Current_List.get(k))){
					count = 1;
				}
			}
			if(count == 0){
				println("This is not a valid city choice, please enter a valid choice:");
			}
		}
		if(city.equals(cities.get(0))){
			String list = "";
			for(int k = 0; k < cities.size() - 1; k++){
				list += cities.get(k) + " -> ";
			}
			println("The route you have chosen is: " + list + cities.get(0));}
		else{Find_Destinations(city);}
	}
	
	//Instance Variables
	int count;
	String line1 = "";
	ArrayList<String> line2 = new ArrayList<String>();
	String city;
	String city_previous;
	ArrayList<String> cities = new ArrayList<String>();
	HashMap<String, ArrayList<String>> destinations = new HashMap<String, ArrayList<String>>();
	BufferedReader rd;
}
