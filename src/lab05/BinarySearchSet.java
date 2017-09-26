

package lab05;

public class BinarySearchSet {
	
	public double[] storage;
	private int capacity;
	private int numItems;
	
	public BinarySearchSet(){ 
		
		numItems = 0;
		capacity = 6;
		storage = new double[capacity];
	}
	
	public boolean isEmpty() {
		
		boolean answer = false;
		if(numItems == 0) {
			answer = true;
		}
		return answer;
	}
	
	public int size() {
		return numItems;
	}
	
	public void grow() {
		
		double[] storage2 = new double[capacity*2];
		for (int i = 0; i < capacity; i++) {
			storage2[i] = storage[i];
		}
		storage = storage2;
		capacity = storage2.length;
	}
	
	public String toString(){
		String elementsOfMyList = "[";
		
		for (int i = 0; i < numItems; i++) {
			elementsOfMyList += storage[i] + (i == numItems-1 ? "" : ", ");
		}
		
		elementsOfMyList += "]";

		return elementsOfMyList + "\n" + "The capacity: " + capacity + ". \n" 
					+ "The number of items: " + numItems + ". \n"; 
	}
	
	public boolean remove(double value) {
		int index = findIndex(storage,value);
		if (numItems == 0 || index < 0) 
			return false;
		for (int i = index; i < numItems - 1; i++) {
			storage[i] = storage[i+1];
		}
		
		storage[(numItems--)-1] = 0.0;
		return true;
		
	}
	
	/*public boolean sequential_add(double newVal) {
		
		if(numItems == capacity){
			grow();
		}
		
		for (int i = 0; i < storage.length; i++) {
			if (newVal == storage[i]) {
				return false;
			}
		}
		numItems++;
		storage[numItems - 1] = newVal;
		sort(storage);
		return true;
	}
	*/
	
	public int findIndex(double[] storage, double val) {
		int index = -1;
		if(contains(val) == true) {
			
		for (int i = 0; i < storage.length; i++) {
			if (storage[i] == val) {
				return i;
			}
		}
		}
		return index;
	}
	public int binarySearch(double value) {
		int min = 0;
		int max = numItems - 1;
		
		while (max >= min) {
			int mid = (max + min) / 2;
			if (storage[mid] == value){
				return mid;
			} else if (storage[mid] < value) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		return ((-min)-1);
	}
	
	public boolean contains (double value) {
		return (binarySearch(value) >= 0);
	}
	
	public boolean containsAll(double[] elements) {
		
		boolean containit = true;
		for (int i = 0; i < elements.length; i++) {
			if (!contains(elements[i])) {
				containit = false;
			}
		}
		return containit;
	}
	
	public boolean binary_add(double newVal) {
		
		if (contains(newVal) == false) {
			int position = -(binarySearch(newVal) + 1);
			
			if(numItems == capacity){
				grow();
			}
			
			for (int i = numItems; i > position; i--) {
				storage[i] = storage[i - 1];
			}
			
			storage[position] = newVal;
			numItems++;
			
			return true;
		}
		return false;
	}
	
	public BinarySearchSet(double[] input) {
	
		numItems = 0;
		capacity = input.length;
		storage = new double[capacity];
		
		for (int i = 0; i < input.length; i++) {
			binary_add(input[i]);
		}
	}
	
	public void clear() {
		numItems = 0;
	}
}
