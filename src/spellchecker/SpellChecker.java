package spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpellChecker {
	static List<String> dict = new ArrayList<String>();
	static List<String> soundex_dict = new ArrayList<String>();
	static List<String> new_words = new ArrayList<String>();
	
	public SpellChecker() throws IOException{
		dictionary();
		corrector(askInput(), askOutput());
		
	}
	//set up dictionary
	public void dictionary() throws IOException{
		FileReader dictReader = new FileReader("dictionary.txt");
		BufferedReader br = new BufferedReader(dictReader);
		String inputLine;
		//copy dictionary to the array list
		while ((inputLine = br.readLine()) != null) {
			dict.add(inputLine);
			soundex_dict.add(SoundEx.soundex(inputLine)); //add soundex code of that word to the other list
		}
		dictReader.close();
	}
	private static boolean searchDict(String word){
		/*
		using a simple loop, look for a misspelled word; 
		if the word is not found in the dictionary, it returns false which means that the word is misspelled
		*/
		for(String w: dict){
			if(w.equals(word))
				return true;
		}
		return false;
	}
	//method that asks user for input, hence no need to repeat the same lines of code
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
		System.out.print("Enter file name (with an extension .txt) to spell check: ");
		return getInput();
	}
	
	public String askOutput(){
		System.out.print("Enter output file name (with an extension .txt): ");
		return getInput();
	}
	
	public String whichOption(String word, String levenshtein, String soundex){
		System.out.println("\n " + word + " is misspelled.");
		
		System.out.println("Press (1) to replace with Levenshtein algorithm suggestion: " + levenshtein);
		System.out.println("Press (2) to replace with SoundEx algorithm suggestion: " + soundex);
		System.out.println("Press (3) to enter your own replacement");
		System.out.println("Press (4) to ignore and move to next word");
		System.out.println("Press (5) to add that word to the dictionary");
		
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
			//FileWriter outDict = new FileWriter("dictionary.txt",true); //to add words to the current dictionary
			boolean found; 
			int countWords = 0;
			int missWords = 0;
			int punctuation = 0;
			String line; //current line
			String curr = ""; //current word
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
						countWords++;
						found = searchDict(curr.toLowerCase()); //change to lower case and search the word in dictionary
						if (found == true){ 
							//word found in dictionary
							lineOut += curr;
						} 
						else //word not found in dictionary
							{
							missWords++;
							String levenshtein = Levenshtein.Suggest(curr, dict);
							String soundex = SoundEx.Suggest(curr, dict, soundex_dict);
							
							String miss = whichOption(curr, levenshtein, soundex);
							if (miss.length() > 0){
								char toDo = miss.charAt(0);
								switch (toDo)
								{
								case '1' : //replace with Levenshtein's suggestion
									lineOut += levenshtein;
									break;
								case '2' : //replace with SoundEx's suggestion
									lineOut += soundex;
									break;
								case '3' : //replace with users suggestion
									System.out.println("Please enter the replacement:");
									curr = getInput();
									lineOut += curr;
								case '4' : //ignore the word
									lineOut += curr;
									break;
								case '5' : //add the word to the dictionary
									dict.add(curr.toLowerCase()); 
									new_words.add(curr.toLowerCase());
									Collections.sort(dict); //sort words alphabetically 
									//print out sorted words into dictionary file
									FileWriter dictWriter = new FileWriter("dictionary.txt");
									PrintWriter outDict = new PrintWriter(dictWriter);
									for (String outputLine : dict) {
										outDict.println(outputLine);
									}
									outDict.flush();
									outDict.close();
									dictWriter.close();
//									outDict.write("\n");
//									outDict.write(curr.toLowerCase());//appends the string to the file
									missWords--;
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
						punctuation++;
					}
				}
				out.print(lineOut); //print off that line to the new file
				out.print("\n");
				lineOut = ""; //reset current line
			}
			
			//outDict.close();
			out.close();
			in.close();
			System.out.println("Done! Your file has been saved as " + fileOut);
			System.out.println("Words in total: " + countWords);
			System.out.println("Misspelled words: " + missWords);
			if(new_words.size()>0)
				System.out.println("New words added to the dictionary: " + new_words.toString());
			System.out.println("Punctuations: " + punctuation);
			System.exit(0);
			
		} catch(Exception e){
			System.out.println(e);
		}
	}//end class corrector
	
	
}//class
