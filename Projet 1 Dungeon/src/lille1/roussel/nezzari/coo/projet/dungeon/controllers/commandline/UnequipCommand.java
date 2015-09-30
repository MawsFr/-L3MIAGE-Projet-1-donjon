package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.EquipementException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Player;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Weapon;
import lille1.roussel.nezzari.coo.projet.dungeon.view.ConsoleView;

public class UnequipCommand extends Command {

	@Override
	public void execute(String userinput) throws CannotBeUsedException {
		Player player = Game.getInstance().getPlayer();
		if(!player.hasWeaponsEquiped()) {
			System.err.println("You don't have any weapon equiped !");
		} else {
			Weapon rightWeapon = player.getRightHandWeapon();
			Weapon leftWeapon = player.getLeftHandWeapon();
			Weapon weaponToUnequip = null;
			if(rightWeapon != null && leftWeapon != null) {
				System.out.println("Which weapon to unequip ?");
				System.out.println("1 - " + rightWeapon);
				System.out.println("2 - " + leftWeapon);
				int choice = ConsoleView.sc.nextInt();
				ConsoleView.sc.nextLine();
				if(choice == 1) {
					weaponToUnequip = rightWeapon;
				} else {
					weaponToUnequip = leftWeapon;
				}
			} else {
				if(rightWeapon != null) {
					weaponToUnequip = rightWeapon;
				} else if(leftWeapon != null) {
					weaponToUnequip = leftWeapon;
				}
			}
			try {
				weaponToUnequip.unequipOfPlayer();
				System.out.println(weaponToUnequip + " unequiped !");
			} catch (EquipementException e) {
				System.err.println(e.getMessage());
			}
			
			System.out.println();


		}
	}

	@Override
	public void executeInFight(String userInput) throws CannotBeUsedException {

	}

	@Override
	public void printHelp() {
		System.out.println("//UNEQUIP COMMAND//: type \"unequip\" to unequip a weapon");
	}

}
