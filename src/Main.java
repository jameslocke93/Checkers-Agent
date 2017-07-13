import java.util.Random;
import java.util.Scanner;

public class Main {

	private static final int MINIMAXPIECES = 7;
	private static final int NUMBEROFRUNS = 100;
	
	private static final int MONTERUNS = 10000;
	private static final int REGRET = 5;
	
	private static final int MONTERUNSTWO = 10000;
	private static final int REGRETTWO = 3;

	public static void main(String[] args) {
		Main main = new Main();

		//main.playerVplayer();
		// main.playerVmonte();
		//main.maxVmonte();
		//main.playerVmax();
		main.monteVmonte();
	}

	private void monteVmonte() {
		
		Game game = new Game();
		boolean running = true;

		Random rand = new Random();
		int monte = rand.nextInt(2) + 1;
		int monteTwo = 0;

		int monteOneWins = 0;
		
		for (int i = 0; i < NUMBEROFRUNS; i++) {
			
			running = true;
			UserGraphics window = new UserGraphics(game.getBoard());
			
			if (monte == 1) {
				monteTwo = 2;
			} else {
				monteTwo = 1;
			}

			while (running) {
				window.repaint();
				// Checks if any takes are available
				if (game.takeCheck() == false) {
					if (monte == game.getPlayer()) {
						// System.out.println("Monte's move... ");
						MonteCarlo machineMove = new MonteCarlo(game.getBoard(),monte, REGRET, MONTERUNS);
						Move move = machineMove.generateMove();
						if(move == null){
							//Forfeit
							running = false;
							game.resetBoard();
							window.setVisible(false);
						} else {
							while (game.moveCheck(move.getCurrent(), move.getDestination()) == false) {
								// System.out.println("Monte messed up");
							}
						}

					} else {
						// System.out.println("Monte's move... ");
						MonteCarlo machineMove = new MonteCarlo(game.getBoard(), monteTwo, REGRETTWO, MONTERUNSTWO);
						Move move = machineMove.generateMove();
						if(move == null){
							//Forefit
							//System.out.println("Monte One wins");
							monteOneWins++;
							running = false;
							game.resetBoard();
							window.setVisible(false);
						} else {
							while (game.moveCheck(move.getCurrent(), move.getDestination()) == false) {
								// System.out.println("Monte messed up");
							}
						}

					}
					game.kingCheck();

				} else {
					while (game.takeCheck()) {
					}
				}

				if (game.winCheck() == true) {
					if (game.getPlayer() == monte) {
						//System.out.println("Monte One wins");
						monteOneWins++;
					} else {
						//System.out.println("Monte Two wins");
					}
					running = false;
					game.resetBoard();
					window.setVisible(false);
				}
				
				if(game.getTurnCounter() >= 300){
					running = false;
					game.resetBoard();
					window.setVisible(false);
				}
			}
		}
		System.out.println("Monte One won " + (double) monteOneWins/NUMBEROFRUNS);
		System.exit(0);
	}

	private void playerVplayer() {

		Game game = new Game();
		int initPos = 0;
		int desPos = 0;
		Scanner input = new Scanner(System.in);
		String inputString;
		String[] inputArray;
		boolean running = true;
		UserGraphics window = new UserGraphics(game.getBoard());

		while (running == true) {
			window.repaint();
			// Checks if any takes are available
			if (game.takeCheck() == false) {
				// Asks user to enter move
				game.printBoard();
				System.out.println(
						"Player " + game.getPlayer() + " Please enter your move: initial Position,destination ");
				inputString = input.nextLine();
				inputArray = inputString.split(",", 2);
				initPos = Integer.parseInt(inputArray[0]) - 1;
				desPos = Integer.parseInt(inputArray[1]) - 1;

				while (game.moveCheck(initPos, desPos) == false) {
					// If move is incorrect
					System.out.println("Incorrect Move");
					System.out.println(
							"Player " + game.getPlayer() + " Please enter your move: initial Position,destination ");
					inputString = input.nextLine();
					inputArray = inputString.split(",", 2);
					initPos = Integer.parseInt(inputArray[0]);
					desPos = Integer.parseInt(inputArray[1]);
				}
				game.kingCheck();

			} else {
				System.out.println("Take Happened");
				System.out.println(game.getPlayer());
			}
			if (game.winCheck() == true) {
				running = false;

			}
		}
		// Player wins
		input.close();
	}

