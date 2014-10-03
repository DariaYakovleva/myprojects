import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

import javafx.util.Pair;

public class Homework2 extends MakeExpr {

	public static List<Expression> hypothesis = new ArrayList<>();
	public static Expression alpha;
	public static Expression state;
	
	public static int compWithHyp(Expression a) {
		for (int i = 0; i < hypothesis.size(); i++) {
			if (almostEqualT(hypothesis.get(i), a)) {
				return i;
			}
		}
		return -1;
	}

	public static void hyParser(String s) {
		int pos = 0;
		s = s.replaceAll("\\s", "");
		while (s.charAt(pos) != (char)(124)) {
			String exp = "";
			while (s.charAt(pos) != ',' && s.charAt(pos) != (char)(124)) {
				exp += s.charAt(pos);
				pos++;
			}
			
			if (s.charAt(pos) == ',') {
				hypothesis.add(ExpressionParser.parse(exp));
				pos++;
			} else {
				alpha = ExpressionParser.parse(exp);
			}
		}
		String exp = "";
		pos += 2;
		exp = "";
		for (int i = pos; i < s.length(); i++) {
			exp += s.charAt(i);
		}
		state = ExpressionParser.parse(exp);
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("proof0.in"));
		Scanner in = new Scanner(System.in);
		System.setOut(new PrintStream("output.txt"));
		String s = in.next();
		hyParser(s);
		System.out.println("alpha = " + alpha.printExp());
		for (int i = 0; i < hypothesis.size(); i++) {
			System.out.println("hyp" + i + " = " + hypothesis.get(i).printExp());
		}
		int n = in.nextInt();
		int number = 1;
		for (int i = 0; i < n; i++) {
			s = in.next();
			Expression exp = ExpressionParser.parse(s);
			int ax = compWithAx(exp);
			int hyp = compWithHyp(exp);
			if ((ax != -1) || (hyp != -1)) {
				statements.add(exp);
				if (ax != -1) {
					System.out.println("(" + (number) + ") " + s + " (Сх.акс. "
						+ (ax + 1) + ")");
				} else {
					System.out.println("(" + (number) + ") " + s + " (hyp)");
				}
				number++;
				Expression exp2 = new Implication(exp, new Implication(alpha, exp));
				statements.add(exp2);
				System.out.println("(" + (number) + ") " + exp2.printExp() + " (Сх.акс. 1)");
				number++;
				statements.add(new Implication(alpha, exp));
				System.out.println("(" + (number) + ") " + alpha.printExp() + " -> " + s + "(M.P. " + 
				(number - 1) + ", " + (number - 2) + ")");
			} else {
				Pair<Integer, Integer> mp = modusPonens(exp);
				if (mp != null) {
					statements.add(exp);
					Expression gj;
//					Expression gk;
					if (statements.get(mp.getKey()).equalTree(new Implication(statements.get(mp.getValue()), exp))) {
						gj = statements.get(mp.getValue());
//						gk = statements.get(mp.getKey());
					} else {
//						gk = statements.get(mp.getValue());
						gj = statements.get(mp.getKey());
					}
						//(A -> gj) -> ((A -> (gj -> gi)) -> (A -> gi))
						Expression tmp = new Implication(new Implication(alpha, new Implication(gj, exp)), new Implication(alpha, exp));
						System.out.println("(" + (number) + ") " + (new Implication(new Implication(alpha, gj), tmp)).printExp() + " (Сх.акс. 2");
						statements.add((new Implication(new Implication(alpha, gj), tmp)));
						number++;
						int fnumb = compWithState(new Implication(alpha, gj));
						System.out.println("(" + (number) + ") " + tmp.printExp() + " (М.Р. " + (number - 1) + " " + (fnumb + 1)+ ")");
						statements.add(tmp);
						number++;
						System.out.println("(" + (number) + ") " + s + " (M.P. " + (mp.getKey() + 1) + ", " + (mp.getValue() + 1) + ")");
						statements.add(exp);

				} else if (alpha.equalTree(exp)) {
//					1) A -> (A -> A) ax 1
//					2) (A -> (A -> A)) -> (A -> ((A -> A) -> A)) -> (A -> A) ax 2
//					3) (A -> ((A -> A) -> A)) -> (A -> A) MP 1, 2
//					4) A -> (A -> A) -> A ax 1
//					5) A -> A MP 3, 4
					Expression first = new Implication(exp, new Implication(exp, exp));
					Expression second = new Implication(new Implication(exp, 
							new Implication(new Implication(exp, exp), exp)), new Implication(exp, exp));
					
					Expression third = new Implication(exp, new Implication(new Implication(exp, exp), exp));
					System.out.println("(" + (number) + ") " + first.printExp() + " (Cx.акс. 1)");
					statements.add(first);
					number++;
					System.out.println("(" + (number) + ") " + (new Implication(first, second)).printExp() + " (Cx.акс. 2)");
					statements.add(new Implication(first, second));
					number++;
					System.out.println("(" + (number) + ") " + second.printExp() + " (М.Р. " + (number - 1) + " " + (number - 2) + ")");
					statements.add(second);
					number++;
					System.out.println("(" + (number) + ") " + third.printExp() + " (Cx.акс. 1)");
					statements.add(third);
					number++;
					System.out.println("(" + (number) + ") " + (new Implication(exp, exp)).printExp() + 
							" (М.Р. " + (number - 1) + " " + (number - 2) + ")");
					statements.add(new Implication(exp, exp));
				} else {
					System.out.println("(" + (number) + ") " + s
							+ " Не доказано");
				}
			}
			number++;
		}
		in.close();
	}
}