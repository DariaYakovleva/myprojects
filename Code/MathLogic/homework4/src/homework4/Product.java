package homework4;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Product implements Expression {
	Expression e1;
	Expression e2;
	public Product(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	public String printExp() {
		return "(" + e1.printExp() + "*" + e2.printExp() + ")";
	}

	public boolean equalTree(Expression b) {
		if (!(b instanceof Product))
			return false;
		Product c = (Product)b;
		return e1.equalTree(c.e1) && e2.equalTree(c.e2);
	} 

	public boolean almostEqualTree(Expression b, Map<String, Expression> list) {
		if (!(b instanceof Product))
			return false;
		Product c = (Product)b;
		return e1.almostEqualTree(c.e1, list) && e2.almostEqualTree(c.e2, list);
	} 
	public boolean freeEntry(Variable xx) {
		return e1.freeEntry(xx) | e2.freeEntry(xx);
	}
	public Expression replaceVar(Variable from, Expression to) {
		return new Product(e1.replaceVar(from, to), e2.replaceVar(from, to));
	}
	public Map<String, Expression> getVariables(Expression b) {
		Map<String, Expression> res = new HashMap<>();
		if (!(b instanceof Product))
			return res;
		Product c = (Product)b;
		res.putAll(e1.getVariables(c.e1));
		res.putAll(e2.getVariables(c.e2));
		return res;
	}

	public boolean haveBoundEntry(Variable xx, Set<String> vars, List<String> quants) {
		return e1.haveBoundEntry(xx, vars, quants) | e2.haveBoundEntry(xx, vars, quants);
	}
}
