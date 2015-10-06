package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;

public class PlayerStatsCommand extends Command {

	@Override
	public void execute(String userinput) throws CannotBeUsedException {
		System.out.println("Player : " + Game.getInstance().getPlayer());
		System.out.println("Bag : \n" + Game.getInstance().getPlayer().getBag());
	}
	
	@Override
	public void executeInFight(String userInput) throws CannotBeUsedException {
		System.out.println(Game.getInstance().getPlayer());
	}
	
	@Override
	public String printHelp() {
		return "//Player stats// : type \"me\" to see your stats";
	}
	
}
