import java.io.Serializable;

public class Node  implements Serializable{
    private Object data; // Stores any kind of data 
    private Node next;

    public Node(Object obj) { // Constructor 
        data = obj;
        next = null;
    }

    // Link access methods
    public void setNext(Node nextPtr) {
        next = nextPtr;
    }

    public Node getNext() {
        return next;
    }

    // Data access methods
    public void setData(Object obj) {
        data = obj;
    }

    public Object getData() {
        return data;
    }

    public String toString() {
        return data.toString();
    }
}