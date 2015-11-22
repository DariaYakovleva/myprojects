import java.util.List;

public interface Expression {      
    public String printExp();
    public List<String> freeVariables(List<String> cur);
    public Expression substituton(List<String> booked, Expression var, Expression sub);
}

