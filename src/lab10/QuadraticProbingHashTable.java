package lab10;


public class QuadraticProbingHashTable
{
	
    public HashEntry [ ] HashTable;   // The array that holds the hash table
    public int currentSize;       // The number of occupied cells	

    public QuadraticProbingHashTable( int size ) {
    	
    	
    	HashTable = new HashEntry[size];
    	currentSize = 0;
    	for(int i = 0; i < size; i++) {
    		HashTable[i] = null;
    	}
    }

    public void insert(int x) {
    	double theone = currentSize+1;
    	double load = loadFactor(theone);
    	if(load >= 0.75) {
    		rehash();
    	}
   
    	
    	int thePos = findPosition(x);
    	if (HashTable[thePos] != null && HashTable[thePos].element == x && HashTable[thePos].isActive)
    		return;
    	HashTable[thePos] = new HashEntry(x);

    	currentSize++;
    }
    public int findPosition(int x) {
    	int start = 0;
    	int curPos = hash(x, HashTable.length);
    	int initialPos = curPos;
    	while(HashTable[curPos] != null && HashTable[curPos].isActive && HashTable[curPos].element != x) {
    		curPos = (initialPos + start * start++) % HashTable.length;
    		
    	}
    	return curPos;
    }

    public void rehash( ) {
    	HashEntry[] old = new HashEntry[HashTable.length];
    	old = HashTable;
    	//int theOne = currentSize;
    	HashTable = new HashEntry[old.length * 2];
    	currentSize = 0;
    	for(int i = 0; i < old.length; i++){
    		if(old[i] != null && old[i].isActive)
    			insert(old[i].element);
    			
  		
    	}
    	
    }
    public double loadFactor(double numElements) {
    	
    	double theResult = ((double)numElements / (double) HashTable.length);
    	return theResult;
    }
    
    public int hash(int value, int tableSize) {
    	int theOne = 0;
    	if(value < 0) {
    		theOne = -(value % tableSize);
    	}
    	else {
    		theOne = value % tableSize;
    	}
    	value = theOne;
    	return value;
    }  
    
    public void remove( int x ) {
    	int one = find(x);
    	if(one == -1)
    		return;
    	
    	HashTable[one].isActive = false;
    	currentSize--;
    }

    public int find( int x )
    {
    	int currPos = findPosition(x);
    	if(HashTable[currPos] != null && HashTable[currPos].isActive) {
    		return currPos;
    	}
    	else {
    		return -1;
    	}
    }

    public String toString(){
    	String toReturn = "";
    	for (int i = 0; i < HashTable.length; i++){
    		if (HashTable[i] == null){
    				toReturn += ("eF ");
    		}else{
    			if (HashTable[i].isActive)
    				toReturn += (HashTable[i].element + "T ");
    			else
    				toReturn += (HashTable[i].element + "F ");
    		}
    	}
    	return toReturn;
    }
    
  public static void main(String[] args){
    	
    	
    	// ********************* TESTS FOR LAB ****************************//
    	
    	QuadraticProbingHashTable h1 = new QuadraticProbingHashTable(10);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF eF eF eF eF "))
    		System.err.print("TEST FAILED: constructor ( 0 )");
    	    	
    	h1.insert(89);
    	h1.insert(58);
    	h1.insert(6);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF 6T eF 58T 89T "))
    		System.err.println("TEST FAILED: insert ( 1 )");
    	    	
    	h1.insert(16);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF 6T 16T 58T 89T "))
    			System.err.println("TEST FAILED: insert ( 2 )");
    	
    	h1.insert(9);
    	if (!h1.toString().equals("9T eF eF eF eF eF 6T 16T 58T 89T "))
			System.err.println("TEST FAILED: insert ( 3 )");   
    	
    	QuadraticProbingHashTable h2 = new QuadraticProbingHashTable(7);
    	
    	h2.insert(0);
    	h2.insert(1);
    	h2.insert(2);
    	h2.insert(3);
    	h2.insert(4);
    	h2.insert(5);
    	
    	if (!h2.toString().equals("0T 1T 2T 3T 4T 5T eF eF eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 4 )"); 
    	
    	System.out.println("Lab Testing Done!!!");
    	
    	
    	// ********************* TESTS FOR ASSIGNMENT ****************************//
    	
    	QuadraticProbingHashTable h3 = new QuadraticProbingHashTable(11);
    	
    	if (!h3.toString().equals("eF eF eF eF eF eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 5 )");    	    	
    	
    	h3.insert(44);    	
    	h3.insert(4);
    	h3.remove(44);
    	
    	if (!h3.toString().equals("44F eF eF eF 4T eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: remove ( 6 )");    	    	
    	
    	h3.insert(77);
    	
    	if (!h3.toString().equals("77T eF eF eF 4T eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 7 )");    	    	    	
    	
    	h3.insert(16);    	
    	h3.insert(28);
    	h3.insert(21);    	
    	h3.insert(11);    	
    	h3.insert(22);
    	h3.insert(33);  
    	
    	if (!h3.toString().equals("77T 11T eF 33T 4T 16T 28T eF eF 22T 21T "))
			System.err.println("TEST FAILED: insert ( 8 )");    	    	

    	h3.insert(55);
    	
    	if (!h3.toString().equals("22T eF eF eF 4T eF 28T eF eF eF eF 77T 11T eF eF 33T 16T eF eF eF 55T 21T "))
			System.err.println("TEST FAILED: insert ( 9 )");    	    	    	
    	
    	if (h3.find(4) != 4)
    		System.err.print("TEST FAILED: find ( 10 )");
    	
    	if (h3.find(44) != -1)
    		System.err.print("TEST FAILED: find ( 11 )");
    	
    	if (h3.find(77) != 11)
    		System.err.print("TEST FAILED: find ( 12 )");    
    	
    	System.out.println("Assignment Testing Done!!!");
    	
    }

}