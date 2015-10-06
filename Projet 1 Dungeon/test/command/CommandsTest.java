package command;

import static org.junit.Assert.*;
import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.CommandLine;
import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.CommandLine.Commands;

import org.junit.Before;
import org.junit.Test;

public class CommandsTest extends CommandLineTest {

	protected CommandLine commandLine;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.commandLine = new CommandLine();
	}
	
	@Test
	public void getCommandTest() {
		assertEquals(Commands.get("go"), Commands.GO);
	}


}
