package database;

import static org.junit.Assert.*;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	
	//Test the game logic
	
	@Before
	public void setUp() throws Exception {
		Database.initDb();
	}
	


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
	}

}
