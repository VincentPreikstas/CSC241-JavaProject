import java.util.*;

public class SLLDriver {
    public static void main(String[] args){
	singlyLinkedList<String> example = new singlyLinkedList<>();
	example.prepend("Test 1");
	example.append("Test 2");
	example.append("Apple");
	example.append("Pear");
	Iterator<String> it = example.iterator();

	String output = "";

	while(it.hasNext()){
	    output += it.next();
	}
	System.out.println(output);

	String test = example.get(2);
	System.out.println(example);

	example.remove("Apple");
	test = example.get(2);
	System.out.println(example);

	example.insert("Apple", 2);
	test = example.get(2);
	System.out.println(example);

	boolean aCheck = example.exists("Apple");
	System.out.println(aCheck);

	Iterator<String> second = example.iterator();
	String output2 = "";
	while(second.hasNext()){
	    output2 += second.next();
	}
	System.out.println(output2);
    }
    
}
