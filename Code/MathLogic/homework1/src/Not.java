import java.util.Map;

public class Not implements Expression {
  
	Expression e1;
    public Not(Expression e1) {
    	this.e1 = e1;
    }
    
    public String printExp() {
		return "!" + "(" + e1.printExp() + ") ";
	}

    public boolean equalTree(Expression b) {
    	if (!(b instanceof Not))
    		return false;
    	Not c = (Not)b;
        return e1.equalTree(c.e1);
    } 
    public boolean almostEqualTree(Expression b, Map<Character, Expression> list) {
    	if (!(b instanceof Not))
    		return false;
    	Not c = (Not)b;
        return e1.almostEqualTree(c.e1, list);
    } 
}