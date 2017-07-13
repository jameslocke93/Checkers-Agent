
public class Game {

	private boolean[][] board;
	private int player;
	private int turncounter;

	private static final int BLACK_PLAYER = 1;
	private static final int WHITE_PLAYER = 2;
	private static final int TILE_LAYER = 0;
	private static final int COLOUR_LAYER = 1;
	private static final int KING_LAYER = 2;

	public Game() {
		resetBoard();
	}

	public boolean takeCheck() {

		for (int i = 0; i < 32; i++) {
			if (player == BLACK_PLAYER) {
				if (board[TILE_LAYER][i] == true) {
					if (board[COLOUR_LAYER][i] == true) {
						// What row it is on
						int row = (int) i / 4;
						if ((row & 1) == 0) {
							// Even
							int row_position = i % 4;
							if (row_position == 3) {
								if (board[KING_LAYER][i] == true) {
									// King only moves
									if ((i - 9 >= 0 && board[TILE_LAYER][i - 9] == false)
											&& (i - 4 >= 0 && board[TILE_LAYER][i - 4] == true)) {
										if (board[COLOUR_LAYER][i - 4] == false) {
											take(i, i - 9, i - 4);
											return true;
										}
									}
								}
								// Legal move
								if ((i + 7 < 32 && board[TILE_LAYER][i + 7] == false)
										&& (i + 4 < 32 && board[TILE_LAYER][i + 4] == true)) {
									if (board[COLOUR_LAYER][i + 4] == false) {
										take(i, i + 7, i + 4);
										return true;
									}
								}
							} else if (row_position == 0) {

								if (board[KING_LAYER][i] == true) {
									if ((i - 7 >= 0 && board[TILE_LAYER][i - 7] == false)
											&& (i - 3 >= 0 && board[TILE_LAYER][i - 3] == true)) {
										if (board[COLOUR_LAYER][i - 3] == false) {
											take(i, i - 7, i - 3);
											return true;
										}
									}
								}

								if ((i + 9 < 32 && board[TILE_LAYER][i + 9] == false)
										&& (i + 5 < 32 && board[TILE_LAYER][i + 5] == true)) {
									if (board[COLOUR_LAYER][i + 5] == false) {
										take(i, i + 9, i + 5);
										return true;
									}
								}

							} else {
								if (board[KING_LAYER][i] == true) {
									if ((i - 9 >= 0 && board[TILE_LAYER][i - 9] == false)
											&& (i - 4 >= 0 && board[TILE_LAYER][i - 4] == true)) {
										if (board[COLOUR_LAYER][i - 4] == false) {
											take(i, i - 9, i - 4);
											return true;
										}
									}
									if ((i - 7 >= 0 && board[TILE_LAYER][i - 7] == false)
											&& (i - 3 >= 0 && board[TILE_LAYER][i - 3] == true)) {
										if (board[COLOUR_LAYER][i - 3] == false) {
											take(i, i - 7, i - 3);
											return true;
										}
									}
								}
								// Legal Move
								if ((i + 7 < 32 && board[TILE_LAYER][i + 7] == false)
										&& (i + 4 < 32 && board[TILE_LAYER][i + 4] == true)) {
									if (board[COLOUR_LAYER][i + 4] == false) {
										take(i, i + 7, i + 4);
										return true;
									}
								}
								if ((i + 9 < 32 && board[TILE_LAYER][i + 9] == false)
										&& (i + 5 < 32 && board[TILE_LAYER][i + 5] == true)) {
									if (board[COLOUR_LAYER][i + 5] == false) {
										take(i, i + 9, i + 5);
										return true;
									}
								}
							}
						} else {
							// Odd
							int row_position = i % 4;
							if (row_position == 0) {
								if (board[KING_LAYER][i] == true) {
									// King only moves
									if ((i - 7 >= 0 && board[TILE_LAYER][i - 7] == false)
											&& (i - 4 >= 0 && board[TILE_LAYER][i - 4] == true)) {
										if (board[COLOUR_LAYER][i - 4] == false) {
											take(i, i - 7, i - 4);
											return true;
										}
									}
								}
								// Legal move
								if ((i + 9 < 32 && board[TILE_LAYER][i + 9] == false)
										&& (i + 4 < 32 && board[TILE_LAYER][i + 4] == true)) {
									if (board[COLOUR_LAYER][i + 4] == false) {
										take(i, i + 9, i + 4);
										return true;
									}
								}
							} else if (row_position == 3) {
								if (board[KING_LAYER][i] == true) {
									if ((i - 9 >= 0 && board[TILE_LAYER][i - 9] == false)
											&& (i - 5 >= 0 && board[TILE_LAYER][i - 5] == true)) {
										if (board[COLOUR_LAYER][i - 5] == false) {
											take(i, i - 9, i - 5);
											return true;
										}
									}
								}

								if ((i + 7 < 32 && board[TILE_LAYER][i + 7] == false)
										&& (i + 3 < 32 && board[TILE_LAYER][i + 3] == true)) {
									if (board[COLOUR_LAYER][i + 3] == false) {
										take(i, i + 7, i + 3);
										return true;
									}
								}
							} else {
								if (board[KING_LAYER][i] == true) {
									// King only moves
									if ((i - 9 >= 0 && board[TILE_LAYER][i - 9] == false)
											&& (i - 5 >= 0 && board[TILE_LAYER][i - 5] == true)) {
										if (board[COLOUR_LAYER][i - 5] == false) {
											take(i, i - 9, i - 5);
											return true;
										}
									}
									if ((i - 7 >= 0 && board[TILE_LAYER][i - 7] == false)
											&& (i - 4 >= 0 && board[TILE_LAYER][i - 5] == true)) {
										if (board[COLOUR_LAYER][i - 4] == false) {
											take(i, i - 7, i - 4);
											return true;
										}
									}
								}
								// Legal Move
								if ((i + 7 < 32 && board[TILE_LAYER][i + 7] == false)
										&& (i + 3 < 32 && board[TILE_LAYER][i + 3] == true)) {
									if (board[COLOUR_LAYER][i + 3] == false) {
										take(i, i + 7, i + 3);
										return true;
									}
								}
								if ((i + 9 < 32 && board[TILE_LAYER][i + 9] == false)
										&& (i + 4 < 32 && board[TILE_LAYER][i + 4] == true)) {
									if (board[COLOUR_LAYER][i + 4] == false) {
										take(i, i + 9, i + 4);
										return true;
									}
								}
							}
						}
					}
				}
			} else {
				// White Player
				if (board[TILE_LAYER][i] == true) {
					if (board[COLOUR_LAYER][i] == false) {
						// What row it is on
						int row = (int) i / 4;
						if ((row & 1) == 0) {
							// Even
							int row_position = i % 4;
							if (row_position == 3) {
								if (board[KING_LAYER][i] == true) {
									// King only moves
									if ((i + 7 < 32 && board[TILE_LAYER][i + 7] == false)
											&& (i + 4 < 32 && board[TILE_LAYER][i + 4] == true)) {
										if (board[COLOUR_LAYER][i + 4] == true) {
											take(i, i + 7, i + 4);
											return true;
										}
									}
								}
								if ((i - 9 >= 0 && board[TILE_LAYER][i - 9] == false)
										&& (i - 4 >= 0 && board[TILE_LAYER][i - 4] == true)) {
									if (board[COLOUR_LAYER][i - 4] == true) {
										take(i, i - 9, i - 4);
										return true;
									}
								}
							} else if (row_position == 0) {
								if (board[KING_LAYER][i] == true) {
									if ((i + 9 < 32 && board[TILE_LAYER][i + 9] == false)
											&& (i + 5 < 32 && board[TILE_LAYER][i + 5] == true)) {
										if (board[COLOUR_LAYER][i + 5] == true) {
											take(i, i + 9, i + 5);
											return true;
										}
									}
								}

								if ((i - 7 >= 0 && board[TILE_LAYER][i - 7] == false)
										&& (i - 3 >= 0 && board[TILE_LAYER][i - 3] == true)) {
									if (board[COLOUR_LAYER][i - 3] == true) {
										take(i, i - 7, i - 3);
										return true;
									}
								}

							} else {
								if (board[KING_LAYER][i] == true) {
									if ((i + 7 < 32 && board[TILE_LAYER][i + 7] == false)
											&& (i + 4 < 32 && board[TILE_LAYER][i + 4] == true)) {
										if (board[COLOUR_LAYER][i + 4] == true) {
											take(i, i + 7, i + 4);
											return true;
										}
									}
									if ((i + 9 < 32 && board[TILE_LAYER][i + 9] == false)
											&& (i + 5 < 32 && board[TILE_LAYER][i + 5] == true)) {
										if (board[COLOUR_LAYER][i + 5] == true) {
											take(i, i + 9, i + 5);
											return true;
										}
									}
								}
								if ((i - 7 >= 0 && board[TILE_LAYER][i - 7] == false)
										&& (i - 3 >= 0 && board[TILE_LAYER][i - 3] == true)) {
									if (board[COLOUR_LAYER][i - 3] == true) {
										take(i, i - 7, i - 3);
										return true;
									}
								}
								if ((i - 9 >= 0 && board[TILE_LAYER][i - 9] == false)
										&& (i - 4 >= 0 && board[TILE_LAYER][i - 4] == true)) {
									if (board[COLOUR_LAYER][i - 4] == true) {
										take(i, i - 9, i - 4);
										return true;
									}
								}
							}
						} else {
							// Odd
							int row_position = i % 4;
							if (row_position == 0) {
								if (board[KING_LAYER][i] == true) {
									if ((i + 9 < board.length && board[TILE_LAYER][i + 9] == false)
											&& (i + 4 < 32 && board[TILE_LAYER][i + 4] == true)) {
										if (board[COLOUR_LAYER][i + 4] == true) {
											take(i, i + 9, i + 4);
											return true;
										}
									}
								}
								if ((i - 7 >= 0 && board[TILE_LAYER][i - 7] == false)
										&& (i - 3 >= 0 && board[TILE_LAYER][i - 3] == true)) {
									if (board[COLOUR_LAYER][i - 3] == true) {
										take(i, i - 7, i - 3);
										return true;
									}
								}
							} else if (row_position == 3) {
								if (board[KING_LAYER][i] == true) {
									if ((i + 7 < board.length && board[TILE_LAYER][i + 7] == false)
											&& (i + 3 < 32 && board[TILE_LAYER][i + 3] == true)) {
										if (board[COLOUR_LAYER][i + 3] == true) {
											take(i, i + 7, i + 3);
											return true;
										}
									}
								}

								if ((i - 9 >= 0 && board[TILE_LAYER][i - 9] == false)
										&& (i - 5 >= 0 && board[TILE_LAYER][i - 5] == true)) {
									if (board[COLOUR_LAYER][i - 5] == true) {
										take(i, i - 9, i - 5);
										return true;
									}
								}

							} else {
								if (board[KING_LAYER][i] == true) {
									// King only moves
									if ((i + 7 < board.length && board[TILE_LAYER][i + 7] == false)
											&& (i + 3 < 32 && board[TILE_LAYER][i + 3] == true)) {
										if (board[COLOUR_LAYER][i + 3] == true) {
											take(i, i + 7, i + 3);
											return true;
										}
									}
									if ((i + 9 < board.length && board[TILE_LAYER][i + 9] == false)
											&& (i + 4 < 32 && board[TILE_LAYER][i + 4] == true)) {
										if (board[COLOUR_LAYER][i + 4] == true) {
											take(i, i + 9, i + 4);
											return true;
										}
									}
								}
								if ((i - 7 >= 0 && board[TILE_LAYER][i - 7] == false)
										&& (i - 4 >= 0 && board[TILE_LAYER][i - 4] == true)) {
									if (board[COLOUR_LAYER][i - 4] == true) {
										take(i, i - 7, i - 4);
										return true;
									}
								}
								if ((i - 9 >= 0 && board[TILE_LAYER][i - 9] == false)
										&& (i - 5 >= 0 && board[TILE_LAYER][i - 5] == true)) {
									if (board[COLOUR_LAYER][i - 5] == true) {
										take(i, i - 9, i - 5);
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private void move(int initPos, int desPos) {

		// set destination position to initial position
		board[TILE_LAYER][desPos] = board[TILE_LAYER][initPos];
		board[COLOUR_LAYER][desPos] = board[COLOUR_LAYER][initPos];
		board[KING_LAYER][desPos] = board[KING_LAYER][initPos];

		// make initial position empty
		board[TILE_LAYER][initPos] = false;
		board[COLOUR_LAYER][initPos] = false;
		board[KING_LAYER][initPos] = false;

		turncounter++;
	}

	private void take(int initPos, int desPos, int takenPos) {
		// set destination position to initial position
		board[TILE_LAYER][desPos] = board[TILE_LAYER][initPos];
		board[COLOUR_LAYER][desPos] = board[COLOUR_LAYER][initPos];
		board[KING_LAYER][desPos] = board[KING_LAYER][initPos];

		// make initial position empty
		board[TILE_LAYER][initPos] = false;
		board[COLOUR_LAYER][initPos] = false;
		board[KING_LAYER][initPos] = false;

		// Make taken position empty
		board[TILE_LAYER][takenPos] = false;
		board[COLOUR_LAYER][takenPos] = false;
		board[KING_LAYER][takenPos] = false;

		turncounter++;
	}

	public boolean moveCheck(int initPos, int desPos) {

		boolean isKing = false;

		// Check if initPos is out of bounds
		if (initPos >= 32 || initPos < 0) {
			return false;
		}
		// Check if piece is a king
		if (board[KING_LAYER][initPos] == true) {
			isKing = true;
		}
		// Check if the move itself is valid given player
		if (player == BLACK_PLAYER) {
			// Check if there is a piece at initPos
			if (board[TILE_LAYER][initPos] == true) {
				if (board[COLOUR_LAYER][initPos] == true) {
					// Check if destination piece is empty
					if (board[TILE_LAYER][desPos] == false) {
						int row = (int) initPos / 4;
						if ((row & 1) == 0) {

							int row_position = initPos % 4;
							if (row_position == 3) {
								if (isKing == true && desPos == initPos - 4) {
									move(initPos, desPos);
									return true;
								}
								if (desPos == initPos + 4) {
									// If all are okay then commit move
									move(initPos, desPos);
									return true;
								}
							} else {
								if (isKing == true && desPos == initPos - 4) {
									move(initPos, desPos);
									return true;
								}

								if (isKing == true && desPos == initPos - 3) {
									move(initPos, desPos);
									return true;
								}
								if (desPos == initPos + 4) {
									move(initPos, desPos);
									return true;
								}
								if (desPos == initPos + 5) {
									move(initPos, desPos);
									return true;
								}
							}
						} else {
							// odd
							int row_position = initPos % 4;
							if (row_position == 0) {
								if (isKing == true && desPos == initPos - 4) {
									move(initPos, desPos);
									return true;
								}
								if (desPos == initPos + 4) {
									move(initPos, desPos);
									return true;
								}
							} else {
								if (isKing == true && desPos == initPos - 4) {
									move(initPos, desPos);
									return true;
								}
								if (isKing == true && desPos == initPos - 5) {
									move(initPos, desPos);
									return true;
								}
								if (desPos == initPos + 4) {
									move(initPos, desPos);
									return true;
								}
								if (desPos == initPos + 3) {
									move(initPos, desPos);
									return true;
								}
							}
						}
					}

				}
			}
		} else {
			// White Player
			if (board[TILE_LAYER][initPos] == true) {
				if (board[COLOUR_LAYER][initPos] == false) {
					if (board[TILE_LAYER][desPos] == false) {
						int row = (int) initPos / 4;
						if ((row & 1) == 0) {
							int row_position = initPos % 4;
							if (row_position == 3) {
								if (isKing == true && desPos == initPos + 4) {
									move(initPos, desPos);
									return true;
								}
								if (desPos == initPos - 4) {
									move(initPos, desPos);
									return true;
								}
							} else {
								if (isKing == true && desPos == initPos + 4) {
									move(initPos, desPos);
									return true;
								}
								if (isKing == true && desPos == initPos + 5) {
									move(initPos, desPos);
									return true;
								}
								if (desPos == initPos - 4) {
									move(initPos, desPos);
									return true;
								}
								if (desPos == initPos - 3) {
									move(initPos, desPos);
									return true;
								}
							}
						} else {
							// odd
							int row_position = initPos % 4;
							if (row_position == 0) {
								if (isKing == true && desPos == initPos + 4) {
									move(initPos, desPos);
									return true;
								}
								if (desPos == initPos - 4) {
									move(initPos, desPos);
									return true;
								}
							} else {
								if (isKing == true && desPos == initPos + 4) {
									move(initPos, desPos);
									return true;
								}
								if (isKing == true && desPos == initPos + 3) {
									move(initPos, desPos);
									return true;
								}
								if (desPos == initPos - 4) {
									move(initPos, desPos);
									return true;
								}
								if (desPos == initPos - 5) {
									move(initPos, desPos);
									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;
	}

	public boolean kingCheck() {

		int tile = 0;

		if (player == BLACK_PLAYER) {
			for (int i = 0; i < 4; i++) {
				tile = 28 + i;
				if (board[TILE_LAYER][tile] == true && board[COLOUR_LAYER][tile] == true
						&& board[KING_LAYER][tile] == false) {
					board[KING_LAYER][tile] = true;
					return true;
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (board[TILE_LAYER][i] == true && board[COLOUR_LAYER][i] == false && board[KING_LAYER][i] == false) {
					board[KING_LAYER][i] = true;
					return true;
				}
			}
		}

		return false;
	}

	public boolean winCheck() {
		if (player == BLACK_PLAYER) {
			for (int i = 0; i < 32; i++) {
				if (board[TILE_LAYER][i] == true) {
					if (board[COLOUR_LAYER][i] == false) {
						player = WHITE_PLAYER;
						return false;
					}
				}
			}
		} else {
			for (int i = 0; i < 32; i++) {
				if (board[COLOUR_LAYER][i] == true) {
					player = BLACK_PLAYER;
					return false;
				}
			}
		}
		return true;
	}

	public int getTurnCounter() {
		return turncounter;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public void setBoard(boolean[][] test_board) {
		board = test_board;
	}

	public boolean[][] getBoard() {
		return board;
	}

	public void printBoard() {
		int i = 0;
		int row = 0;

		for (int tile = 0; tile < 64; tile++) {
			if (tile % 8 == 0) {
				System.out.println("");
			}
			row = (int) tile / 8;
			if ((row & 1) == 0) {
				if ((tile & 1) == 0) {
					System.out.print("0 ");
				} else {
					if (board[TILE_LAYER][i] == true && board[COLOUR_LAYER][i] == true
							&& board[KING_LAYER][i] == false) {
						System.out.print("BC ");
					} else if (board[TILE_LAYER][i] == true && board[COLOUR_LAYER][i] == false
							&& board[KING_LAYER][i] == false) {
						System.out.print("WC ");
					} else if (board[TILE_LAYER][i] == true && board[COLOUR_LAYER][i] == true
							&& board[KING_LAYER][i] == true) {
						System.out.print("BK ");
					} else if (board[TILE_LAYER][i] == true && board[COLOUR_LAYER][i] == false
							&& board[KING_LAYER][i] == true) {
						System.out.print("WK ");
					} else {
						System.out.print("1  ");
					}
					i++;
				}
			} else {
				if ((tile & 1) == 1) {
					System.out.print("0 ");
				} else {
					if (board[TILE_LAYER][i] == true && board[COLOUR_LAYER][i] == true
							&& board[KING_LAYER][i] == false) {
						System.out.print("BC ");
					} else if (board[TILE_LAYER][i] == true && board[COLOUR_LAYER][i] == false
							&& board[KING_LAYER][i] == false) {
						System.out.print("WC ");
					} else if (board[TILE_LAYER][i] == true && board[COLOUR_LAYER][i] == true
							&& board[KING_LAYER][i] == true) {
						System.out.print("BK ");
					} else if (board[TILE_LAYER][i] == true && board[COLOUR_LAYER][i] == false
							&& board[KING_LAYER][i] == true) {
						System.out.print("WK ");
					} else {
						System.out.print("1  ");
					}
					i++;
				}
			}
		}
		System.out.println("");
	}

	public int pieceCount() {
		int numberOfPieces = 0;

		for (int i = 0; i < board[TILE_LAYER].length; i++) {
			if (board[TILE_LAYER][i] == true) {
				numberOfPieces++;
			}
		}

		return numberOfPieces;
	}

	public void resetBoard() {
		board = new boolean[][] {
				{ true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false,
						false, false, false, false, true, true, true, true, true, true, true, true, true, true, true,
						true }, // Which tiles are occupied
				{ true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false,
						false, false, false, false, false, false, false, false, false, false, false, false, false,
						false, false, false }, // What colour the pieces are,
												// true = white, false = black
				{ false, false, false, false, false, false, false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false, false, false, false, false, false,
						false, false, false, false, false }// What pieces are
															// kings
		};

		player = BLACK_PLAYER;
		turncounter = 1;
	}

}
