import java.util.Map;

public class Or implements Expression {
  
	Expression e1;
	Expression e2;
    public Or(Expression e1, Expression e2) {
    	this.e1 = e1;
    	this.e2 = e2;
    }
    
    public String printExp() {
		return "(" + e1.printExp() + ") | (" + e2.printExp() + ")";
	}

    public boolean equalTree(Expression b) {
    	if (!(b instanceof Or))
    		return false;
    	Or c = (Or)b;
        return e1.equalTree(c.e1) && e2.equalTree(c.e2);
    } 
    public boolean almostEqualTree(Expression b, Map<Character, Expression> list) {
    	if (!(b instanceof Or))
    		return false;
    	Or c = (Or)b;
        return e1.almostEqualTree(c.e1, list) && e2.almostEqualTree(c.e2, list);
    } 
}