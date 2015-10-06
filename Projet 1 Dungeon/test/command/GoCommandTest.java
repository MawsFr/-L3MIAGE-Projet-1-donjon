package command;

import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.Command;
import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.CommandLine.Commands;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.InvalidCommandException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game.GAMES_STATES;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.Potion;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.Direction;
import static org.junit.Assert.*;

import org.junit.Test;

public class GoCommandTest extends CommandLineTest {
	
	protected Command goCommand;
	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.goCommand = this.commandLine.getCommand(Commands.GO);
	}
	
	@Test(expected=InvalidCommandException.class)
	public void goNullDirection() throws CannotBeUsedException, InvalidCommandException {
		goCommand.execute(null);
		
	}
	
	@Test(expected=InvalidCommandException.class)
	public void goInvalidDirection() throws CannotBeUsedException, InvalidCommandException {
		goCommand.execute("heaven");
		
	}
	
	@Test
	public void goValidDirection() throws CannotBeUsedException, InvalidCommandException {
		room1.addNeighbor(Direction.North, room2);
		goCommand.execute("north");
		assertEquals(room2, this.dungeon.getCurrentRoom());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void goValidDirectionButNoRoom() throws CannotBeUsedException, InvalidCommandException {
		goCommand.execute("north");
	}
	
	@Test
	public void findObjectTest() throws CannotBeUsedException, InvalidCommandException {
		room1.addNeighbor(Direction.North, room2);
		Potion potion = new Potion("Potion");
		room2.addItemsToFind(potion);
		goCommand.execute("north");
		game.getPlayer().getBag().getItems().contains(potion);
	}
	
	@Test(expected=CannotBeUsedException.class)
	public void goInFight() throws CannotBeUsedException {
		game.setCurrentState(GAMES_STATES.INFIGHTSTATE);
		goCommand.executeInFight(null);
	}
	
	@Test
	public void printHelpTest() {
		assertEquals("//Go Command// : Type \"go + direction\" where direction can be {north, south, west, east, up, down}", goCommand.printHelp());
	}
	
	
}
