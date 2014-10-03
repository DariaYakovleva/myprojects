import java.util.Map;

public interface Expression {      
    public boolean equalTree(Expression b); 
    public boolean almostEqualTree(Expression b, Map<Character, Expression> list); 
    public String printExp();
}
