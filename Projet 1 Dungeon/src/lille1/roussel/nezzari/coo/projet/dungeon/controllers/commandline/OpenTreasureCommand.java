package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;

public class OpenTreasureCommand extends UseSpecialObjectCommand {

	//This class is not needed since treasure are automatically opened when the user describes the room

	@Override
	public void execute(String userInput) {
		try {
			super.execute(userInput);
		} catch (CannotBeUsedException e) {
			System.err.println("There are no trasure here !");
		}

	}

	@Override
	public void executeInFight(String userInput) {
		System.err.println("You can't use \"open\" in a fight !");
	}

	@Override
	public void printHelp() {
		System.out.println("//Open Trasure Command// : Type \"open treasure\" to open treasure");
	}

}
