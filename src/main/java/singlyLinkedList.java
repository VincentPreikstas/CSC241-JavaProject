import java.util.Iterator;
 
public class singlyLinkedList<T> implements Iterable<T> {
 
    private class Node<T> {
        T data;
        Node<T> next;
 
        public Node(T data){
            this.data = data;
        }
 
        public Node(T data, Node<T> next){
            this(data);
            this.next = next;
        }
 
        public String toString(){
            return "" + data;
        }
    }
    
    public class LLIterator<U> implements Iterator<U> {
        singlyLinkedList<U> ll;
        singlyLinkedList<U>.Node<U> curr;
 
        public LLIterator(singlyLinkedList<U> ll){
            this.ll = ll;
            curr = null;
        }

	//This is here to check
	
        public boolean hasNext(){
            if (curr == ll.tail && ll.tail != null) return false;
            return true;
        }
 
        public U next(){
            if (curr == null)
                curr = ll.head;
            else curr = curr.next;
            return curr.data;
        }
    }
 
    private Node<T> head, tail;
    private int count;
 
    public singlyLinkedList(){
        count = 0;
    }
 
    public void prepend (T i){
        if (count == 0){
            head = tail = new Node<T>(i);
        }
        else {
            head = new Node<T>(i, head);
        }
        count++;
    }
    
    public void insert(T data, int index){
	if (index < 0 || index > count){
	    throw new IndexOutOfBoundsException("Index is out of bounds");
	} else {
	    if (index == 0){
		Node<T> nn = new Node<T>(data);
		nn.next = head;
		head = nn;
	    } else {
		Node<T> temp = head;
		for (int i = 0; i < index - 1; i++){
		    temp = temp.next;
		}
		Node<T> newNode = new Node<T>(data);
		Node<T> secondTemp = temp.next;
		temp.next = newNode;
		newNode.next = secondTemp;
	    }
	}
	count ++;
    }

    public boolean exists(T data){
	Node<T> temp = head;
	for (int i = 0; i < count; i++){
	    if (temp.data != null && data != null){
		if (temp.data.equals(data)){
		    return true;
		}
	    } else {
		if (data == null){
		    return true;
		}
	    }
	    temp = temp.next;
	}
	return false;
    }

    
    public boolean remove(T data)
    {
   
        if (head == null){
            return false;
	}
 
    
        Node temp = head;
	if (head.data.equals(data) && head.next == null){
	    head = tail = null;
	    count --;
	    return true;
	}
    
        if (head.data.equals(data)){
		head = temp.next;   
        	count --;
		return true;
	    }
	// find which thing removes tail and handle setting the other thing to tail.
        for (int i = 0; i < count; i++){
	    if (temp.next.data.equals(data) && temp.next.next != null){
		
		temp.next = temp.next.next;
		count --;
		return true;
	    } else if(temp.next != null && temp.next.data.equals(data)){
		if (temp.next == tail){
		    tail = temp;
		}
		temp.next = null;
		count --;
		return true;
	    }
	    temp = temp.next;
	}
	return false;
	
 
 
        
    }

    /*
    
    public boolean remove(T data){
	if (head.data.equals(data)){
	    head = head.next;
	    count --;
	    return true;
	}
	Node<T> temp = head;
	int counter = 0;
	for (int i = 0; i < count; i++){
	    if (temp.data != null && data != null){
		if (temp.data.equals(data)){
		    Node<T> secondTemp = head;
		    for (int j = 0; j < counter; j++){
			if (j+1 == counter){
			    secondTemp.next = temp.next;
			    count--;
			    return true;
			} else {
			    secondTemp = secondTemp.next;
			}
		    }
		}
	    } else if (temp.data == null && data == null) {
		Node<T> secondTemp = head;
		for (int j = 0; j < counter; j++){
		    if (j+1 == counter){
			secondTemp.next = temp.next;
			count--;
			return true;
		    } else {
			secondTemp = secondTemp.next;
		    }	
		}	
	    }
	    temp = temp.next;
	    counter ++;
	}
	
	return false;
    }
    */
    
    public T get(int index){
	if (index < 0 || index > count){
	    throw new IndexOutOfBoundsException("Index is out of bounds");
	}
	Node<T> temp = head;
	for (int i = 0; i <= index; i++){
	    if (i == index){
		return temp.data;
	    } else {
		temp = temp.next;
	    }
	}
	return null;
    }
    
   
 
    public void append(T i){
        if (count == 0){
            head = tail = new Node<T>(i);
        }
        else {
            tail = tail.next = new Node<T>(i);
        }
        count++;
    }
 
    public int size(){
        return count;
    }
 
    public boolean isEmpty(){
        return head == null;
    }
 
    public String toString() {
        String retVal = "Linked list with " + count + " elements\nNodes:";
        //Node temp = head;
        //while(temp != null){
        //    retVal += temp + " ";
        //    temp = temp.next;
        //}
 
        for (Node<T> temp = head; temp != null; temp = temp.next)
            retVal += temp + " ";
        
        return retVal;
    }
 
    public Iterator<T> iterator(){
        return new LLIterator<T>(this);
    }
}
