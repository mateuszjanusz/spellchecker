package question3;

public class ExamResultTest {
	public static void main(String[] args){
		ExamResult maths = new ExamResult("Maths");
		
		maths.readMarks();
		System.out.println();
		System.out.println();
		maths.printReport();
	}
}
