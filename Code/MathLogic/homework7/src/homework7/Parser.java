package homework7;

public class Parser {

	private final char PLUS = '+';
	private final char MUL = '*';
	private final char POW = '^';

	private String lexem;
	private int nextterm;
	
	public Parser(String a) {
		nextterm = 0;
		lexem = a.replaceAll("\\s", "").concat(".");
	}
	
	private Expression term() {
		Expression a;
		if (lexem.charAt(nextterm) == 'w') {
			nextterm++;
			a = new W();
		} else if (lexem.charAt(nextterm) == '(') {
			nextterm++;
			a = expr();
			nextterm++;
		} else {
			Integer x = 0;
			while (lexem.charAt(nextterm) >= '0' && lexem.charAt(nextterm) <= '9') {
				 x = x * 10 + (lexem.charAt(nextterm) - '0');
				 nextterm++;
			}
			a = new Const(x);
		}
		return a;
	}
	
	private Expression multiplier() {
		Expression a = term();
		while (lexem.charAt(nextterm) == POW) {
			nextterm++;
			a = new Power(a, multiplier());
		}
		return a;
	}
	
	private Expression summand() {
		Expression a = multiplier();
		while (lexem.charAt(nextterm) == MUL) {
			nextterm++;
			a = new Multiplier(a, multiplier());
		}
		return a;
	}
	
	private Expression expr() {
		Expression a = summand();
		while (lexem.charAt(nextterm) == PLUS) {
			nextterm++;
			a = new Summand(a, expr());
		}
		return a;
	}
	
	public Expression parse() {
		return expr();
	}
}