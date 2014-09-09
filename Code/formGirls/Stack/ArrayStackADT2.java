//������ �� ������� ����� ������
//���� this. ��� ������ stack., ���� ������� �������� � ������ stack

public class ArrayStackADT {
    //���������� ����� - private                        
    //private static Object[] elements = new Object[10];
    private Object[] elements = new Object[10];
    private int size = 0;
                      
    public static void push(Object o) {
        ensureCapacity(size + 1);
        elements[size++] = o;     //���������� ������ NULL'�
    } 

    private void ensureCapacity(int capacity) {
        if (elements.length < capacity) {
            Object[] e = new Object[capacity * 2];
            for (int i = 0; i < size; i++) {
                e[i] = elements[i];  
            }
            elements = e;
        }       
    }

    public Object pop() {
        assert size > 0;     //��������� � -ea
        Object result = elements[size - 1];
        elements[size - 1] = null;
        size--;
        return result;
        
    }
     
    public Object peek() {
        assert size > 0;
        return elements[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    } 
 
}
