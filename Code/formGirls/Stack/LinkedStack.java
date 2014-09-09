//final - ���-��, ������������ 1 ��� (��������� ��� ��������) 
//����� ����� ����������������, ��������, �������������
//���� �������� ���� size, �� ����� �������������� ������ �� (�������� ������� �����������)
//���� ������ ��� ������ ������ super.size();

public class LinkedStack extends AbstractStack implements Stack {
    private Node head;

    public void push(Object element) {
        //Node(); - ��� ������, �. �. ���� ����� ����������� 
        //LinkedStack() - � ��� �����, �.�. ��� ��� ������������ � ����� �������� ����������� �� ���������

        //����������� ����� ������� ������ ���
        head = new Node(element, head);
        size++;
    }

    protected Object popImpl() {
        Object result = head.value;
        head = head.next;
        return result;
    }
         
    private static class Node {
        private final Object value;
        private final Node next;

        //���� ����������� �� ��������. �� �� �������, ��� ��� �� �������������� ���� 
        //public Node() {                                                              
        //}
        
        //���� ����������
        public Node(Object v, Node n) {
            value = v;
            next = n;
        }

    }
}