	private void playerVmonte() {

		Game game = new Game();
		int initPos = 0;
		int desPos = 0;
		Scanner input = new Scanner(System.in);
		String inputString;
		String[] inputArray;
		boolean running = true;
		UserGraphics window = new UserGraphics(game.getBoard());

		Random rand = new Random();
		int machinePlayer = rand.nextInt(2) + 1;

		while (running) {
			window.repaint();
			// Checks if any takes are available
			if (game.takeCheck() == false) {
				if (machinePlayer == game.getPlayer()) {
					System.out.println("Machine's move... ");
					MonteCarlo machineMove = new MonteCarlo(game.getBoard(), machinePlayer, REGRET, MONTERUNS);
					Move move = machineMove.generateMove();
					if(move == null){
						//Forfeit
						running = false;
						game.resetBoard();
						window.setVisible(false);
					} else {
						while (game.moveCheck(move.getCurrent(), move.getDestination()) == false) {
							System.out.println("Machine messed up");
						}
					}

				} else {
					// Asks user to enter move
					game.printBoard();
					System.out.println(
							"Player " + game.getPlayer() + " Please enter your move: initial Position,destination ");
					inputString = input.nextLine();
					inputArray = inputString.split(",", 2);
					initPos = Integer.parseInt(inputArray[0]) - 1;
					desPos = Integer.parseInt(inputArray[1]) - 1;

					while (game.moveCheck(initPos, desPos) == false) {
						// If move is incorrect
						System.out.println("Incorrect Move");
						System.out.println("Player " + game.getPlayer()
								+ " Please enter your move: initial Position,destination ");
						inputString = input.nextLine();
						inputArray = inputString.split(",", 2);
						initPos = Integer.parseInt(inputArray[0]);
						desPos = Integer.parseInt(inputArray[1]);
					}
				}
				game.kingCheck();

			} else {
				while (game.takeCheck()) {
				}
				System.out.println("Take Happened");
				System.out.println(game.getPlayer());
			}
			if (game.winCheck() == true) {
				running = false;

			}
		}
		input.close();
	}

	private void playerVmax() {

		Game game = new Game();
		int initPos = 0;
		int desPos = 0;
		Scanner input = new Scanner(System.in);
		String inputString;
		String[] inputArray;
		boolean running = true;
		UserGraphics window = new UserGraphics(game.getBoard());

		Random rand = new Random();
		int machinePlayer = rand.nextInt(2) + 1;

		while (running) {
			window.repaint();
			// Checks if any takes are available
			if (game.takeCheck() == false) {
				if (machinePlayer == game.getPlayer()) {
					Move move;

					if (game.pieceCount() < MINIMAXPIECES) {
						MiniMax machineMove = new MiniMax(game.getBoard(), machinePlayer);
						machineMove.generateTree();
						move = machineMove.selectMove();
					} else {
						MonteCarlo machineMove = new MonteCarlo(game.getBoard(), machinePlayer, REGRET, MONTERUNS);
						move = machineMove.generateMove();
					}
					if(move == null){
						//Forfeit
						running = false;
						game.resetBoard();
						window.setVisible(false);
					} else {
						while (game.moveCheck(move.getCurrent(), move.getDestination()) == false) {
						}
					}

				} else {
					// Asks user to enter move
					game.printBoard();
					System.out.println(
							"Player " + game.getPlayer() + " Please enter your move: initial Position,destination ");
					inputString = input.nextLine();
					inputArray = inputString.split(",", 2);
					initPos = Integer.parseInt(inputArray[0]) - 1;
					desPos = Integer.parseInt(inputArray[1]) - 1;

					while (game.moveCheck(initPos, desPos) == false) {
						// If move is incorrect
						System.out.println("Incorrect Move");
						System.out.println("Player " + game.getPlayer()
								+ " Please enter your move: initial Position,destination ");
						inputString = input.nextLine();
						inputArray = inputString.split(",", 2);
						initPos = Integer.parseInt(inputArray[0]);
						desPos = Integer.parseInt(inputArray[1]);
					}
				}
				game.kingCheck();

			} else {
				while (game.takeCheck()) {
				}
				System.out.println("Take Happened");
				System.out.println(game.getPlayer());
			}
			if (game.winCheck() == true) {
				running = false;

			}
		}
		// Player wins
		input.close();
	}

	private void maxVmonte() {

		Game game = new Game();
		boolean running = true;

		Random rand = new Random();
		int monte = rand.nextInt(2) + 1;
		int max = 0;

		int maxWins = 0;

		for (int i = 0; i < NUMBEROFRUNS; i++) {

			running = true;
			UserGraphics window = new UserGraphics(game.getBoard());

			if (monte == 1) {
				max = 2;
			} else {
				max = 1;
			}

			while (running) {
				window.repaint();
				// Checks if any takes are available
				if (game.takeCheck() == false) {
					if (monte == game.getPlayer()) {
						// System.out.println("Monte's move... ");
						MonteCarlo machineMove = new MonteCarlo(game.getBoard(), monte, REGRET, MONTERUNS);
						Move move = machineMove.generateMove();

						while (game.moveCheck(move.getCurrent(), move.getDestination()) == false) {
							// System.out.println("Monte messed up");
						}

					} else {
						Move move;

						// System.out.println("Max's move... ");

						if (game.pieceCount() < MINIMAXPIECES) {
							MiniMax machineMove = new MiniMax(game.getBoard(), max);
							machineMove.generateTree();
							move = machineMove.selectMove();
						} else {
							MonteCarlo machineMove = new MonteCarlo(game.getBoard(), max, REGRET, MONTERUNS);
							move = machineMove.generateMove();
						}

						while (game.moveCheck(move.getCurrent(), move.getDestination()) == false) {
							// System.out.println("Max messed up");
						}

					}
					game.kingCheck();

				} else {
					while (game.takeCheck()) {
					}
				}

				if (game.winCheck() == true) {
					if (game.getPlayer() == max) {
						System.out.println("Max wins!");
						maxWins++;
					} else {
						System.out.println("Monte wins!");

					}
					running = false;
					game.resetBoard();
					window.setVisible(false);
				}
			}
		}
		// System.out.println("Max won " + (double) maxWins/NUMBEROFRUNS);
		System.exit(0);

	}
}