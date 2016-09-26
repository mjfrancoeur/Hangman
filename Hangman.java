/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */


import acm.program.*;
import acm.util.*;

import java.util.*;





public class Hangman extends ConsoleProgram {

	/* iVars and Constants */
	
	private static final int NUMBER_OF_GUESSES = 10; /* number of guesses */
	private RandomGenerator rgen = new RandomGenerator();
	private HangmanLexicon hlex = new HangmanLexicon();

	private String wordWithDashes;
	private String word;
	private ArrayList<Integer> index = new ArrayList<Integer>();
	private HangmanCanvas canvas;

	
	
    public void run() {
    	runHangman();
    }
    
    private void runHangman() {
    	canvas.reset(); 
    	word = getRandomWord();
		wordWithDashes = dasherizeString(word);
		initiateHangman();
    }

    public void init() {
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
    

	private String getRandomWord() {
		int randomInt = rgen.nextInt(0, hlex.getWordCount());
		String randomWord = hlex.getWord(randomInt);
		return randomWord;
	}

	/**
	 * Adds hyphens to String result for each char in argument String str. Returns the hyphenated String.
	 * @param str
	 * @return
	 */
	
	private String dasherizeString(String str) {
		String result = "";
		for(int i = 0; i < str.length(); i++) {
			result = result + '-';
		}
		return result;
	}
	

	private void initiateHangman() {
		int nGuesses = NUMBER_OF_GUESSES;
		println("Welcome to Hangman!");
		
		while(true) {
			if(wordWithDashes.equals(word)) {
				println("You guessed the word: " + word);
				canvas.displayWord(wordWithDashes);
				println("You win. If only life were as easy as this game...");
  				break;
			} else if(nGuesses == 0) {
  				println("You are completely hung.");
  				println("The word was: " + word);
  				println("You lose. Have you heard of that book called the dictionary?");
  				break;
			} else {
				canvas.displayWord(wordWithDashes);
				println("The word now looks like this: " + wordWithDashes);
	      		println("You have " + nGuesses + " guesses left.");
	      		String guess = readLine("Your guess: ");
	      		guess = guess.toUpperCase();
	      		char chGuess = guess.charAt(0);
	      		if (checkGuess(chGuess)) {
	      			println("That guess is correct.");
	      			updateWordWithDashes(chGuess);
	      		} else {
	      			println("There are no " + guess + "'s in the word.");
	      			nGuesses--;
	      			canvas.noteIncorrectGuess(chGuess);
	      		}
			}	
		}
		String playAgain = readLine("Would you like to play again? (enter Y or N): ");
		playAgain = playAgain.toUpperCase();
		if(playAgain.equals("Y")) {
			println("Bring it, cowboy.");
			runHangman();
		} else {
			println("*SNIFF* It's not me, it's you.");
			println("GAME OVER");
		}
		
	}
	
/**
 * Returns true if argument char is contained within String word.
 * If true, adds the positions where that char appears within the String word to ArrayList index.
 * @param ch
 * @return
 */	

  private boolean checkGuess(char ch) {  
	  
	  boolean result = false;
	  index.clear();
	  
	  for(int i = 0; i < word.length(); i++) {
		 if(ch == word.charAt(i)) {
			 index.add(i);
			 result = true;
		 }
	  }
	  return result;
  }    	  
	  
  
  /**
   * Updates the string displayed on the screen, using the argument char to display letters that are correctly guessed;
   * unguessed letters remain hyphens. 
   * @param ch
   */
  
  private void updateWordWithDashes(char ch) {
	  int x;
	  for(int i = 0; i < index.size(); i++)	{  
		  x = index.get(i);
		  wordWithDashes = wordWithDashes.substring(0, x) + ch + wordWithDashes.substring(x+1);     	  
	  }
  }
	
	
}