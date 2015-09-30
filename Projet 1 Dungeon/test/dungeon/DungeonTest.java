package dungeon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.NormalRoom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DungeonTest {
	
	protected Dungeon dungeon;
	protected IRoom room1;

	@Before
	public void setUp() throws Exception {
		dungeon = new Dungeon("Dungeon");
		room1 = new NormalRoom("Entrance", dungeon);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void dungeonWithNullName() {
		dungeon = new Dungeon(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void dungeonWithEmptyName() {
		dungeon = new Dungeon("");
	}
	
	@Test(expected=NullPointerException.class)
	public void dungeonWithNoEntrance() throws NullPointerException{
		dungeon.init();
		assertFalse(dungeon.isInitialized());
	}
	
	@Test
	public void dungeonWithEntrance() {
		dungeon.addRoom(room1);
		dungeon.init();
		assertTrue(dungeon.isInitialized());
		
	}

	@After
	public void tearDown() throws Exception {
	}


}
