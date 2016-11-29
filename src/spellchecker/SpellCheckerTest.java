package spellchecker;

import java.io.FileNotFoundException;

public class SpellCheckerTest {
	public static void main(String[] args) throws FileNotFoundException{
//		System.out.println(SoundEx.soundex("Mateusz") + ": " + "Mateusz");
//		System.out.println(SoundEx.soundex("book") + ": " + "book");
//		System.out.println(SoundEx.soundex("wrhr") + ": " + "wrhr");
//		
//		System.out.println(Levenshtein.distance("Mateusz", "Janusz"));
		
		SpellChecker.scanFile();
	}
}
