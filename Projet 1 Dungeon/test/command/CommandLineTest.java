package command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.AttackCommand;
import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.CommandLine;
import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.CommandLine.Commands;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.EquipementException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.InvalidCommandException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game.GAMES_STATES;
import lille1.roussel.nezzari.coo.projet.dungeon.model.fight.Fight;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Player;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Sword;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.ButtonRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.Direction;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.FightRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.NormalRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.RestoreRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.TreasureRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.Button;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.Fountain;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.Treasure;

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
		dungeon = new Dungeon("Death dungeon");
		room1 = new NormalRoom("Entrance", dungeon);
		room2 = new NormalRoom("Intersection", dungeon);
		dungeon.addRoom(room1);
		dungeon.addRoom(room2);
		
		game.setCurrentDungeon(dungeon);
		dungeon.init();
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void interpretNullCommand() throws IllegalArgumentException, InvalidCommandException {
		commandLine.interpretCommand(null);
	}

	@Test(expected=InvalidCommandException.class)
	public void interpretInvalidCommand() throws InvalidCommandException {
		commandLine.interpretCommand("hello");
	}
	
	//ME, GO, USE, DESCRIBE, PUSH /*button*/, OPEN /*treasure*/, ATTACK, HELP, EQUIP, UNEQUIP, DRINK;
	
	@Test
	public void interpretGoCommandTest() throws InvalidCommandException {
		//GO COMMAND
		room1.addNeighbor(Direction.North, room2);
		commandLine.interpretCommand("go north");
		assertEquals(dungeon.getCurrentRoom(), room2);
	}
	
	@Test
	public void interpretUseCommandTest() throws InvalidCommandException {
		
	}
	
	@Test
	public void interpretDescribeCommandTest() throws InvalidCommandException, CannotBeUsedException {
		room1.setDescription("You are in the hall of the dungeon, welcome boy ! :)");
		room1.addNeighbor(Direction.North, room2);
		
		commandLine.interpretCommand("describe");
		
		assertEquals("\nName : Entrance\n" +

		"\nNeighbours :\n"+

		"\nNorth : 1 room(s)"+
		"\nSouth : 0 room(s)"+
		"\nWest : 0 room(s)"+
		"\nEast : 0 room(s)"+
		"\nUp : 0 room(s)"+
		"\nDown : 0 room(s)\n"+

		"\nYou are in the hall of the dungeon, welcome boy ! :)" + "\n", outContent.toString());
	}
	
	@Test
	public void interpretPushCommandTest() throws InvalidCommandException {
		Button button = new Button("Button", room2);
		assertFalse(room2.isVisible());
		ButtonRoom room = new ButtonRoom("TestButtonRoom", dungeon, button);
		dungeon.addRoom(room);
		dungeon.setCurrentRoom(room);
		commandLine.interpretCommand("push");
		assertTrue(room2.isVisible());
		
	}
	
	@Test
	public void interpretOpenCommandTest() throws InvalidCommandException {
		Treasure treasure = new Treasure("Treasure", room2);
		assertFalse(room2.isVisible());
		TreasureRoom room = new TreasureRoom("TestTreasureRoom", dungeon, treasure);
		dungeon.setCurrentRoom(room);
		commandLine.interpretCommand("open");
		assertTrue(room2.isVisible());
	}
	
	@Test
	public void interpretAttackCommandTest() throws InvalidCommandException, EquipementException {
		Fight fight = new Fight();
		FightRoom room = new FightRoom("Fight", dungeon, fight);
		dungeon.setCurrentRoom(room);
		game.setCurrentState(GAMES_STATES.INFIGHTSTATE);
		((AttackCommand) commandLine.getCommand(Commands.ATTACK)).setCurrentFight(fight);
		
		Sword sword = new Sword("Sword");
		player.equipWeapon(sword);
		fight.setPlayer(player);
		int hp = fight.getMonster().getHealthPoints();
		commandLine.interpretCommand("attack");
		
		assertEquals(hp - player.getDamagePoints(), fight.getMonster().getHealthPoints());
	}
	
	@Test
	public void interpretEquipCommandTest() throws InvalidCommandException {
		
	}
	
	@Test
	public void interpretUnequipCommandTest() throws InvalidCommandException {
		
	}
	
	@Test
	public void interpretDrinkCommandTest() throws InvalidCommandException {
		RestoreRoom room = new RestoreRoom("RestoreTestRoom", dungeon, new Fountain("Fountain"));
		dungeon.setCurrentRoom(room);
		player.setDamagePoints(100);
		commandLine.interpretCommand("drink");
		assertEquals(player.MAX_HEALTH_POINTS, player.getHealthPoints());
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(null);
	    System.setErr(null);
	}
	
}
