/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;
import java.util.Arrays;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		while(nPlayers > 4){
			nPlayers = dialog.readInt("Cannot be more than 4! Please, enter number of players");
		}
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		
		upper_score = new int[nPlayers];
		lower_score = new int[nPlayers];
		
		for (int i = 0; i < nPlayers; i++) {
			upper_score[i] = 0;
			lower_score[i] = 0;
		}
		
		player_categories = new int[nPlayers][N_CATEGORIES];
		for (int k = 0; k < nPlayers; k++){
			for (int l = 0; l < N_CATEGORIES; l++){
				player_categories[k][l] = 0;
			}
		}
		
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		
		for(int categ = 0; categ < N_SCORING_CATEGORIES; categ++){
			for(int player = 1; player <= nPlayers; player++){
				//Player's turn to play
				display.printMessage(playerNames[player - 1] + "'s turn. Click \"Roll Dice\" button to roll the dice."); 
				display.waitForPlayerToClickRoll(player);
				
				//First dice roll
				int[] dice = new int[N_DICE];
				for(int i = 0; i < N_DICE; i++){
					dice[i] = rgen.nextInt(1, 6);
				}
				display.displayDice(dice);
				
				//Select dice to re-roll - this runs twice
				for(int turn = 2; turn <= 3; turn++){	
					display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\"."); 
					display.waitForPlayerToSelectDice();
					for(int j = 0; j < N_DICE; j++){
						if(display.isDieSelected(j)){
							dice[j] = rgen.nextInt(1, 6);
						}
					}
					display.displayDice(dice);
				}
				
				//Assign a category
				display.printMessage("Select a category for this roll."); 
				int category = display.waitForPlayerToSelectCategory();
				while(player_categories[player-1][category] != 0){
					display.printMessage("You have already selected this category! Please select another one."); 
					category = display.waitForPlayerToSelectCategory();
				}
				player_categories[player-1][category] = 1;
				
				//Evaluate score
				int score = 0;
				HashMap<Integer, Integer> dice_map = new HashMap<Integer, Integer>();
				
				for(int h = 0; h < N_DICE; h++){
					for(int number = 1; number <= 6; number++){
						if(dice[h] == number){
							if(dice_map.containsKey(number)){
								dice_map.put(number, dice_map.get(number) + 1);
							}
							else{
								dice_map.put(number, 1);
							}
						}
					}
				}
				
				if(category == 1){
					if(dice_map.get(1) != null){
						score = dice_map.get(1);
						upper_score[player-1] += score;
					}
				}
				if(category == 2){
					if(dice_map.get(2) != null){
						score = dice_map.get(2) * 2;
						upper_score[player-1] += score;
					}
				}
				if(category == 3){
					if(dice_map.get(3) != null){
						score = dice_map.get(3) * 3;
						upper_score[player-1] += score;
					}
				}
				if(category == 4){
					if(dice_map.get(4) != null){
						score = dice_map.get(4) * 4;
						upper_score[player-1] += score;
					}
				}
				if(category == 5){
					if(dice_map.get(5) != null){
						score = dice_map.get(5) * 5;
						upper_score[player-1] += score;
					}
				}
				if(category == 6){
					if(dice_map.get(6) != null){
						score = dice_map.get(6) * 6;
						upper_score[player-1] += score;
					}
				}
				if(category == 9){
					Iterator <Integer> i = dice_map.keySet().iterator();
					boolean test = false;
					while(i.hasNext()){
						if(dice_map.get(i.next()) >= 3){
							test = true;
						}
					}
					if(test){
						Iterator <Integer> new_i = dice_map.keySet().iterator();
						while(new_i.hasNext()){
							int current = new_i.next();
							score += (int)current * (int)dice_map.get(current);
						}
					}
				
					lower_score[player-1] += score;
					
				}
				if(category == 10){
					Iterator <Integer> i = dice_map.keySet().iterator();
					boolean test = false;
					while(i.hasNext()){
						if(dice_map.get(i.next()) >= 4){
							test = true;
						}
					}
					if(test){
						Iterator <Integer> new_i = dice_map.keySet().iterator();
						while(new_i.hasNext()){
							int current = new_i.next();
							score += (int)current * (int)dice_map.get(current);
						}
					}
				
					lower_score[player-1] += score;
					
				}
				if(category == 11){
					if(dice_map.size() == 2){
						score = 25;
					}
					
					lower_score[player-1] += score;
				}
				if(category == 12){
					boolean test = false;
					if(dice_map.size() >= 4){
						Arrays.sort(dice);
						for(int i = 0; i < N_DICE - 1; i++){
							if(dice[i] + 1 == dice[i + 1]){
								test = true;
							}
						}
					}
					if(test){
						score = 30;
					}
					
					lower_score[player-1] += score;
					
				}
				if(category == 13){
					if(dice_map.size() == 5){
						score = 40;
					}
				}
				if(category == 14){
					if(dice_map.size() == 1){
						score = 50;
					}
					
					lower_score[player-1] += score;
					
				}
				if(category == 15){
					for(int h = 0; h < N_DICE; h++){
						score += dice[h];
					}
					
					lower_score[player-1] += score;
					
				}
				
				display.updateScorecard(category, player, score); 
			}	
		}
		
		for(int user = 1; user <= nPlayers; user++) {
			display.updateScorecard(7, user, upper_score[user-1]);
			if(upper_score[user-1] > 63){
				display.updateScorecard(8, user, 35);
			}
			display.updateScorecard(16, user, lower_score[user-1]);
			display.updateScorecard(17, user, upper_score[user-1] + lower_score[user-1]);
		}
	}
		
/* Private instance variables */
	private int nPlayers;
	private int[] upper_score;
	private int[] lower_score;
	private String[] playerNames;
	private int[][] player_categories;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
