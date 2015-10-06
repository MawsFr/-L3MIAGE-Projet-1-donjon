package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;

public class DescribeCommand extends Command {

	@Override
	public void execute(String userInput) {
		System.out.println(Game.getInstance().getCurrentDungeon().getCurrentRoom());
		System.out.println(Game.getInstance().getCurrentDungeon().getCurrentRoom().getDescription());

	}

	@Override
	public void executeInFight(String substring) {

	}

	@Override
	public String printHelp() {
		return "//Describe Command// : type \"describe\" to describe the room";


	}

}
