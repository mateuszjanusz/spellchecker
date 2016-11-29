package spellchecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellChecker {
	static ArrayList<String> list = new ArrayList<String>();
	static ArrayList<String> dict = new ArrayList<String>();
	static ArrayList<String> m = new ArrayList<String>(); //list of misspelled words
	
	public static void scanFile() throws FileNotFoundException {
		
		
		
		//copy dictionary to the new array list
		Scanner dictionary = new Scanner(new File("dictionary.txt"));
		while (dictionary.hasNext()) {
			dict.add(dictionary.next());
		}
		dictionary.close();
		
		//ask for the file to check spelling 
		System.out.println("Enter file name: ");
		Scanner scan = new Scanner(System.in);
		String fileName = scan.next().trim();
		//scan.close();
		
		//scan file and copy all words to the new array list
		Scanner file = new Scanner(new File(fileName));
		file.useDelimiter("[ ():;,!?.]+"); //skip any punctuation
		
		while (file.hasNext()) {
				//String word = file.next();
				list.add(file.next());
		}
		file.close();
		
		//print words from file to check
		//System.out.println("Words: " + list.toString());
		
		/*-----------------------------------------------*/
		//loop through all words in my array to find out if dictionary contains these words
		for (int i=1; i<list.size(); i++){
			if(search(dict, list.get(i)) != true){ //if not found
				m.add(list.get(i));
				//System.out.print(list.get(i) + ", ");
			} 
		}
		System.out.println("Words misspelled: " + m.toString());
		System.out.println();
		
		if(m.isEmpty()) { //if no words are misspelled
			System.out.print("None. Well done!"); 
		} else {
			System.out.println("Enter '1' for SoundEx algorithm or '2' for Levenshtein algorithm:");
			int opt = scan.nextInt();
			if (opt == 2){
				Scanner repl = new Scanner(System.in);
				System.out.println("Suggested words using Levenshtein algorithm: ");
				for (int j=0; j<m.size(); j++){
					System.out.println("-> " + m.get(j) + ": ");
					LnSuggest(m.get(j), dict);
					System.out.println();
					System.out.println("Enter 1 to accept the replacement of the word or 2 to enter your own replacement");
					
						if(repl.nextInt() == 1){
							//replace word with the suggestion
						}
						if(repl.nextInt() == 2){
							System.out.println("Enter you replacement: ");
						}
				repl.close();
				}
			}//opt2
			
			if (opt == 1){
				System.out.println("Suggested words using SoundEx algorithm: ");
				for (int i=1; i<list.size(); i++){
					
					}
				}
			}
	}//met
		
	
	//using a simple loop, we look for a misspelled word; 
	//if the word is not found in the dictionary, it returns false which means that the word is misspelled
	private static boolean search(ArrayList<String> dict, String targetValue){
		for(String s: dict){
			if(s.equals(targetValue))
				return true;
		}
		return false;
	}
	
	private static void LnSuggest(String targetValue, ArrayList<String> dict){
		for(String s: dict){
			int dist = Levenshtein.distance(targetValue, s);
			if(dist == 1 ){
				System.out.print(s + ", ");
				break; //suggest only one word
			}
			
		}
	}
	private static void SxSuggest(){ 
		
	}
	
}//class
