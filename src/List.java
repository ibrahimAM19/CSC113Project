public class List {
    private Node head;
    private Node tail;
    private String Name;

    public List() {
        head = tail = null;
        Name = "No Name"; 
    }

    public List(String name) {
        head = tail = null;
        Name = name;
    }
    

    public boolean isEmpty() {
        return head == null; 
    }
    public Node getHead() {
    	return head;
    }

    // Standard method to add at the beginning 
    public void insertAtFront(Object obj) {
        Node newnode = new Node(obj);
        newnode.setNext(head);
        if (isEmpty())
            head = tail = newnode;
        else
            head = newnode;
    }

    // Standard method to add at the end 
    public void insertAtBack(Object obj) {
        Node newnode = new Node(obj);
        if (isEmpty())
            head = tail = newnode;
        else {	
            tail.setNext(newnode); //  (tail = node n-1) -> new node
            tail = newnode; // tail = new node
        }
    }

    // Method to remove from the front
    public Object removeFromFront() {
        if (isEmpty())
            return null;

        Node first = head;
        if (head == tail) // only one node
            head = tail = null;
        else 
            head = head.getNext();

        return first.getData();
    }
    public Object removeFromBack() {
    	if (isEmpty()) {
    		return null;
    	}
    	
    	Node current = head;
    	if(current.getNext() == null) {
    		head = tail =null;
    		return current.getData();
    	}
    	
    	Node previous = null;
    	while( current.getNext() != null) {
    		previous = current; // pervious -> n-1
    		current= current.getNext(); // (current = tail)-> n
    	}
    	previous.setNext(null);
    	tail = previous; // now nothing point to current so is dislinked to the list
    	return current.getData();
    }
    public boolean removeByIndex(int index) {
    	if( index == 0 ) {
    		removeFromFront();
    		return true;
    	}
    	
    	if( index == size() -1 ) {
    		removeFromBack();
    		return true;
    	}
    	
		Node current = head;
		Node previous = null;
		int count = 0;
		while(current != null) {
			if( count == index) {
				previous.setNext(current.getNext());
				return true;
			}
				
			count++;
			previous = current;
			current = current.getNext();
			
		}
		return false;
    }
    public Object getElementAt(int i) {
    	if (i > size() ) return null; // index out of bounds
    	Node element = head;
    	int count = 0;
    	while(element != null && count<i) {
    		element = element .getNext();
    		count++;
    	}
    	return element.getData();
    	
    }
    public int size() {
    	int size = 0;
    	if( isEmpty() ) return size;
    	
    	Node current = head;
    	while(current.getNext() != null) {
    		current =current.getNext();
    		size++;
    	}
    	return size;
    }
    public void print() {
    	if (isEmpty()) { //
            System.out.println("The list is empty.");
            return;
        }

        Node current = head;
        int count = 1;
        while (current != null) { 
            System.out.println(count + "-"+current.getData()); 
            current = current.getNext(); 
            count++;
        }
        
    }
   }
