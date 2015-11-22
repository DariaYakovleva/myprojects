
import java.util.List;


public class Application implements Expression {
  
	Expression e1;
	Expression e2;
    public Application(Expression e1, Expression e2) {
    	this.e1 = e1;
    	this.e2 = e2;
    }
    
    public String printExp() {
    	return "(" + e1.printExp() + " " + e2.printExp() + ")";
	} 
    public List<String> freeVariables(List<String> cur) {
    	List<String> res = e1.freeVariables(cur);
    	res.addAll(e2.freeVariables(cur));
    	return res;
    }
    
    public Expression substituton(List<String> booked, Expression var, Expression sub) {
    	return new Application(e1.substituton(booked, var, sub), e2.substituton(booked, var, sub));
    }
}