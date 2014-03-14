/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import java.awt.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		
	GLine scaffold1 = new GLine(getWidth()*0.25, 40, getWidth()*0.25, 40 + SCAFFOLD_HEIGHT);
	GLine scaffold2 = new GLine(getWidth()*0.25, 40, getWidth()*0.25 + BEAM_LENGTH, 40);
	GLine scaffold3 = new GLine(getWidth()*0.25 + BEAM_LENGTH, 40, getWidth()*0.25 + BEAM_LENGTH, 50);
	add(scaffold1);
	add(scaffold2);	
	add(scaffold3);	
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		if(getElementAt(80, getHeight()*0.75) != null){
			GObject object = getElementAt(80, getHeight()*0.75);
			remove(object);
		}
		GLabel label = new GLabel(word);
		add(label, 80, getHeight()*0.75);
		Font big = new Font("SansSerif", Font.PLAIN, 20);
		label.setFont(big);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		
		print_guessed(letter);
		guess_count += drawbody(guess_count);
		
	}
	
	
	private int drawbody(int guess){
		
		int count = 0;
		switch (guess) {
		case 0: GOval head = new GOval(getWidth()*0.25 + BEAM_LENGTH - HEAD_RADIUS/2, 50, HEAD_RADIUS, HEAD_RADIUS);
				add(head);
				count++;
				break; 
		case 1:	GLine torso = new GLine(getWidth()*0.25 + BEAM_LENGTH, 50 + HEAD_RADIUS, getWidth()*0.25 + BEAM_LENGTH, 50 + 2*HEAD_RADIUS + BODY_LENGTH);
				add(torso);
				count++;
				break;
		case 2:	GLine arm = new GLine(getWidth()*0.25 + BEAM_LENGTH - UPPER_ARM_LENGTH, 50 + HEAD_RADIUS + 15, getWidth()*0.25 + BEAM_LENGTH  + UPPER_ARM_LENGTH, 50 + HEAD_RADIUS + 15);
				add(arm);
				count++;
				break;
		case 3:	GLine hand1 = new GLine(getWidth()*0.25 + BEAM_LENGTH - UPPER_ARM_LENGTH, 50 + HEAD_RADIUS + 15, getWidth()*0.25 + BEAM_LENGTH  - UPPER_ARM_LENGTH, 50 + HEAD_RADIUS + 15 + LOWER_ARM_LENGTH);
				GLine hand2 = new GLine(getWidth()*0.25 + BEAM_LENGTH + UPPER_ARM_LENGTH, 50 + HEAD_RADIUS + 15, getWidth()*0.25 + BEAM_LENGTH  + UPPER_ARM_LENGTH, 50 + HEAD_RADIUS + 15 + LOWER_ARM_LENGTH);
				add(hand1);
				add(hand2);
				count++;
				break;
		case 4:	GLine hip = new GLine(getWidth()*0.25 + BEAM_LENGTH - HIP_WIDTH, 50 + 2*HEAD_RADIUS + BODY_LENGTH, getWidth()*0.25 + BEAM_LENGTH + HIP_WIDTH, 50 + 2*HEAD_RADIUS + BODY_LENGTH);
				add(hip);
				count++;
				break;
		case 5:	GLine leg1 = new GLine(getWidth()*0.25 + BEAM_LENGTH - HIP_WIDTH, 50 + 2*HEAD_RADIUS + BODY_LENGTH, getWidth()*0.25 + BEAM_LENGTH - HIP_WIDTH, 50 + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
				GLine leg2 = new GLine(getWidth()*0.25 + BEAM_LENGTH + HIP_WIDTH, 50 + 2*HEAD_RADIUS + BODY_LENGTH, getWidth()*0.25 + BEAM_LENGTH + HIP_WIDTH, 50 + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
				add(leg1);
				add(leg2);
				count++;
				break;
		case 6:	GLine foot1 = new GLine(getWidth()*0.25 + BEAM_LENGTH - HIP_WIDTH, 50 + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, getWidth()*0.25 + BEAM_LENGTH - HIP_WIDTH -FOOT_LENGTH, 50 + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
				add(foot1);
				count++;
				break;
		case 7:	GLine foot2 = new GLine(getWidth()*0.25 + BEAM_LENGTH + HIP_WIDTH, 50 + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, getWidth()*0.25 + BEAM_LENGTH + HIP_WIDTH + FOOT_LENGTH, 50 + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
				add(foot2);
				count++;
				break;
		}
		return count;
	}
	
	
	private void print_guessed(char letter){
		
		GLabel hist_guess = new GLabel(Character.toString(letter));
		add(hist_guess, 80 + move_letter, getHeight()*0.88);
		move_letter += hist_guess.getWidth();
	}
	
	

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 260;
	private static final int BEAM_LENGTH = 100;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 90;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 45;
	private static final int LOWER_ARM_LENGTH = 30;
	private static final int HIP_WIDTH = 25;
	private static final int LEG_LENGTH = 60;
	private static final int FOOT_LENGTH = 15;
	
	private int guess_count = 0;
	private double move_letter = 0;
}
