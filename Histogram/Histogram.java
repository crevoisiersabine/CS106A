/*
 * File: Histogram.java
 * ---------------------------------
 */

import acm.util.*;

import java.util.*;
import java.io.*;

import acm.program.*;

public class Histogram extends ConsoleProgram{
	
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
	
	private void read_lines_for_histogram(BufferedReader rd){
		
		count_list = new int[11];
		star_list = new String[11];
		for(int i = 0; i < count_list.length; i++){
			count_list[i] = 0;
		}
		
		star_list[0] = "00-09";
		star_list[1] = "10-19";
		star_list[2] = "20-29";
		star_list[3] = "30-39";
		star_list[4] = "40-49";
		star_list[5] = "50-59";
		star_list[6] = "60-69";
		star_list[7] = "70-79";
		star_list[8] = "80-89";
		star_list[9] = "90-99";
		star_list[10] = "  100";

		try{
			while(true){
				String line = rd.readLine();
				if(line == null){
					break;
				}
				if(Integer.valueOf(line) >= 0 && Integer.valueOf(line) < 10){
					count_list[0]++;
				}
				if(Integer.valueOf(line) >= 10 && Integer.valueOf(line) < 20){
					count_list[1]++;
				}
				if(Integer.valueOf(line) >= 20 && Integer.valueOf(line) < 30){
					count_list[2]++;
				}
				if(Integer.valueOf(line) >= 30 && Integer.valueOf(line) < 40){
					count_list[3]++;
				}
				if(Integer.valueOf(line) >= 40 && Integer.valueOf(line) < 50){
					count_list[4]++;
				}
				if(Integer.valueOf(line) >= 50 && Integer.valueOf(line) < 60){
					count_list[5]++;
				}
				if(Integer.valueOf(line) >= 60 && Integer.valueOf(line) < 70){
					count_list[6]++;
				}
				if(Integer.valueOf(line) >= 70 && Integer.valueOf(line) < 80){
					count_list[7]++;
				}
				if(Integer.valueOf(line) >= 80 && Integer.valueOf(line) < 90){
					count_list[8]++;
				}
				if(Integer.valueOf(line) >= 90 && Integer.valueOf(line) < 100){
					count_list[9]++;
				}
				if(Integer.valueOf(line) == 100){
					count_list[10]++;
				}
			}
			
			rd.close();
		}
		
		catch (IOException e){
			throw new ErrorException(e);
		}
	}
	
	private void print_stars(){
		for(int i = 0; i < count_list.length; i++){
			int j = count_list[i];
			String star = "*";
			String r = repeat_string(star, j);
			println(star_list[i] + ": " + r);
		}
	}
	
	private String repeat_string(String str, int times){
		StringBuilder ret = new StringBuilder();
		for(int i = 0; i < times; i++){
			ret.append(str);
		}
		return ret.toString();
	}
	
	public void run(){
		String file = readLine("File: ");
		BufferedReader rd = Read_file(file);
		read_lines_for_histogram(rd);
		print_stars();
	}
	
	private int[] count_list;
	private String[] star_list;
	
}
