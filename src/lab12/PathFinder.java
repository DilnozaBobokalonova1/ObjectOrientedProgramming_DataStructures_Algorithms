package lab12;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PathFinder {
	static Node start;
	static Node goal;
	public static void solveMaze(String inputFile, String outputFile) {
		int height = 0;
		int width = 0;
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(inputFile));
			String[] dimensions = input.readLine().split(" ");
			height = Integer.parseInt(dimensions[0]);
			width = Integer.parseInt(dimensions[1]);
			Node[][] myNodes = new Node[height][width];
			for(int i = 0; i < height; i++){
				String theRead = input.readLine();
				for(int j = 0; j < width; j++) {
					myNodes[i][j] = new Node(theRead.charAt(j), i, j);
					if(myNodes[i][j].theData == 'S') {
						start = myNodes[i][j];
					}
					if(myNodes[i][j].theData =='G') {
						goal = myNodes[i][j];
					}
				}
			}
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					Node node = myNodes[i][j];
					if(myNodes[i][j].theData != 'X'){
						node.neighbors = new ArrayList<Node>();
						if(j != 0 && myNodes[i][j-1].theData != 'X')
							node.neighbors.add(myNodes[i][j-1]);
						if(j != (width - 1) && myNodes[i][j+1].theData != 'X')
							node.neighbors.add(myNodes[i][j+1]);
						if(i != 0 && myNodes[i-1][j].theData != 'X')
							node.neighbors.add(myNodes[i-1][j]);
						if(i != (height -1) && myNodes[i+1][j].theData != 'X')
							node.neighbors.add(myNodes[i+1][j]);
					}
				}
			}
			Queue<Node> Q = new LinkedList<Node>();
			start.visited = true;
			Q.add(start);
			while(!Q.isEmpty()) {
				Node curr = Q.remove();
				if(curr.equals(goal)){
					Node ten = goal.cameFrom;
					while( ten.theData != 'S') {
						char new1 = '.';
						ten.theData = new1;
						ten = ten.cameFrom;
					}
					break;
				}
				for(Node next: curr.neighbors)
					
					if(!next.visited) {
						next.visited = true;
						next.cameFrom = curr;
						Q.add(next);
					}
			}
			try(PrintWriter output = new PrintWriter(new FileWriter(outputFile))){
				output.println(height + " " + width);
				for(int i = 0; i < height; i++) {
					for(int j = 0; j < width; j++) {
						output.print(myNodes[i][j].theData);
					}
					output.println();
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		solveMaze("tinyMaze.txt", "tinyMazeSol.txt");
		solveMaze("bigMaze.txt", "bigMaze1.txt");
		solveMaze("classic.txt", "classicSol.txt");
		solveMaze("demoMaze.txt", "demoMazeSol.txt");
		solveMaze("mediumMaze.txt", "mediumMazeSol.txt");
		solveMaze("randomMaze.txt", "randomMazeSol.txt");
		solveMaze("straight.txt", "straightSol.txt");
		solveMaze("tinyOpen.txt", "tinyOpenSol.txt");
		solveMaze("turn.txt", "turnSol.txt");
		solveMaze("unsolvable.txt", "unsolvableSol.txt");
	}
}
