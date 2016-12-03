package spellchecker;


/* program: Spell Checker
 * author: Mateusz Janusz (mjanu001@gold.ac.uk)
 * date: Nov/Dec 2016
 * 
 * input: 
 * 	dictionary
 * 	file to spellcheck
 * 	name of the new file
 *  optional: own replacements
 *  
 * output:
 * 	new spell-checked text file
 *	optional: dictionary with added new words
 * 
 * description:
	 * Run the program. 
	 * Enter name of the file with extension to spellcheck. 
	 * Enter name for the new file. 
	 * If there is any misspelled word, choose one from the options:
	  * 	Press (1) to replace with Levenshtein algorithm suggestion: <suggestedword>
	  *		Press (2) to replace with SoundEx algorithm suggestion: <suggestedword>
	  *		Press (3) to enter your own replacement -> (then enter your own replacament)
	  * 	Press (4) to ignore and move to next word
	  *		Press (5) to add that word to the dictionary 
 * 
 */


import java.io.IOException;

public class SpellCheckerTest {
	public static void main(String[] args) throws IOException{
		new SpellChecker();
	}
}
