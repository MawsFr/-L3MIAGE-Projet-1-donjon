package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;

public class DrinkCommand extends UseSpecialObjectCommand {
	
	@Override
	public void execute(String userInput) throws CannotBeUsedException {
		try {
			super.execute(userInput);
		} catch (CannotBeUsedException e) {
			System.err.println("There are no fountain here for you Stranger !");	
		}
	}
	
	@Override
	public String printHelp() {
		return "//Drink Command// : type \"drink\" to restore your Health Point at the Maximum.";
	}

}
