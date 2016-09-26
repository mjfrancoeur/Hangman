/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import java.awt.Font.*;

public class HangmanCanvas extends GCanvas {

	/* initializing x and y coordinates for hangman's bodyparts */
	private int yCoordinateForHead; 
	private int yCoordinateForUpperArm;
	private int yCoordinateForHip;
	private int yCoordinateForFoot;
	
	/* GLabels that will display the word (initially with hyphens representing words
	 * which will updated with correct character guesses) and displayGuesses will display incorrect guesses
	 *
	 */
	private GLabel displayWord;
	private GLabel displayGuesses;
	private String guesses;	
		
	
	/* sets number of guesses, to determine body parts to add */
	private int nGuesses;
	
	
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
	
		yCoordinateForHead = (getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH; 
		yCoordinateForUpperArm = yCoordinateForHead + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		yCoordinateForHip = yCoordinateForHead + HEAD_RADIUS + BODY_LENGTH;
		yCoordinateForFoot = yCoordinateForHip + LEG_LENGTH;
		
		this.removeAll();			
		createScaffold();
		createGLabels();
		guesses = "";
		nGuesses = 10;
		
		
	}
	private void createGLabels() {
		
		int xCoordinate = getWidth() / 4;
		int yCoordinateOne = ((getHeight() - SCAFFOLD_HEIGHT)/2) + + SCAFFOLD_HEIGHT + 15;
		int yCoordinateTwo = yCoordinateOne + 25;
		
		displayGuesses = new GLabel("", xCoordinate, yCoordinateTwo); 
		displayGuesses.setFont("Helvetica-16");
		add(displayGuesses);
		
		displayWord = new GLabel("", xCoordinate, yCoordinateOne);
		displayWord.setFont("Helvetica-24");
		add(displayWord);
		
	}
	
	private void createScaffold() {
		
		/* X and Y Coordinates for GLine Scaffold */
		int centerWidth = getWidth() / 2;
		int scaffoldYOne = (getHeight() - SCAFFOLD_HEIGHT)/2; 
		int scaffoldYTwo = scaffoldYOne + SCAFFOLD_HEIGHT;

		/* create and add GLine scaffold */
		GLine scaffold = new GLine(centerWidth - BEAM_LENGTH, scaffoldYOne, centerWidth - BEAM_LENGTH, scaffoldYTwo);
		add(scaffold);
	
		/* create and add GLine beam */
		GLine beam = new GLine(centerWidth, scaffoldYOne, centerWidth - BEAM_LENGTH, scaffoldYOne);
		add(beam);
		
		/* create and add GLine rope */
		GLine rope = new GLine(centerWidth, scaffoldYOne, centerWidth, scaffoldYOne + ROPE_LENGTH);
		add(rope);
	
	}
	
	
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		displayWord.setLabel(word);	
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		guesses += letter;
		displayGuesses.setLabel(guesses);
		addBodyPart();
		nGuesses--;
	}
	
	
	private void addBodyPart() {
		
		
		switch(nGuesses) {
		case 10:
			addHead();
			break;
		case 9: 
			addSpine();
			break;
		case 8: 
			addRightArm();
			break;
		case 7: 
			addLeftArm();
			break;
		case 6:
			addRightLeg();
			break;
		case 5: 
			addLeftLeg();
			break;
		case 4: 
			addRightHand();
			break;
		case 3: 
			addLeftHand();
			break;
		case 2:	
			addRightFoot();
			break;
		case 1: 
			addLeftFoot();
			break;
		
		}
	}
	
	/*Methods for adding bodyparts */
	
	private void addHead() {
		GOval head = new GOval(getWidth() / 2 - HEAD_RADIUS, yCoordinateForHead, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
	 	add(head);
	}
	
	private void addSpine() {
		GLine spine = new GLine(getWidth() / 2, yCoordinateForHead + HEAD_RADIUS * 2, getWidth() / 2, yCoordinateForHead + HEAD_RADIUS + BODY_LENGTH); 
		add(spine);
		
	}
	
	private void addRightArm() {
		GLine rightArm = new GLine(getWidth() / 2, yCoordinateForUpperArm, getWidth() / 2 + UPPER_ARM_LENGTH, yCoordinateForUpperArm);
		add(rightArm);
	
	}
	
	private void addLeftArm() {
		GLine leftArm = new GLine(getWidth() / 2, yCoordinateForUpperArm, getWidth() / 2 - UPPER_ARM_LENGTH, yCoordinateForUpperArm);
		add(leftArm);
	
	}
	
	private void addRightLeg() {
		GLine rightHip = new GLine(getWidth() / 2, yCoordinateForHip, getWidth() / 2 + HIP_WIDTH, yCoordinateForHip);
		GLine rightLeg = new GLine(getWidth() / 2 + HIP_WIDTH, yCoordinateForHip, getWidth() / 2 + HIP_WIDTH, yCoordinateForFoot);  
		add(rightHip);
		add(rightLeg);
		
	}
	
	private void addLeftLeg() {
		GLine leftHip = new GLine(getWidth() / 2, yCoordinateForHip, getWidth() / 2 - HIP_WIDTH, yCoordinateForHip);
		add(leftHip);
		GLine leftLeg = new GLine(getWidth() / 2 - HIP_WIDTH, yCoordinateForHip, getWidth() / 2 - HIP_WIDTH, yCoordinateForFoot);
		add(leftLeg);
	}
	
	private void addRightHand() {
		GLine rightHand = new GLine(getWidth() / 2 + UPPER_ARM_LENGTH, yCoordinateForUpperArm, getWidth() / 2 + UPPER_ARM_LENGTH, yCoordinateForUpperArm + LOWER_ARM_LENGTH);
		add(rightHand);
		
	}
	
	private void addLeftHand() {
		GLine leftHand = new GLine(getWidth() / 2 - UPPER_ARM_LENGTH, yCoordinateForUpperArm, getWidth() / 2 - UPPER_ARM_LENGTH, yCoordinateForUpperArm + LOWER_ARM_LENGTH);
		add(leftHand);
	
	}
	
	private void addRightFoot() {
		GLine rightFoot = new GLine(getWidth() / 2 + HIP_WIDTH, yCoordinateForFoot, getWidth() / 2 + HIP_WIDTH + FOOT_LENGTH, yCoordinateForFoot);
		add(rightFoot);
	}
	
	private void addLeftFoot() {
		GLine leftFoot = new GLine(getWidth() / 2 - HIP_WIDTH, yCoordinateForFoot, getWidth() / 2 - HIP_WIDTH - FOOT_LENGTH, yCoordinateForFoot);
		add(leftFoot);
	}


	
	
	
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

/* Constant for number of incorrect guesses allowed */	
	private static final int NUMBER_OF_GUESSES = 10;

	
}
