package spellchecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellChecker {
	static ArrayList<String> list = new ArrayList<String>();
	static ArrayList<String> dict = new ArrayList<String>();
	static ArrayList<String> m = new ArrayList<String>(); //list of misspelled words
	
	public SpellChecker() throws FileNotFoundException{
		dictionary();
		corrector(askInput(), askOutput());
		
	}
	
	public void dictionary() throws FileNotFoundException{
		//copy dictionary to the new array list
		Scanner dictionary = new Scanner(new File("dictionary.txt"));
		while (dictionary.hasNext()) {
				dict.add(dictionary.next());
			}
		dictionary.close();
	}
	
	public String getInput(){
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			return input.readLine();
		} catch(Exception e){
			System.out.println(e);
		}
		return null;
	}
	
	public String askInput(){
		System.out.print("Enter file name (with an extension) to spell check: ");
		return getInput();
	}

	public String askOutput(){
		System.out.print("Enter output file name (with an extension): ");
		return getInput();
	}
	public String askOpt(String word, String replace){
		System.out.println("\n " + word + " is misspelled.");
		System.out.println("Press (1) to replace with my suggestion: " + replace);
		System.out.println("Press (2) to enter your own replacement");
		System.out.println("Press (3) to ignore and move to next word");
		try{
			return getInput();
		}
		catch(Exception e){};
		return null;
	}
	public void corrector(String fileIn, String fileOut){
		
		try{
			BufferedReader in = new BufferedReader(new FileReader(fileIn));
			PrintWriter out = new PrintWriter(new FileWriter(fileOut));
			boolean found; 
			String line; //current line
			String curr = ""; //curent word
			String lineOut = ""; //line to print out
			char ch;
			
			while((line= in.readLine()) != null){ //check if no more lines can be read
				
				for (int i = 0; i <= line.length(); i++)
				{
					ch = ' ';
					if (i < line.length())
						ch = line.charAt(i);
					//constructing the word
					if (Character.isLetter(ch) || (ch == '\047' && curr.length() != 0)) //if letter or APOSTROPHE
					{
						curr = curr + ch;
					}
					else if (curr.length() != 0){
						found = search(dict, curr.toLowerCase()); //change word to lowercase and search
						if (found == true){ 
							//word found in dictionary
							lineOut += curr;
							//System.out.println("word found");
						} 
						else //word not found in dictionary
							{
							String replace = LnSuggest(curr, dict);
							String miss = askOpt(curr, replace);
							if (miss.length() > 0){
								char toDo = miss.charAt(0);
								switch (toDo)
								{
								case '1' : //replace with my suggestion
									lineOut += replace;
									break;
								case '2' : //replace with users suggestion
									System.out.println("Please enter a replacement:");
									curr = getInput();
									lineOut += curr;
									break;
								case '3' : //ignore the word
									lineOut += curr;
									break;
								} //end switch
							}
						} //if word not found
						curr = ""; //reset current word
						if(i < line.length())
							lineOut += ch; //add character that followed the current word
					} else {
						if(i < line.length()) //include the space after comma
							lineOut += ch;
					}
				}
				out.print(lineOut); //print off that line to the new file
				out.print("\n");
				lineOut = ""; //reset current line
			}
			out.close();
			in.close();
			System.out.println("Done! Your file has been saved as " + fileOut);
			System.exit(0);
			
		} catch(Exception e){
			System.out.println(e);
		}
	}
//	public static void scanFile() throws FileNotFoundException {
//		
//		//ask for the file to check spelling 
//		System.out.println("Enter file name: ");
//		Scanner scan = new Scanner(System.in);
//		String fileName = scan.next().trim();
//		//scan.close();
//		
//		//scan file and copy all words to the new array list
//		Scanner file = new Scanner(new File(fileName));
//		file.useDelimiter("[ ():;,!?.]+"); //skip any punctuation
//		
//		while (file.hasNext()) {
//				//String word = file.next();
//				list.add(file.next());
//		}
//		file.close();
//		
//		//print words from file to check
//		//System.out.println("Words: " + list.toString());
//		
//		/*-----------------------------------------------*/
//		//loop through all words in my array to find out if dictionary contains these words
//		for (int i=1; i<list.size(); i++){
//			if(search(dict, list.get(i)) != true){ //if not found
//				m.add(list.get(i));
//				//System.out.print(list.get(i) + ", ");
//			} 
//		}
//		System.out.println("Words misspelled: " + m.toString());
//		System.out.println();
//		
//		if(m.isEmpty()) { //if no words are misspelled
//			System.out.print("None. Well done!"); 
//		} else {
//			System.out.println("Enter '1' for SoundEx algorithm or '2' for Levenshtein algorithm:");
//			int opt = scan.nextInt();
//			if (opt == 2){
//				Scanner repl = new Scanner(System.in);
//				System.out.println("Suggested words using Levenshtein algorithm: ");
//				for (int j=0; j<m.size(); j++){
//					System.out.println("-> " + m.get(j) + ": ");
//					LnSuggest(m.get(j), dict);
//					System.out.println();
//					System.out.println("Enter 1 to accept the replacement of the word or 2 to enter your own replacement");
//					
//						if(repl.nextInt() == 1){
//							//replace word with the suggestion
//						}
//						if(repl.nextInt() == 2){
//							System.out.println("Enter you replacement: ");
//						}
//				repl.close();
//				}
//			}//opt2
//			
//			if (opt == 1){
//				System.out.println("Suggested words using SoundEx algorithm: ");
//				for (int i=1; i<list.size(); i++){
//					
//					}
//				}
//			}
//	}//met
		
	
	//using a simple loop, we look for a misspelled word; 
	//if the word is not found in the dictionary, it returns false which means that the word is misspelled
	private static boolean search(ArrayList<String> dict, String targetValue){
		for(String s: dict){
			if(s.equals(targetValue))
				return true;
		}
		return false;
	}
	
	private String LnSuggest(String targetValue, ArrayList<String> dict){
		for(String s: dict){
			int dist = Levenshtein.distance(targetValue, s);
			if(dist == 1 ){
				return s;
			}
			
		}
		return null;
	}
	//private static void SxSuggest(){ 
		
	//}
	
}//class
