package rooms;
import static org.junit.Assert.*;

import java.util.List;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.AlreadyUnlockedRoomException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.Direction;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.NormalRoom;

import org.junit.Before;
import org.junit.Test;


public class RoomTest {
	
	Dungeon dungeon1;
	
	protected IRoom room1;
	protected IRoom room2;
	protected IRoom room3;
	protected IRoom room4;
	protected IRoom room5;
	

	@Before
	public void setUp() throws Exception {
		dungeon1 = new Dungeon("Dungeon 1");
		room1 = new NormalRoom("entrance", dungeon1);
		room2 = new NormalRoom("intersection", dungeon1);
		room3 = new NormalRoom("trap", dungeon1);
		room4 = new NormalRoom("exit", dungeon1);
		room5 = new NormalRoom("Room 5", dungeon1);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void roomWithNoName() {
		room1 = new NormalRoom(null, dungeon1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void roomWithEmptyName() {
		room1 = new NormalRoom("", dungeon1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void roomWithNullDungeon() {
		room1 = new NormalRoom("Room", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addNullNeighbor() {
		room1.addNeighbor(Direction.North, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addRoomAsItsNeighbor() {
		room1.addNeighbor(Direction.North, room1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addNullItemToFind() {
		room1.addItemToFind(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setNullName() {
		room1.setName(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void setEmptyName() {
		room1.setName("");
	}
	
	@Test
	public void testGetNeighbors() {
		room1.addNeighbor(Direction.North, room2);
		room2.addNeighbor(Direction.West, room3);
		room2.addNeighbor(Direction.North, room4);
		room2.addNeighbor(Direction.North, room5);
		
		assertTrue(room1.isNeighborOf(room2));
		assertTrue(room1.hasNeighbor(room2));
		assertFalse(room1.isNeighborOf(room3));
		assertFalse(room1.hasNeighbor(room3));
		
		List<IRoom> room2Neighbors = room2.getNeighbors(Direction.North);
		
		assertEquals("exit", room2Neighbors.get(0).getName());
		assertEquals("Room 5", room2Neighbors.get(1).getName());
	}
	
	@Test(expected=AlreadyUnlockedRoomException.class)
	public void tryUnlockAlreadyUnlockedRoom() throws AlreadyUnlockedRoomException {
		room1.unlock();
		room1.unlock();
	}
	
	@Test
	public void findNoItem() throws CannotBeUsedException, AlreadyUnlockedRoomException {
		assertFalse(room1.findAllItems());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addNeighborTest() {
		room1.addNeighbor(Direction.North, room1);
	}
	
	
	

}
