package homework4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Homework4 {
	public static void main(String[] args) throws FileNotFoundException {

//		System.setIn(new FileInputStream("input.txt"));
		System.setIn(new FileInputStream("HW4/correct13.in"));
		Scanner in = new Scanner(System.in);
		System.setOut(new PrintStream("HW4/output.out"));
		String state = in.nextLine();
		List<Expression> exprs = new ArrayList<>();
		while (in.hasNext()) {
			String s = in.nextLine();
			exprs.add(ExpressionParser.parse(s));
		}
		Deduction deduction = new Deduction(state, exprs);
		Expression alpha = deduction.getAlpha();
		List<Expression> res = new ArrayList<>();
		List<Expression> hyp = deduction.getHyp();
		Correctness correct = new Correctness(exprs, hyp, alpha);
		if (correct.isCorrect()) {
			if (alpha == null) {
				res = exprs;
			} else {
				res = deduction.getDeduction();
			}
		} else {
			List<String> errs = correct.getErrors();
			for (int i = 0; i < errs.size(); i++) {
				System.out.print(errs.get(i));
			}
		}

		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i).printExp());
		}
		in.close();
	}

}
