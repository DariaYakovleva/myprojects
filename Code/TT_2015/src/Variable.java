import java.util.ArrayList;
import java.util.List;


public class Variable implements Expression {

	String c;

	public Variable(String a) {
		this.c = a;
	}

	public String printExp() {
		return c;
	}
	
	public List<String> freeVariables(List<String> cur) {
    	List<String> res = new ArrayList<String>();
    	boolean have = false;
    	for (String v : cur) {
    		if (c.equals(v)) {
    			have = true;
    			break;
    		}
    	}
    	if (!have) res.add(c);
    	return res;
    }
	
	public Expression substituton(List<String> booked, Expression var, Expression sub) {
		if (!c.equals(var.printExp())) return new Variable(c);
    	boolean have = false;
    	for (String v : booked) {
    		if (v.equals(var.printExp())) {
    			have = true;
    			break;
    		}
    	}
    	if (have) {
    		return new Variable(c);
    	}
    	return LambdaParser.parse(sub.printExp());
    }
}