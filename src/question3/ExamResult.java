package question3;

import java.util.Arrays;
import java.util.Scanner;

public class ExamResult {
	public String moduleName;
	public Integer size;
	public Integer[] marks;
	public Integer A = 0, B = 0, C = 0, D = 0, E = 0;
	//ArrayList<Integer> marks = new ArrayList<Integer>();
	
	ExamResult(String module){
		moduleName = module;
	}
	
	public void readMarks(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of marks: ");
		size = scan.nextInt();
		marks = new Integer[size];
		for (int i = 0; i < size; i++){
			System.out.print("Enter mark " + (i+1) + " : ");
	        marks[i] = scan.nextInt();
		}
		//scan.close();
		Arrays.sort(marks);
	}
	
	public String getModuleName(){
		return moduleName;
	}
	
	public String toString(){
		return Arrays.toString(marks);
	}
	public Integer getMin(){
		return marks[0];
	}
	public Integer getMax(){
		return marks[size-1];
	}
	public Double getAvg(){
		int sum = 0;
	    int n = size;
	    for (int i = 0; i < n; i++)
	        sum += marks[i];
	    return ((double) sum) / n; 
	}
	public Integer numberOfGradeA(){
		for (int i = 0; i < size; i++){
			if(marks[i] >= 70 && marks[i] <= 100) 
				A++;
		}
		return A;
	}
	public Integer numberOfGradeB(){
		for (int i = 0; i < size; i++){
			if(marks[i] >= 60 && marks[i] <= 69) 
				B++;
		}
		return B;
	}
	public Integer numberOfGradeC(){
		for (int i = 0; i < size; i++){
			if(marks[i] >= 50 && marks[i] <= 59) 
				C++;
		}
		return C;
	}
	public Integer numberOfGradeD(){
		for (int i = 0; i < size; i++){
			if(marks[i] >= 40 && marks[i] <= 49) 
				D++;
		}
		return D;
	}
	public Integer numberOfGradeE(){
		for (int i = 0; i < size; i++){
			if(marks[i] >= 0 && marks[i] <= 39) 
				E++;
		}
		return E;
	}
	public void printReport(){
		System.out.println(getModuleName());
		System.out.println("The total number of grades: " + size);
		System.out.println("The highest mark: " + getMin());
		System.out.println("The lowest mark: " + getMax());
		System.out.println("The average mark: " + getAvg());
		System.out.println("The number of grades in each category:");
		System.out.println("The number of As: " + numberOfGradeA());
		System.out.println("The number of Bs: " + numberOfGradeB());
		System.out.println("The number of Cs: " + numberOfGradeC());
		System.out.println("The number of Ds: " + numberOfGradeD());
		System.out.println("The number of Es: " + numberOfGradeE());
	}
	
}
