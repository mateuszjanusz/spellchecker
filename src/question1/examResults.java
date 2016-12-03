package question1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class examResults {
	private static Scanner scan;
	private static Integer input;
	private static Integer A = 0, B = 0, C = 0, D = 0, E = 0;
	
	public static void main(String[] args){
		enterResults();
		}
	
	public static void enterResults(){
		scan = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<Integer>(); //arraylist for the marks
		
		System.out.println("Please input results (ended by negative value)");
		
		//add input values to the list one by one
		//SKIP THE LAST NEGATIVE VALUE
		do {
			input = scan.nextInt();
			list.add(input);
		}while (!(input < 0));
			int last = list.size();
			list.remove(last-1);
			scan.close();
			//allocate marks to the appropriate category
			for (int i = 0; i < list.size(); i++){
				if(list.get(i) >= 70 && list.get(i) <= 100) 
					A++;
				if(list.get(i) >= 60 && list.get(i) <= 69) 
					B++;
				if(list.get(i) >= 50 && list.get(i) <= 59) 
					C++;
				if(list.get(i) >= 40 && list.get(i) <= 49) 
					D++;
				if(list.get(i) >= 0 && list.get(i) <= 39) 
					E++;
			}
			
			System.out.println("EXAM RESULTS");
			System.out.println("The total number of grades: " + list.size()); //list.size() returns the number of elements in the array list
			System.out.println("The highest mark: " + Collections.max(list)); //collections.max() prints maximum value from the specific array list
			System.out.println("The lowest mark: " + Collections.min(list)); //collections.min() prints minimum value from the specific array list
			System.out.println("The average mark: " + average(list));
			System.out.println("The number of grades in each category:");
			System.out.println("The number of As: " + A);
			System.out.println("The number of Bs: " + B);
			System.out.println("The number of Cs: " + C);
			System.out.println("The number of Ds: " + D);
			System.out.println("The number of Es: " + E);
	}
	//calculate average of all the marks
	public static double average(ArrayList<Integer> list) {
	    if (list == null || list.isEmpty())
	        return 0.0;
	    int sum = 0;
	    int n = list.size();
	    for (int i = 0; i < n; i++)
	        sum += list.get(i);
	    return ((double) sum) / n; 
	}
}
