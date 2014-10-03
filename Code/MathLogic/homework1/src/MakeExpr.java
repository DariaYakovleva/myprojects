import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

public class MakeExpr {
	public static final String[] AXIOMS = { "A->(B->A)",
			"(A->B)->((A->B->C)->(A->C))", "A&B->A", "A&B->B", "A->(B->(A&B))",
			"A->(A|B)", "B->(A|B)", "(A->C)->((B->C)->((A|B)->C))",
			"(A->B)->((A->!B)->!A)", "!!A->A", };
	
	public static List<Expression> statements = new ArrayList<>();

	void init() {
		for (int i = 0; i < AXIOMS.length; i++) {
			statements.add(ExpressionParser.parse(AXIOMS[i]));
		}
	}

	public static boolean equalT(Expression a, Expression b) {
		return a.equalTree(b);
	}

	public static boolean almostEqualT(Expression a, Expression b) {
		Map<Character, Expression> list = new HashMap<>();
		return a.almostEqualTree(b, list);
	}

	public static int compWithAx(Expression a) {
		for (int i = 0; i < AXIOMS.length; i++) {
			if (almostEqualT(ExpressionParser.parse(AXIOMS[i]), a)) {
				return i;
			}
		}
		return -1;
	}
	public static int compWithState(Expression a) {
		for (int i = 0; i < statements.size(); i++) {
			if (almostEqualT(statements.get(i), a)) {
				return i;
			}
		}
		return -1;
	}

	public static Pair<Integer, Integer> modusPonens(Expression a) {
		for (int i = 0; i < statements.size(); i++) {
			Expression imp = new Implication(statements.get(i), a);
			for (int j = 0; j < statements.size(); j++) {
				if (equalT(statements.get(j), imp)) {
					return new Pair<Integer, Integer>(i, j);
				}
			}
		}
		return null;
	}
	
	public static String printExp(Expression a) {
		return a.printExp();
	}


}
