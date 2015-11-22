import java.util.List;
import java.util.ArrayList;

public class Lambda implements Expression {
  
	Expression e1;
	Expression e2;
    public Lambda(Expression e1, Expression e2) {
    	this.e1 = e1;
    	this.e2 = e2;
    }
    
    public String printExp() {
		return "(\\" + e1.printExp() + "." + e2.printExp() + ")";
	} 
    public List<String> freeVariables(List<String> cur) {
//    	List<String> res = e1.freeVariables(cur);
    	List<String> res = new ArrayList<>();
    	List<String> curr = new ArrayList<String>(cur);
    	curr.add(e1.printExp());
    	res.addAll(e2.freeVariables(curr));
    	return res;
    }
    public Expression substituton(List<String> booked, Expression var, Expression sub) {
    	List<String> cur = new ArrayList<String>(booked);
    	cur.add(e1.printExp());
    	return new Lambda(e1, e2.substituton(cur, var, sub));
    }

}