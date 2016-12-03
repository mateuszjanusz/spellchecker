package spellchecker;

import java.util.List;

public class Levenshtein {
	
	public static int distance(String s, String t){
		char s_i; // ith character of string s
		char t_j; // jth character of string t
		
		if (s == t)
			return 0;
		if (s.length() == 0)
			return t.length();
		if (t.length() == 0)
			return s.length();
		
		//construct a matrix of t.length rows and s.length columns.
		int[][] d = new int[s.length()+1][t.length()+1];
		
		
		//initialize the first row to t.length
		for (int i=0; i<=t.length(); i++){
			d[0][i] = i;
		}
		//initialize the first column to s.length
		for (int j=0; j<=s.length(); j++){
			d[j][0] = j;
		}
		
		for (int k=1; k<=s.length(); k++){
			 s_i = s.charAt (k-1);
			for (int l=1; l<=t.length(); l++){
				t_j = t.charAt (l-1);
				
				int cost = (s_i == t_j) ? 0 : 1;
				//set cell d[k][l] of the matrix to the minimum
				d[k][l] = Minimum(d[k-1][l]+1, d[k][l-1]+1, d[k-1][l-1] + cost);
			}
		}
		//return the distance which is found in the lower right corner of the matrix
		return d[s.length()][t.length()];
	}
	
	private static int Minimum(int a, int b, int c){
		int current;
		
		current = a;
		if (b < current)
			current = b;
		if (c < current)
			current = c;
		
		return current;
	}
	
	public static String Suggest(String targetValue, List<String> dict){
		for(String s: dict){
			int dist = Levenshtein.distance(targetValue, s);
			if(dist == 1 ){
				return s;
			}
		}
		return null;
	}
	
}
