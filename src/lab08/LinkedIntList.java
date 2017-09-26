package lab08;


// modified version of Chapter 16 LinkedIntList class (page 990)
public class LinkedIntList {
	public ListNode front; // first value in the list
	
	// post: constructs an empty list
	public LinkedIntList(){
		front = null;
	}
	
	public LinkedIntList(int[] arr){
		this();
		
		for (int i = 0; i < arr.length; i++)
			add(arr[i]);
	}
	
	// post: returns the current number of elements in the lists
	public int size(){
		int count = 0;
		ListNode current = front;
		while (current != null){
			current = current.next;
			count++;
		}
		return count;
	}
	
	// post: return comma-separated, bracketed version of the list
	public String toString(){
		if (front == null){
			return "[]";
		}else{
			String result = "[" + front.data;
			ListNode current = front.next;
			while (current != null){
				result += ", " + current.data;
				current = current.next;
			}
			result += "]";
			return result;
		}
	}
	
	// post: appends the given value to the end of the list
	public void add(int value){
		if (front == null){
			front = new ListNode(value);
		}else{
			ListNode current = front;
			while(current.next != null){
				current = current.next;
			}
			current.next = new ListNode(value);	
		}
	}
	
	
	// pre: 0 <= index < size()
	// post: returns a reference to the node at the given index
	private ListNode nodeAt(int index){
		ListNode current = front;
		for (int i = 0; i < index; i++){
			current = current.next;
		}
		return current;
	}
	
	// pre: 0 <= index <= size()
	// post: inserts the given value at the given index
	public void add(int index, int value){
		if (index == 0){
			front = new ListNode(value, front);
		}else if (index == size()){
			add(value);
		}else if (index > size()){
			return;
		}else{
			ListNode current = nodeAt(index-1);
			current.next = new ListNode(value, current.next);
		}
	}
	
	//*************************************************
	
	// This method accepts an integer value as a parameter and returns the index in the list of 
	// the last occurrence of that value, or -1 if the value is not found in the list.
	public int lastIndexOf(int value){
		ListNode current = front;
		int theOne = 0;
		int index = -1;
		while(current != null) {
			if(current.data == value) {
				index = theOne;
				theOne++;
			}
			else if(current.data != value) {
				theOne++;
			}
			current = current.next;
		}
		return index;
	}
	
	// This method should remove all occurrence of a particular value.
	public void removeAll(int value){
		
		ListNode current = front;
		
		while(front.data == value) {
			front = front.next;
		}
		
		if(front == null) {
			return;
		}	

		while(current.next != null) {
			while(current.next.data == value) {
				if(current.next.next != null) {
					current.next = current.next.next;
					
				}
				else {
					current.next = null;
					return;
				}
			}
			current = current.next;
		}
	}
		// @TA: There are many ways to do this. A cleaner way to do this is to write a helper function to find index first and then delete
		//* FILL IN
	
	// This method will rearrange the elements of a list of integers by moving to the end of the list all 
	// values that are in odd-numbered positions and otherwise preserving list order. Remember, it does not 
	// matter whether the value itself is odd or even; what matters is whether the value appears in an odd
	// index. Also, the original order of the elements of the list should otherwise be preserved. 
	// You may not construct any new nodes nor use any auxiliary data structures to solve this problem. 
	// You also may not change any data fields of the nodes; you must solve this problem by rearranging the 
	// links of the list.
	public void shift(){
		
 		ListNode current = front;
		int another = this.size()-1;
		
		if(front == null) {
			return;
		}
		if(this.size() == 2 || this.size() == 1) {
			return;
		}

			for (int i = 1; i <= Math.ceil(this.size()/2); i++) {
		
				current = this.nodeAt(another);
				current.next = this.nodeAt(i);
			
				current = this.nodeAt(i-1);
				current.next = this.nodeAt(i+1);
				
				current = this.nodeAt(another);
				current.next = null;
		}
	}
	
	// This method will double the size of the list by replacing every integer 
	// in the list with two of that integer.
	// if the list is empty simply return
	public void stutter(){
		
		if(front == null) {
			return;
		}
		ListNode current = front;
		while(current != null) {
			ListNode copyOfCurrent = new ListNode(current.data);
			copyOfCurrent.next = current.next;
			current.next = copyOfCurrent;
			current = copyOfCurrent.next;
		}
	}
}