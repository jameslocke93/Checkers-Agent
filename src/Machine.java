import java.util.ArrayList;

public class Machine {
	
	private static final int BLANK = 0;
	private static final int WHITE_MEN = 1;
	private static final int WHITE_KING = 2;
	private static final int BLACK_MEN = 3;
	private static final int BLACK_KING = 4;

	private static final int TILE_LAYER = 0;
	private static final int COLOUR_LAYER = 1;
	private static final int KING_LAYER = 2;
	
	private static final int BLACK_PLAYER = 1;
	
	public Game game;
	public ArrayList<Move> moveList;
	public Node gameTree;
	public int[] intBoard;
	public boolean[][] boolBoard;
	
	boolean first = true;
	
	Machine(boolean[][] board){
		game = new Game();
		moveList = new ArrayList<Move>();
		gameTree = new Node();
		
		boolBoard = board;

		intBoard = convertBoardInt(board);
		
		game.setBoard(board);
	}
	
	public String convertBoardToString(boolean[][] board){
		String tileLayer = "";
		String colourLayer = "";
		String kingLayer = "";

		for (int i = 0; i < board[0].length; i++) {
			tileLayer = tileLayer + board[0][i];
		}

		for (int i = 0; i < board[1].length; i++) {
			colourLayer = colourLayer + board[1][i];
		}

		for (int i = 0; i < board[2].length; i++) {
			kingLayer = kingLayer + board[2][i];
		}

		return new String(tileLayer + colourLayer + kingLayer);
	}

	public boolean[][] convertBoardString(String board) {
		boolean[][] convertedBoard = new boolean[3][32];
		
		//Split the string inclusive of the e... 'true' 'false'
		String[] words = board.split("(?<=e)");

		for (int i = 0; i < 32; i++) {
			if (words[i].equals("true")) {
				convertedBoard[TILE_LAYER][i] = true;
			} else {
				convertedBoard[TILE_LAYER][i] = false;
			}
		}

		for (int i = 0; i < 32; i++) {
			int j = i + (COLOUR_LAYER * 32);
			if (words[j].equals("true")) {
				convertedBoard[COLOUR_LAYER][i] = true;
			} else {
				convertedBoard[COLOUR_LAYER][i] = false;
			}
		}

		for (int i = 0; i < 32; i++) {
			int j = i + (KING_LAYER * 32);
			if (words[j].equals("true")) {
				convertedBoard[KING_LAYER][i] = true;
			} else {
				convertedBoard[KING_LAYER][i] = false;
			}
		}

		return convertedBoard;
	}

	public int[] convertBoardInt(boolean[][] board) {

		int[] convertedBoard = new int[32];

		for (int i = 0; i < convertedBoard.length; i++) {
			boolean currentPiece = board[TILE_LAYER][i];
			boolean currentColour = board[COLOUR_LAYER][i];
			boolean currentKing = board[KING_LAYER][i];

			if (currentPiece == true) {
				if (currentColour == true) {
					if (currentKing == true) {
						convertedBoard[i] = BLACK_KING;
					} else {
						convertedBoard[i] = BLACK_MEN;
					}
				} else {
					if (currentKing == true) {
						convertedBoard[i] = WHITE_KING;
					} else {
						convertedBoard[i] = WHITE_MEN;
					}
				}
			} else {
				convertedBoard[i] = BLANK;
			}
		}

		return convertedBoard;
	}

	public boolean[][] convertBoardBool(int[] board){
		boolean[][] convertedBoard = new boolean[3][32];
		
		for (int i = 0; i < board.length; i++) {
			switch(board[i]){
			case BLANK:
				convertedBoard[TILE_LAYER][i] = false;
				convertedBoard[COLOUR_LAYER][i] = false;
				convertedBoard[KING_LAYER][i] = false;
				break;
			case WHITE_MEN:
				convertedBoard[TILE_LAYER][i] = true;
				convertedBoard[COLOUR_LAYER][i] = false;
				convertedBoard[KING_LAYER][i] = false;
				break;
			case WHITE_KING:
				convertedBoard[TILE_LAYER][i] = true;
				convertedBoard[COLOUR_LAYER][i] = false;
				convertedBoard[KING_LAYER][i] = true;
				break;
			case BLACK_MEN:
				convertedBoard[TILE_LAYER][i] = true;
				convertedBoard[COLOUR_LAYER][i] = true;
				convertedBoard[KING_LAYER][i] = false;
				break;
			case BLACK_KING:
				convertedBoard[TILE_LAYER][i] = true;
				convertedBoard[COLOUR_LAYER][i] = true;
				convertedBoard[KING_LAYER][i] = true;
				break;
			}
		}
		
		return convertedBoard;
	}
	
