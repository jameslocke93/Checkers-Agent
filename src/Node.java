import java.util.ArrayList;

public class Node {

	private Move move;
	private int timesVisited, timesWon;
	private Node parent;
	private ArrayList<Node> child;
	private int score, depth;
	private boolean visited, minimax;

	// Root node
	Node() {
		parent = null;
		child = new ArrayList<Node>();

		timesVisited = timesWon = 0;
	}

	// Every other node
	Node(Node parent, Move move) {
		this.parent = parent;
		child = new ArrayList<Node>();

		timesVisited = 0;
		timesWon = 0;
		visited = false;
		minimax = false;

		this.move = move;
	}
	
	//MiniMax nodes
	Node(Node parent, Move move, int score){
		this.parent = parent;
		child = new ArrayList<Node>();

		timesVisited = 0;
		timesWon = 0;
		visited = false;
		minimax = false;
		
		this.score = score;

		this.move = move;
	}

	public boolean hasChild() {
		if (child == null || child.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public double getRatio() {
		return (double) timesWon / timesVisited;
	}

	public Move getMove() {
		return move;
	}

	public void setChild(Move move) {
		child.add(new Node(this, move));
	}
	
	public void setChild(Move move, int score) {
		child.add(new Node(this, move, score));
	}

	public void incrimentVisit() {
		timesVisited++;
	}

	public void incrimentWon() {
		timesWon++;
	}

	public int getVisit() {
		return timesVisited;
	}

	public int getWon() {
		return timesWon;
	}

	public int getScore() {
		return score;
	}

	public int getDepth() {

		Node node = this;
		depth = 0;
		
		while (node.getParent() != null) {
			depth++;
			node = node.getParent();
		}
		return depth;
	}

	public Node getChild(int index) {
		if (child != null && child.size() >= index) {
			return child.get(index);
		} else {
			return null;
		}
	}

	public Node getParent() {
		if (parent != null) {
			return parent;
		} else {
			return null;
		}
	}

	public int numberOfChildren() {
		return child.size();
	}

	public void addScore(int currentScore) {
		score += currentScore;
	}
	
	public void setScore(int currentScore) {
		score = currentScore;
	}
	
	public boolean getMiniMax(){return minimax;}
	public void visitMiniMax(){ minimax = true;}
	
	public ArrayList<Integer> getUnvisitedMiniMax(){
		ArrayList<Integer> childrenIndex = new ArrayList<Integer>();
		
		if(hasChild()){
			for(int i = 0; i < numberOfChildren(); i++){
				if(getChild(i).getMiniMax() == false){
					childrenIndex.add(i);
				}
			}
		}
		
		return childrenIndex;
	}
	
	
	public boolean getVisited(){return visited;}
	public void visit(){ visited = true;}
	
	public ArrayList<Integer> getUnvisited(){
		ArrayList<Integer> childrenIndex = new ArrayList<Integer>();
		
		if(hasChild()){
			for(int i = 0; i < numberOfChildren(); i++){
				if(getChild(i).getVisited() == false){
					childrenIndex.add(i);
				}
			}
		}
		
		return childrenIndex;
	}
}