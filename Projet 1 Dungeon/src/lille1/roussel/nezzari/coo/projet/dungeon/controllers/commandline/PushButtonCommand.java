package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;

public class PushButtonCommand extends UseSpecialObjectCommand {
	@Override
	public void execute(String userInput) {
		try {
			super.execute(userInput);
		} catch (CannotBeUsedException e) {
			System.err.println("There are no buttons here !");	
		}

	}

	@Override
	public void executeInFight(String substring) {
		System.err.println("You can't use \"push\" command in a fight");

	}

	@Override
	public String printHelp() {
		return "//Push Button Command// : type \"push\" to push the button";

	}
	
}
