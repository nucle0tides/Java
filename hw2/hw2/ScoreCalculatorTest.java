package hw2;

public class ScoreCalculatorTest {
	public static void main(String[] args)
	{ 
		ScoreCalculator sc = new ScoreCalculator(5000, 2000, 100, 1000);
		sc.start(10); // for a ten-letter word
		 System.out.println(sc.getPossibleScore(0));
		 System.out.println("Expected 50000");
		 System.out.println(sc.getPossibleScore(50001));
		 System.out.println("Expected 0");
		 System.out.println(sc.getPossibleScore(20000));
		 System.out.println("Expected 30000");

		 sc.applyHintPenalty();
		 sc.applyHintPenalty();
		 System.out.println(sc.getPossibleScore(0));
		 System.out.println("Expected 46000");
	}
}
