package question2;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class examResults2 {
	private static Scanner scan;
	private static Integer A = 0, B = 0, C = 0, D = 0, E = 0; 
	private static String n;
	private static Double m;
	
	public static void main(String[] args) throws FileNotFoundException{
		enterResults();
		}
	
	public static void enterResults() throws FileNotFoundException{
		
		File inputFile = new File ("marks.txt");
		scan = new Scanner(inputFile);
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<Double> mark = new ArrayList<Double>();
		
		//if there is any new line, allocate its name and marks to the appropriate array lists 
			while(scan.hasNextLine()) { 
					n = scan.next();
					name.add(n); //add string to the array list of strings
					m = scan.nextDouble();
					mark.add(m); //add marks (doubles) to the array list of doubles
				}
			//allocate marks to the appropriate category
			for (int i = 0; i < mark.size(); i++){
				if(mark.get(i) >= 70 && mark.get(i) <= 100) 
					A++;
				if(mark.get(i) >= 60 && mark.get(i) <= 69) 
					B++;
				if(mark.get(i) >= 50 && mark.get(i) <= 59) 
					C++;
				if(mark.get(i) >= 40 && mark.get(i) <= 49) 
					D++;
				if(mark.get(i) >= 0 && mark.get(i) <= 39) 
					E++;
				}

			System.out.println("EXAM RESULTS");
			System.out.println("The total number of grades: " + mark.size()); //list.size() returns the number of elements in the array list
			System.out.println("The highest mark: " + Collections.max(mark)); //collections.max() prints maximum value from the specific array list
			System.out.println("The lowest mark: " + Collections.min(mark)); //collections.min() prints minimum value from the specific array list
			System.out.println("The average mark: " + average(mark));
			System.out.println("The number of grades in each category:");
			System.out.println("The number of As: " + A);
			System.out.println("The number of Bs: " + B);
			System.out.println("The number of Cs: " + C);
			System.out.println("The number of Ds: " + D);
			System.out.println("The number of Es: " + E);
	}
	
	public static double average(ArrayList<Double> list) {
	    if (list == null || list.isEmpty())
	        return 0.0;
	    int sum = 0;
	    int n = list.size();
	    for (int i = 0; i < n; i++)
	        sum += list.get(i);
	    return ((double) sum) / n; 
	}
}
