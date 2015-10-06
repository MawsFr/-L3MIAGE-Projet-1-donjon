package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.CommandLine.Commands;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.fight.Fight;

public class AttackCommand extends Command {
	
	protected Fight currentFight;
	
	@Override
	public void execute(String substring) throws CannotBeUsedException {
		//throw exception can use out of a fight
		throw new CannotBeUsedException("The attack command cannot be used outside a fight");
	}
	
	@Override
	public void executeInFight(String substring) {
		if(substring.equalsIgnoreCase(Commands.ATTACK.name())) {
			System.out.println("You attack !");
			currentFight.damageMonster();
		}
	}
	
	public Fight getCurrentFight() {
		return currentFight;
	}
	
	public void setCurrentFight(Fight currentFight) {
		this.currentFight = currentFight;
	}
	
	@Override
	public String printHelp() {
		return "//Attack command// : In fight, type \"attack\" to attack the monster";
		
	}

}