	public void legalMoves(int[] board) {
		
		if(first && game.getPlayer() == BLACK_PLAYER){
			first = false;
		}
		
		moveList.clear();
		
		for (int i = 0; i < board.length; i++) {

			int piece = board[i];

			if (game.getPlayer() == BLACK_PLAYER) {
				if (piece == BLACK_MEN || piece == BLACK_KING) {
					int row = (int) i / 4;
					int row_position = i % 4;

					if ((row & 1) == 0) {
						if (row_position == 3) {
							if (piece == BLACK_KING) {
								if (i - 4 >= 0 && board[i - 4] == BLANK) {
									moveList.add(new Move(i, i - 4));
								}
							}

							if (i + 4 < board.length && board[i + 4] == BLANK) {
								moveList.add(new Move(i, i + 4));
							}
						} else {
							if (piece == BLACK_KING) {
								if (i - 4 >= 0 && board[i - 4] == BLANK) {
									moveList.add(new Move(i, i - 4));
								}

								if (i - 3 >= 0 && board[i - 3] == BLANK) {
									moveList.add(new Move(i, i - 3));
								}
							}

							if (i + 4 < board.length && board[i + 4] == BLANK) {
								moveList.add(new Move(i, i + 4));
							}

							if (i + 5 < board.length && board[i + 5] == BLANK) {
								moveList.add(new Move(i, i + 5));
							}
						}
					} else {
						// odd row
						if (row_position == 0) {
							if (piece == BLACK_KING) {
								if (i - 4 >= 0 && board[i - 4] == BLANK) {
									moveList.add(new Move(i, i - 4));
								}
							}

							if (i + 4 < board.length && board[i + 4] == BLANK) {
								moveList.add(new Move(i, i + 4));
							}
						} else {
							if (piece == BLACK_KING) {
								if (i - 4 >= 0 && board[i - 4] == BLANK) {
									moveList.add(new Move(i, i - 4));
								}
								if (i - 5 >= 0 && board[i - 5] == BLANK) {
									moveList.add(new Move(i, i - 5));
								}
							}

							if (i + 4 < board.length && board[i + 4] == BLANK) {
								moveList.add(new Move(i, i + 4));
							}
							if (i + 3 < board.length && board[i + 3] == BLANK) {
								moveList.add(new Move(i, i + 3));
							}
						}
					}
				}
			} else {
				// White Player
				if (piece == WHITE_MEN || piece == WHITE_KING) {
					int row = (int) i / 4;
					int row_position = i % 4;

					if ((row & 1) == 0) {
						// Even row
						if (row_position == 3) {
							if (piece == WHITE_KING) {
								if (i + 4 < board.length && board[i + 4] == BLANK) {
									moveList.add(new Move(i, i + 4));
								}
							}

							if (i - 4 >= 0 && board[i - 4] == BLANK) {
								moveList.add(new Move(i, i - 4));
							}
						} else {
							if (piece == WHITE_KING) {
								if (i + 4 < board.length && board[i + 4] == BLANK) {
									moveList.add(new Move(i, i + 4));
								}
								if (i + 5 < board.length && board[i + 5] == BLANK) {
									moveList.add(new Move(i, i + 5));
								}
							}

							if (i - 4 >= 0 && board[i - 4] == BLANK) {
								moveList.add(new Move(i, i - 4));
							}

							if (i - 3 >= 0 && board[i - 3] == BLANK) {
								moveList.add(new Move(i, i - 3));
							}
						}
					} else {
						// odd
						if (row_position == 0) {
							if (piece == WHITE_KING) {
								if (i + 4 < board.length && board[i + 4] == BLANK) {
									moveList.add(new Move(i, i + 4));
								}
							}

							if (i - 4 >= 0 && board[i - 4] == BLANK) {
								moveList.add(new Move(i, i - 4));
							}
						} else {
							if (piece == WHITE_KING) {
								if (i + 4 < board.length && board[i + 4] == BLANK) {
									moveList.add(new Move(i, i + 4));
								}
								if (i + 3 < board.length && board[i + 3] == BLANK) {
									moveList.add(new Move(i, i + 3));
								}
							}

							if (i - 4 >= 0 && board[i - 4] == BLANK) {
								moveList.add(new Move(i, i - 4));
							}
							if (i - 5 >= 0 && board[i - 5] == BLANK) {
								moveList.add(new Move(i, i - 5));
							}
						}
					}
				}
			}
		}

	}

}
