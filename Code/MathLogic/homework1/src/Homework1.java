import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

import javafx.util.Pair;

public class Homework1 extends MakeExpr{
	
	public static void main(String[] args) throws FileNotFoundException {

//		System.setIn(new FileInputStream("input.txt"));
		System.setIn(new FileInputStream("maxtest1.in"));
		System.setOut(new PrintStream("output.txt"));
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		for (int i = 0; i < n; i++) {
			String s = in.next();
			Expression exp = ExpressionParser.parse(s);
			int ax = compWithAx(exp);
			if (ax != -1) {
				statements.add(exp);
				System.out.println("(" + (i + 1) + ") " + s + " (Сх.акс. " + (ax + 1)
						+ ")");
			} else {
				Pair<Integer, Integer> mp = modusPonens(exp);
				if (mp != null) {
					statements.add(exp);
					System.out.println("(" + (i + 1) + ") " + s + " (M.P. "
							+ (mp.getKey() + 1) + ", " + (mp.getValue() + 1) + ")");
				} else {
					System.out.println("(" + (i + 1) + ") " + s + " Не доказано");
				}
			}
		}
		in.close();
	}
}