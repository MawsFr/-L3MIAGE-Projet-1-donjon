package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;

public abstract class Command {

	//TODO : add possibility to specify directly in argument userInput for example for the use command, the object we want to use
	
	public abstract void execute(String userinput) throws CannotBeUsedException;
	public abstract void executeInFight(String userInput) throws CannotBeUsedException;

	public abstract void printHelp();

}
