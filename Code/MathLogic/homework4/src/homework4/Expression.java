package homework4;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Expression {     
    public boolean equalTree(Expression b); 
    public boolean almostEqualTree(Expression b, Map<String, Expression> list); 
    public String printExp();    
    public boolean freeEntry(Variable xx); // ���� �� ��������� ��������� xx
    public Expression replaceVar(Variable from, Expression to); //������ ���� ��������� ���������
    public Map<String, Expression> getVariables(Expression b); //������ ������ ���������� � �� ����� � b
    public boolean haveBoundEntry(Variable xx, Set<String> vars, List<String> quants); //������ �� ���������� �� �������� ��� �����������
}
