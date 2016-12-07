
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {

	/* Array of words, from HangmanLexicon.txt */
	private ArrayList<String> arrayListOfWords = new ArrayList<String>();
	private BufferedReader rd = null;

	/**
	 * reads in strings from HangmanLexicon.txt and adds them into ArrayList
	 */

	private BufferedReader openFile() {
		while (rd == null) {
			try {
				rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			} catch (IOException ex) {
				throw new ErrorException(ex);
			}
		}
		return rd;

	}

	public HangmanLexicon() {

		rd = openFile();
		try {
			while (true) {
				String line = rd.readLine();
				if (line == null)
					break;
				arrayListOfWords.add(line);
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return arrayListOfWords.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return (arrayListOfWords.get(index));
	};

}
