
public class ExpressionParser {

	private final char IMP = '-';
	private final char OPEN = '(';
	private final char OR = '|';
	private final char AND = '&';
	private final char NOT = '!';

	private String lexem;
	private int nextperm;
	Expression res;

	private Expression negation() {
		// assert !good;
		Expression a;
		if (lexem.charAt(nextperm) >= 'A' && lexem.charAt(nextperm) <= 'Z') {
			a = new Variable(lexem.charAt(nextperm));
			nextperm++;
		}  else if (lexem.charAt(nextperm) == OPEN) {
			nextperm++;
			a = expr();
			nextperm++;
		} else if (lexem.charAt(nextperm) == NOT) {
			nextperm++;
			a = new Not(negation());
		} else {
			a = null;
		}
		return a;
	}
	private Expression conjunction() {
		// assert !good;
		Expression a = negation();
		while (lexem.charAt(nextperm) == AND) {
			nextperm++;
			a = new And(a, negation());
		}
		return a;
	}
	
	private Expression disjunction() {
		// assert !good;
		Expression a = conjunction();
		while (lexem.charAt(nextperm) == OR) {
			nextperm++;
			a = new Or(a, conjunction());
		}
		return a;
	}

	private Expression expr() {
		Expression a = disjunction();
		if (lexem.charAt(nextperm) == IMP) {
			nextperm += 2;
			a = new Implication(a, expr());
		}
		return a;
	}

	private ExpressionParser(String a) {
		nextperm = 0;
		lexem = a.replaceAll("\\s", "").concat(".");
		res = expr();
	}

	public static Expression parse(String a) {
		return new ExpressionParser(a).res;
	}

}
