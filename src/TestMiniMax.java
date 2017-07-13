import org.junit.Test;

public class TestMiniMax {

	private Game testGame = new Game();
	private MiniMax test;
	
	@Test
	public void legalMoves() {
		
		boolean[][] test_board = new boolean[][]{
			{false,true,true,false,
			 false,true,true,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,true,true,false,
			 false,true,true,false},//Which tiles are occupied
			{false,true,true,false,
			 false,true,true,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false},//What colour the pieces are, true = black, false = white
			{false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false}//What pieces are kings
		};
		
		testGame.setBoard(test_board);
		
		test = new MiniMax(testGame.getBoard(), 1);
		
		test.generateTree();
		Move move = test.selectMove();
		
		System.out.println(move.getCurrent() + ", " + move.getDestination());
		
	}
	
}
