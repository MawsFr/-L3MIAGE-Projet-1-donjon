package command;

import static org.junit.Assert.*;
import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.CommandLine;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Player;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.Direction;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.NormalRoom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CommandLineTest {

	protected CommandLine commandLine;
	protected Game game;
	protected Player player;
	protected Dungeon dungeon;
	protected IRoom room1;
	protected IRoom room2;
	
	
	
	@Before
	public void setUp() throws Exception {
		player = new Player("Maws");
		game = Game.getInstance();
		game.setPlayer(player);
		commandLine = new CommandLine();
		dungeon = new Dungeon("Death dungeon");
		room1 = new NormalRoom("Entrance", dungeon);
		room2 = new NormalRoom("Intersection", dungeon);
		dungeon.addRoom(room1);
		dungeon.addRoom(room2);
		
		game.setCurrentDungeon(dungeon);
		dungeon.init();
		
	}
	
	

	
	
	@Test
	public void GoCommandWrongDirectionTest() {
		room1.addNeighbor(Direction.North, room2);
	}

	@After
	public void tearDown() throws Exception {
	}

}
