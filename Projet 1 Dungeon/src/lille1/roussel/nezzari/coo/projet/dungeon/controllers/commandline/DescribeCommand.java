package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;

public class DescribeCommand extends Command {
	
	@Override
	public void execute(String userInput) {
		try {
//			System.out.println(Game.getInstance().getCurrentDungeon().getCurrentRoom());
			System.out.println(Game.getInstance().getCurrentDungeon().getCurrentRoom().getDescription());
		} catch (CannotBeUsedException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	@Override
	public void executeInFight(String substring) {
		
	}
	
	@Override
	public void printHelp() {
		System.out.println("//Describe Command// : type \"describe\" to describe the room");
		
		
	}

}
