import static org.junit.Assert.*;

import org.junit.Test;

public class Test_Game {

	private Game test_game = new Game();
	
	@Test
	public void test_legal_take_men(){
		boolean[][] test_board = new boolean[][]{
			{false,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 true,false,false,false,
			 true,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,false},//Which tiles are occupied
			{false,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 true,false,false,false,
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
		
		test_game.setBoard(test_board);
		
		assertTrue(test_game.takeCheck());
	}
	
	@Test
	public void test_no_take_men(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 true,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,false},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
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
		
		test_game.setBoard(test_board);
		
		assertFalse(test_game.takeCheck());
	}
	
	@Test
	public void test_legal_take_king(){
		boolean[][] test_board = new boolean[][]{
			{false,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 true,false,false,false,
			 true,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,false},//Which tiles are occupied
			{false,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 true,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false},//What colour the pieces are, true = black, false = white
			{false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 true,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false}//What pieces are kings
		};
		
		test_game.setBoard(test_board);
		assertTrue(test_game.takeCheck());
	}
	
	@Test
	public void test_no_take_king(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,true,false,
			 false,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,false},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,true,false,
			 true,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false},//What colour the pieces are, true = black, false = white
			{false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,true,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false}//What pieces are kings
		};
		
		test_game.setBoard(test_board);
		assertFalse(test_game.takeCheck());
	}
	
	@Test
	public void test_legal_moveCheck_right_men(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
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
		
		test_game.setBoard(test_board);
		test_game.setPlayer(1);
		assertTrue(test_game.moveCheck(9,14));
	}
	
	@Test
	public void test_legal_moveCheck_left_men(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
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
		
		test_game.setBoard(test_board);
		
		assertTrue(test_game.moveCheck(9,13));
	}
	
	@Test
	public void test_no_legel_moveCheck_right_men(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
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
		
		test_game.setBoard(test_board);
		
		assertFalse(test_game.moveCheck(9,15));
	}
	
	@Test
	public void test_no_legal_moveCheck_left_men(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
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
		
		test_game.setBoard(test_board);
		test_game.setPlayer(1);
		assertFalse(test_game.moveCheck(10,13));
	}
	
	@Test
	public void test_legal_moveCheck_right_king(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 false,true,true,true,
			 false,false,false,false,
			 false,true,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 false,true,true,true,
			 false,false,false,false,
			 false,true,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false},//What colour the pieces are, true = black, false = white
			{false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,true,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false}//What pieces are kings
		};
		
		test_game.setBoard(test_board);
		test_game.setPlayer(1);
		assertTrue(test_game.moveCheck(18,15));
	}
	
	@Test
	public void test_legal_moveCheck_left_king(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 false,true,true,true,
			 false,false,false,false,
			 false,true,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 false,true,true,true,
			 false,false,false,false,
			 false,true,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false},//What colour the pieces are, true = black, false = white
			{false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,true,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false}//What pieces are kings
		};
		
		test_game.setBoard(test_board);
		
		assertTrue(test_game.moveCheck(18,14));
	}
	
	@Test
	public void test_no_legal_moveCheck_right_king(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 false,true,true,true,
			 false,false,false,false,
			 false,true,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 false,true,true,true,
			 false,false,false,false,
			 false,true,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false},//What colour the pieces are, true = white, false = black
			{false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,true,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false}//What pieces are kings
		};
		
		test_game.setBoard(test_board);
		
		assertFalse(test_game.moveCheck(18,16));
	}
	
	@Test
	public void test_no_legal_moveCheck_left_king(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 false,true,true,true,
			 false,false,false,false,
			 false,false,true,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 false,true,true,true,
			 false,false,false,false,
			 false,true,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false},//What colour the pieces are, true = black, false = white
			{false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,true,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false}//What pieces are kings
		};
		
		test_game.setBoard(test_board);
		
		assertFalse(test_game.moveCheck(18,13));
	}
	
	@Test
	public void test_black_king_upgrade(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 false,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 false,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,true,false,false},//What colour the pieces are, true = black, false = white
			{false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false}//What pieces are kings
		};
		
		test_game.setBoard(test_board);
		test_game.setPlayer(1);
		assertTrue(test_game.kingCheck());
	}
	
	@Test
	public void test_white_king_upgrade(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,false,true,true,
			 true,true,true,true,
			 false,true,true,true,
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
		
		test_game.setBoard(test_board);
		test_game.setPlayer(0);
		assertTrue(test_game.kingCheck());
	}
	
	@Test
	public void test_no_black_upgrade(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
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
		
		test_game.setBoard(test_board);
		test_game.setPlayer(1);
		assertFalse(test_game.kingCheck());
	}
	
	@Test
	public void test_no_white_upgrade(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
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
		
		test_game.setBoard(test_board);
		test_game.setPlayer(0);
		assertFalse(test_game.kingCheck());
	}
	
	@Test
	public void test_black_win_check(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
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
		
		test_game.setBoard(test_board);
		test_game.setPlayer(1);
		assertTrue(test_game.winCheck());
	}
	
	@Test
	public void test_white_win_check(){
		boolean[][] test_board = new boolean[][]{
			{false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{false,false,false,false,
			 false,false,false,false,
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
		
		test_game.setBoard(test_board);
		test_game.setPlayer(0);
		assertTrue(test_game.winCheck());
	}
	
	@Test
	public void test_no_black_win_check(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
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
		
		test_game.setBoard(test_board);
		test_game.setPlayer(1);
		assertFalse(test_game.winCheck());
	}
	
	@Test
	public void test_no_white_win_check(){
		boolean[][] test_board = new boolean[][]{
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
			 false,false,false,false,
			 false,false,false,false,
			 true,true,true,true,
			 true,true,true,true,
			 true,true,true,true},//Which tiles are occupied
			{true,true,true,true,
			 true,true,true,true,
			 true,true,true,true,
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
		
		test_game.setBoard(test_board);
		test_game.setPlayer(0);
		assertFalse(test_game.winCheck());
	}
	
	@Test
	public void test_double_jump(){
		boolean[][] test_board = new boolean[][]{
			{false,false,false,false,
			 false,false,true,false,
			 false,false,false,false,
			 false,false,true,false,
			 false,false,true,false,
			 false,false,false,false,
			 false,false,false,false,
			 false,false,false,false,},//Which tiles are occupied
			{false,false,false,false,
			 false,false,true,false,
			 false,false,false,false,
			 false,false,true,false,
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
		
		test_game.setBoard(test_board);
		test_game.setPlayer(2);
		
		if(test_game.takeCheck() == false){
		} else {
			System.out.println("taken");
			while(test_game.takeCheck()){
				System.out.println("another take");
			}
		}
		
		assertTrue(test_game.winCheck());
		
		
	}

}
