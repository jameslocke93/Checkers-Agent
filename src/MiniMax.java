import java.util.ArrayList;

public class MiniMax extends Machine {

	private int mainPlayer;
	private String originalBoard;

	private static final int KING_SCORE = 2;
	private static final int TAKE_SCORE = 5;
	private static final int WIN_SCORE = 100;
	
	private static final int GOAL_DEPTH = 7;

	MiniMax(boolean[][] board, int player) {
		super(board);
		mainPlayer = player;
		
		gameTree.setScore(0);
		
		game.setPlayer(mainPlayer);

		originalBoard = convertBoardToString(board);
	}

	public Move selectMove() {
		
		boolean running = true;
		ArrayList<Integer> unvisitedNodes = new ArrayList<Integer>();
		
		while(running){
			
			if(gameTree.hasChild()){
				unvisitedNodes = gameTree.getUnvisitedMiniMax();
				if(unvisitedNodes.isEmpty() == false){
					gameTree = gameTree.getChild(unvisitedNodes.get(0));
				} else {
					if (gameTree.getDepth() == 0) {
						// At root and each child has been visited
						running = false;
						break;
					} else {
						gameTree.visitMiniMax();
						while (gameTree.getParent() != null) {
							// Go back to root
							gameTree = gameTree.getParent();
							
							if((gameTree.getDepth() & 1) == 0){
								//MAX
								gameTree.setScore(gameTree.getChild(0).getScore());
								for(int i = 0; i < gameTree.numberOfChildren(); i++){
									if(gameTree.getChild(i).getScore() > gameTree.getScore()){
										gameTree.setScore(gameTree.getChild(i).getScore());
									}
								}
							} else {
								//MIN
								gameTree.setScore(gameTree.getChild(0).getScore());
								for(int i = 0; i < gameTree.numberOfChildren(); i++){
									if(gameTree.getChild(i).getScore() < gameTree.getScore()){
										gameTree.setScore(gameTree.getChild(i).getScore());
									}
								}
							}
						}
					}
				}
			} else {
				//Leaf node
				gameTree.visitMiniMax();
				
				while (gameTree.getParent() != null) {
					// Go back to root
					gameTree = gameTree.getParent();
					
					if((gameTree.getDepth() & 1) == 0){
						int max = gameTree.getScore();
						for(int i = 0; i < gameTree.numberOfChildren(); i++){
							if(gameTree.getChild(i).getScore() > max){
								max = gameTree.getChild(i).getScore();
								gameTree.setScore(max);
							}
						}
					} else {
						int min = gameTree.getScore();
						for(int i = 0; i < gameTree.numberOfChildren(); i++){
							if(gameTree.getChild(i).getScore() < min){
								
								min = gameTree.getChild(i).getScore();
								gameTree.setScore(min);
							}
						}
					}
				}
			}
		}
		
		int highestScore = Integer.MIN_VALUE;
		int index = 0;
		
		for(int i = 0; i < gameTree.numberOfChildren(); i++){
			if(gameTree.getChild(i).getScore() > highestScore){
				index = i;
				highestScore = gameTree.getChild(i).getScore();
			}
		}
		
		return new Move(gameTree.getChild(index).getMove().getCurrent(), gameTree.getChild(index).getMove().getDestination());
	}

	public void generateTree() {
		
		boolean running = true;
		ArrayList<Integer> unvisitedNodes = new ArrayList<Integer>();

		while (running) {
			intBoard = convertBoardInt(game.getBoard());
			legalMoves(intBoard);
			for (int i = 0; i < moveList.size(); i++) {
				gameTree.setChild(moveList.get(i), gameTree.getScore());
			}

			gameTree.visit();

			while (gameTree.getParent() != null) {
				// Go back to root
				gameTree = gameTree.getParent();
			}
			game.setBoard(convertBoardString(originalBoard));
			game.setPlayer(mainPlayer);

			while (gameTree.getDepth() < GOAL_DEPTH) {
				if (gameTree.hasChild()) {
					if (gameTree.getUnvisited().isEmpty()) {
						if (gameTree.getDepth() == 0) {
							// At root and each child has been visited
							running = false;
							break;
						} else {
							gameTree.visit();
							
							while (gameTree.getParent() != null) {
								// Go back to root
								gameTree = gameTree.getParent();
							}
							game.setBoard(convertBoardString(originalBoard));
							game.setPlayer(mainPlayer);
						}
					} else {
						unvisitedNodes = gameTree.getUnvisited();
						gameTree = gameTree.getChild(unvisitedNodes.get(0));
						game.moveCheck(gameTree.getMove().getCurrent(), gameTree.getMove().getDestination());
						
						if (game.takeCheck()) {
							// Check for multiple takes
							while (game.takeCheck()) {
								if (game.getPlayer() == mainPlayer) {
									gameTree.addScore(TAKE_SCORE);
								} else {
									gameTree.addScore(-TAKE_SCORE);
								}
							}
							if (game.getPlayer() == mainPlayer) {
								gameTree.addScore(TAKE_SCORE);
							} else {
								gameTree.addScore(-TAKE_SCORE);
							}
						}
						if (game.winCheck()) {
							if (game.getPlayer() == mainPlayer) {
								gameTree.addScore(WIN_SCORE);
							} else {
								gameTree.addScore(-WIN_SCORE);
							}

							gameTree.visit();
							while (gameTree.getParent() != null) {
								// Go back to root
								gameTree = gameTree.getParent();
							}

							game.setBoard(convertBoardString(originalBoard));
							game.setPlayer(mainPlayer);
						}
						if (game.kingCheck()) {
							if (game.getPlayer() == mainPlayer) {
								gameTree.addScore(KING_SCORE);
							} else {
								gameTree.addScore(-KING_SCORE);
							}
						}
					}
				} else {
					
					intBoard = convertBoardInt(game.getBoard());
					legalMoves(intBoard);
					if(moveList.isEmpty()){
						gameTree.visit();
						
						while (gameTree.getParent() != null) {
							// Go back to root
							gameTree = gameTree.getParent();
						}

						game.setBoard(convertBoardString(originalBoard));
						game.setPlayer(mainPlayer);
						
					} else {
						for (int i = 0; i < moveList.size(); i++) {
							gameTree.setChild(moveList.get(i), gameTree.getScore());
						}
					}
				}
			}
		}
	}
}