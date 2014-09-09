public class ArrayDequeSingleton {
    private static Object[] elements = new Object[10];
    private static int size = 0;
    private static int s = 10;
    private static int last = 0;
    private static int first = 0;


    private static int module(int x) {
        return (x % s + s) % s;
    }

    private static void ensureCapacity(int f, int l) {
        if (f > s) {
            last -= s;
            first -= s;
        }        
        if (l < 0) {
            last += s;
            first += s;
        }
        if (s == l - f) {
            Object[] e = new Object[(l - f) * 2]; 
            int num = 0;
            for (int i = f; i < l; i++) {
                e[num] = elements[module(i)];
                num++;
            }
            elements = e;
            s = (l - f) * 2;
            first = 0;
            last = num;
        }  
    }

    //pre: -
    //post: Your element has been added at the end of the deque; size++;
    //inv: Order of other elements;
    public static void addLast(Object x) {  
        ensureCapacity(first, last);
        size++;
        last++;
        elements[module(last - 1)] = x;
    }

    //pre: -
    //post: Your element has been added at the begining of the deque; size++;
    //inv: Order of other elements;     
    public static void addFirst(Object x) {         
        ensureCapacity(first, last);
        size++;
        first--;
        elements[module(first)] = x;
    }

    //pre: size > 0
    //post: Returned element has been removed from the begining of the deque; size--;
    //inv: Order of other elements;     
    public static Object removeFirst() {
        assert size > 0; 
        Object result = elements[module(first)];
        elements[module(first)] = null;
        first++;
        size--;
        return result;
    }
 
    //pre: size > 0
    //post: Returned element has been removed from the end of the deque; size--;
    //inv: Order of other elements;     
    public static Object removeLast() {
        assert size > 0; 
        Object result = elements[module(last - 1)];
        elements[module(last - 1)] = null;
        last--;
        size--;    
        return result;
    }
    
    //pre: size > 0
    //post: Returned element is on the first place in the deque;
    //inv: Order of all elements; size;            
    public static Object peekFirst() {
        assert size > 0;
        return elements[module(first)];
    }
              
    //pre: size > 0
    //post: Returned element is on the last place in the deque;
    //inv: Oreder of all elements; size;
    public static Object peekLast() {
        assert size > 0;
        return elements[module(last - 1)];
    }

    //pre: -
    //post: Returned element is size of the deque;
    //inv: Oreder of all elements; size;
    public static int size() {
        return size;
    }

    //pre: -
    //post: Returns "true" if deque is empty and "flse" if it's not;
    //inv: Oreder of all elements; size;
    public static boolean isEmpty() {
        return size == 0;
    } 
 
}