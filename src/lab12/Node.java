package lab12;

import java.util.ArrayList;

public class Node {
	
	char theData;
	boolean visited; //if it was visited, it won't be visited again
	Node cameFrom; //the parent of the node
	ArrayList<Node> neighbors;
	int row, column;
	
	public Node(char theData, int row, int column) {
		this.theData = theData;
		this.row = row;
		this.column = column;
	}
}
