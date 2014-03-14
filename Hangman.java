/*
 * File: Hangman.java
 * ----------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.util.Arrays;

public class Hangman extends ConsoleProgram {
	
	
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		}

    public void run() {
    	
    	canvas.reset();
    	println("Welcome to Hangman!");
    	String word = Choose_a_word();
    	String[] player = Setup_player(word);
    	String[] answer = Setup_answer(word);
    	
    	Play_the_game(player, answer);
		
	}
    
    private String Choose_a_word(){
    	int number_of_words = words.getWordCount();
    	String word = words.getWord(rgen.nextInt(0, number_of_words));
    	return(word);
    }
    
    private String[] Setup_player(String word){
    	int length = word.length();
    	String[] player = new String[length];
    	for(int j = 0; j < length; j++){
    		player[j] = "-";
    	}
    	return player;
    }
    	
    private String[] Setup_answer(String word){
    	int length = word.length();
    	String[] answer = new String[length];
    	for(int k = 0; k < length; k++){
    		answer[k] = word.substring(k, k+1);
    	}
    	return answer;
    }
    
    private String print_array(String[] array){
    	String new_string = "";
    	for(int i = 0; i < array.length; i++){
    		new_string += array[i];
    	}
    	return new_string;
    }
    
    private void Play_the_game(String[] player, String[] answer){
    	int counter = 0;
    	while(true){
//    		println(print_array(answer));
    		println("The word now looks like this: " + print_array(player));
    		canvas.displayWord(print_array(player));
    		println("You have " + (8 - counter) + " guesses left.");
    		String letter = readLine("Your guess: ");
    		letter = letter.toUpperCase();
    		counter = Check_if_in_word(letter, answer, player, counter);
    		
    		if(counter == 8){
    			break;
    		}
    		
    		if(check_if_finished(player) == false){
    			break;
    		}
    		
    	}
    	if(check_if_finished(player) == false){
    		println("The word was: " + print_array(player));
    		println("You win!");
    	}
    	else{
    		println("The word was: " + print_array(answer));
    		println("You lose!");
    	}
    }
    
    private int Check_if_in_word(String letter, String[] answer, String[] player, int counter) {
    	int counter_2 = 0;
    	for(int j = 0; j < answer.length; j ++){
    		if(letter.equals(answer[j])){
    			player[j] = letter;
    		}
    		else{
    			counter_2++;
    		}
    	}
    	if(counter_2 == answer.length){
    		canvas.noteIncorrectGuess(letter.charAt(0));
    	}
    	return counter_2/answer.length + counter;
    }
    
    private boolean check_if_finished(String[] player){
    	int count = 0;
    	for(int i = 0; i < player.length; i++){
    		if(player[i].contains("-")){
    			count++;
    		}
    	}
    	if(count != 0){return true;}
    	else{return false;}
    }

    private HangmanLexicon words = new HangmanLexicon();
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private HangmanCanvas canvas;
    
}
