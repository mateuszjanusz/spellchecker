package spellchecker;
/* This class implement soundex algorithm as it was described in The Art of Computer Programming by D.Knuth.
 * 0: AEHIOUWY // THESE LETTERS ARE SKIPPED
 * 1: BFPV
 * 2: CGJKWSXZ
 * 3: DT
 * 4: L
 * 5: MN
 * 6: R
 * 
 */
public class SoundEx {
	public static final char[] map = {
		    //A  B   C   D   E   F   G   H   I   J   K   L   M
		    '0','1','2','3','0','1','2','0','0','2','2','4','5',
		    //N  O   P   W   R   S   T   U   V   W   X   Y   Z
		    '5','0','1','2','6','2','3','0','1','0','2','0','2'
		  };
	
	public static String soundex(String s){ //it is static, hence we don't need to instantiate this class
		
		String word = s.toUpperCase(); //convert given word to uppercase
		StringBuffer input = new StringBuffer();
		
		char current, previous = 'n';
		
		for (int i = 0; i < word.length() && input.length() < 4 && (current = word.charAt(i)) != ','; i++){
			//check if the word is alphabetic
			if(current >= 'A' && current <= 'Z' && current != previous){
				if(i == 0) //first character stays unchanged
					input.append(current);
				else {
					char m = map [current-'A']; //mapping char to number in the hashmap
					if (m!='0')
						input.append(m);
				}
			}
		}
		if(input.length() == 0)
			return null;
		for (int i = input.length(); i < 4; i++)
			input.append('0');
		
		return input.toString();
		
	}
}
