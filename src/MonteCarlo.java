import java.util.ArrayList;
import java.util.Random;

public class MonteCarlo extends Machine {

	private String originalBoard;
	private int mainPlayer;
	private int regret;
	private int runs;

	MonteCarlo(boolean[][] board, int player, int regret, int runs) {

		super(board);
		
		originalBoard = convertBoardToString(board);
		
		mainPlayer = player;
		game.setPlayer(mainPlayer);
		this.regret = regret;
		this.runs = runs;

	}

	public Move generateMove() {
		
		for (int i = 0; i < runs; i++) {
			boolean running = true;
			boolean win = false;

			// Reset the board to its original state each run of Monte Carlo
			game.setBoard(convertBoardString(originalBoard));
			game.setPlayer(mainPlayer);

			while (running) {
				if (game.takeCheck() == false) {
					intBoard = convertBoardInt(game.getBoard());
					legalMoves(intBoard);

					if (gameTree.hasChild() == false) {

						if (moveList.size() == 0) {
							// Treat these as a loss
							running = false;
							break;
						}
						
						// Pick a legal movement randomly
						Random rand = new Random();

						int movementIndex = rand.nextInt(moveList.size());
						Move move = moveList.get(movementIndex);

						// Commit the movement
						game.moveCheck(move.getCurrent(), move.getDestination());
						
						gameTree.setChild(move);

						// Only ever be the one child here
						// Changes the current node to the child node
						gameTree = gameTree.getChild(0);
					} else {
						// check the what moves are stored within the children
						// nodes
						for (int index = 0; index < gameTree.numberOfChildren(); index++) {
							// remove the moves stored within legal moves that
							// match moves stored in the children
							for (int j = 0; j < moveList.size(); j++) {
								if (moveList.get(j).check(gameTree.getChild(index).getMove())) {
									moveList.remove(j);
								}
							}
						}

						if (moveList.isEmpty()) {
							double highestRatio = 0.0;
							ArrayList<Integer> children = new ArrayList<Integer>();

							// Find highest ratio(s) and create a list from
							// these children
							for (int index = 0; index < gameTree.numberOfChildren(); index++) {
								
								if (gameTree.getChild(index).getRatio() > highestRatio) {
									highestRatio = gameTree.getChild(index).getRatio();
									children.clear();
									children.add(new Integer(index));
								} else if (gameTree.getChild(index).getRatio() == highestRatio) {
									children.add(new Integer(index));
								}
							}
							
							//Allows for the exploration of under explored nodes
							for(int index = 0; index < children.size(); index++){
								for(int j = 0; j < gameTree.numberOfChildren(); j++){
									if((gameTree.getChild(j).getVisit() * regret) < gameTree.getChild(children.get(index)).getVisit()){
										children.clear();
										children.add(new Integer(j));
										break;
									}
								}
							}
							
							// Exploring the nodes with the highest ratio
							Random childRand = new Random();
							int childIndex = childRand.nextInt(children.size());
							Move move = gameTree.getChild(children.get(childIndex)).getMove();

							// Commit the movement
							game.moveCheck(move.getCurrent(), move.getDestination());

							gameTree = gameTree.getChild(children.get(childIndex));

						} else {
							// Pick a legal movement randomly
							Random rand = new Random();
							int movementIndex = rand.nextInt(moveList.size());
							Move move = moveList.get(movementIndex);

							// Commit the movement
							game.moveCheck(move.getCurrent(), move.getDestination());
							
							gameTree.setChild(move);

							// Move to child that you just created
							for (int index = 0; index < gameTree.numberOfChildren(); index++) {
								if (gameTree.getChild(index).getMove().check(move)) {
									gameTree = gameTree.getChild(index);
								}
							}
						}
					}
				} else {
					while (game.takeCheck()) {
					}
				}

				// Changes player and check if the goal state has been achieved
				if (game.winCheck()) {
					running = false;
					if (game.getPlayer() == mainPlayer) {
						win = true;
					}
					break;
				}
				game.kingCheck();
			}

			// Back props all the way to the node and increments the necessary
			// values
			while (gameTree.getParent() != null) {
				gameTree.incrimentVisit();
				if (win) {
					gameTree.incrimentWon();
				}
				gameTree = gameTree.getParent();
			}
		}

		// Finds the highest ratio of the children of the root node and returns
		// it
		double highestRatio = 0.0;
		int index = 0;

		for (int i = 0; i < gameTree.numberOfChildren(); i++) {
			/*System.out.println("Ratio: " + gameTree.getChild(i).getRatio() + " for child " + i); 
			System.out.println("Number of visits: " + gameTree.getChild(i).getVisit());*/
			if (gameTree.getChild(i).getRatio() > highestRatio) {
				highestRatio = gameTree.getChild(i).getRatio();
				index = i;
			}
		}
		
		/*System.out.println("Highest ratio is: " + gameTree.getChild(index).getRatio() + " with times visited: "
				+ gameTree.getChild(index).getVisit() + " and times won: " + gameTree.getChild(index).getWon());*/
		if(gameTree.hasChild() == false){
			return null;
		}
		return gameTree.getChild(index).getMove();
	}

}