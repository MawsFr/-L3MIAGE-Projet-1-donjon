package game;

import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;

import org.junit.After;
import org.junit.Before;

public class GameTest {
	
	protected Game game;

	@Before
	public void setUp() throws Exception {
		game = Game.getInstance();
		
	}
	
	
	

	@After
	public void tearDown() throws Exception {
	}

}
