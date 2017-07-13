import org.junit.Test;

public class TestMonteCarlo {
	
	private Game testGame = new Game();
	private MonteCarlo test = new MonteCarlo(testGame.getBoard(), testGame.getPlayer(), 25, 10000);
	
	@Test
	public void legalMoves() {

		test.generateMove();
	}

}
