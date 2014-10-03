import java.util.*;

public class Variable implements Expression {

	char c;

	public Variable(char a) {
		this.c = a;
	}

	public boolean equalTree(Expression b) {
		if (!(b instanceof Variable))
			return false;
		Variable d = (Variable) b;
		return c == d.c;
	}
	
	public String printExp() {
		return "" + c;
	}
	

	public boolean almostEqualTree(Expression b, Map<Character, Expression> list) {
		// for (char i:list.keySet())
		if (!list.containsKey(c)) {
			list.put(c, b);
			return true;
		}
		return b.equalTree(list.get(c));
	}
}