public interface Stack {
//������ ��� ��� ������    
//���������� ������ ������ e
//public abstract - �� ��������� � ������ ��������    
    public abstract void push(Object e);
    Object peek();
    Object pop();
    int size();
    boolean isEmpty();
}