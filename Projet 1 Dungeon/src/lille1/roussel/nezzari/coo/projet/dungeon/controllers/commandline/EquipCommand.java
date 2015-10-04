package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import java.util.List;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.EquipementException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Weapon;
import lille1.roussel.nezzari.coo.projet.dungeon.view.ConsoleView;

public class EquipCommand extends Command {

	@Override
	public void execute(String userInput) throws CannotBeUsedException {
		List<Weapon> weapons = Game.getInstance().getPlayer().getBag().getWeapons();
		if(weapons.isEmpty()) {
			System.out.println("You have no weapons");
		} else if (!Game.getInstance().getPlayer().canCarryNewWeapon()) {
			System.out.println("You cannot carry more than two weapons at a time !");
		} else {
			System.out.println("Which weapon to equip ?");
			for(int i = 0; i < weapons.size(); ++i) {
				System.out.println((i+1 + " - " + weapons));
			}
			
			int choice = ConsoleView.sc.nextInt();
			ConsoleView.sc.nextLine();
			
			try {
				weapons.get(choice - 1).equipToPlayer();
				System.out.println(weapons.get(choice - 1) + " equiped !");
			} catch (EquipementException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	@Override
	public void executeInFight(String userInput) throws CannotBeUsedException {
		execute(userInput);
	}
	
	@Override
	public void printHelp() {
		
	}
	
	
	
}
