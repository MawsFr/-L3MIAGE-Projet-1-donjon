package rooms;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.CommandLine;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.AlreadyUnlockedRoomException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game.GAMES_STATES;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Player;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.AtkPowerUp;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.IBagItem;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.Potion;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Sword;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.Direction;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.NormalRoom;

import org.junit.Before;
import org.junit.Test;


public class RoomTest {
	
	protected CommandLine commandLine;
	protected Game game;
	protected Player player;
	protected Dungeon dungeon1;
	protected IRoom room1;
	protected IRoom room2;
	protected IRoom room3;
	protected IRoom room4;
	protected IRoom room5;
	
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
		player = new Player("Maws");
		game = Game.getInstance();
		game.setCurrentState(GAMES_STATES.INGAMESTATE);
		game.setPlayer(player);
		commandLine = new CommandLine();
		dungeon1 = new Dungeon("Death dungeon");
		room1 = new NormalRoom("Entrance", dungeon1);
		room2 = new NormalRoom("Intersection", dungeon1);
		room3 = new NormalRoom("trap", dungeon1);
		room4 = new NormalRoom("exit", dungeon1);
		room5 = new NormalRoom("Room 5", dungeon1);
		dungeon1.addRoom(room1);
		dungeon1.addRoom(room2);
		dungeon1.addRoom(room3);
		dungeon1.addRoom(room4);
		dungeon1.addRoom(room5);
		
		game.setCurrentDungeon(dungeon1);
		dungeon1.init();
		
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
	public void unlockRoomTest() throws AlreadyUnlockedRoomException {
		room1.lock();
		assertTrue(room1.isLocked());
		room1.unlock();
		assertTrue(!room1.isLocked());

	}
	
	@Test(expected=CannotBeUsedException.class)
	public void tryEnterUnlockedRoom() throws CannotBeUsedException {
		room1.lock();
		room1.enter();
	}
	
	@Test
	public void findNoItem() throws CannotBeUsedException, AlreadyUnlockedRoomException {
		assertFalse(room1.findAllItems());
	}
	
	@Test
	public void findItemToUseWhenFound() throws CannotBeUsedException, AlreadyUnlockedRoomException {
		player.setHealthPoints(100);
		
		Potion item = new Potion("Potion");
		item.setUseWhenFound(true);
		
		room1.addItemsToFind(item);
		
		assertTrue(room1.findAllItems());
		
		assertEquals(player.MAX_HEALTH_POINTS, player.getHealthPoints());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addNeighborTest() {
		room1.addNeighbor(Direction.North, room1);
	}
	
	
	

}
