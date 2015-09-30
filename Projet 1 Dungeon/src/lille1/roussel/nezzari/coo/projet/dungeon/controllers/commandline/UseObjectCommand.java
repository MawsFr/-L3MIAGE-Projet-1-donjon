package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import java.util.List;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.AlreadyUnlockedRoomException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.EquipementException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game.GAMES_STATES;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Bag;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.IBagItem;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Weapon;
import lille1.roussel.nezzari.coo.projet.dungeon.view.ConsoleView;

public class UseObjectCommand extends Command {



	@Override
	public void execute(String userInput) {
		//TODO : for weapons show equipe or unequip
		if(Game.getInstance().getPlayer().getBag().isEmpty()) {
			System.out.println("You have no items in your bag !");
			return;
		}
		System.out.println("What do you want to use ?");
		Bag bag = Game.getInstance().getPlayer().getBag();
		List<Weapon> weapons = bag.getWeapons();
		List<IBagItem> items = bag.getItems();
		items.removeAll(weapons);
		int j = 0;
		if(items.isEmpty()) {
			System.out.println("You don't have normal object.");
		} else {
			System.out.println("#####\tNORMAL ITEMS\t#####");
			for(int i = 0; i < items.size(); ++i) {
				System.out.print((j+1) + " - " + items.get(i));
				if(i % 3 == 0) {
					System.out.println();
				}
				++j;
			}
			System.out.println();
		}

		if(weapons.isEmpty()) {
			System.out.println("You don't have any weapons.");
		} else {
			System.out.println("#####\tWEAPONS\t#####");

			for(int i = 0; i < weapons.size(); ++i) {
				System.out.println((j+1) + " - " + weapons.get(i) + ((weapons.get(i).isEquipedToPlayer()) ? " (equiped)" : ""));
				++j;
			}
		}

		items.addAll(weapons);
		System.out.println("\n0 - Cancel");

		int choice = ConsoleView.sc.nextInt();
		ConsoleView.sc.nextLine();

		if(choice == 0) {
			return;
		}

		IBagItem item = items.get(choice - 1);
		if(item.isWeapon()) {
			Weapon weapon = (Weapon) item;
			if(weapon.isEquipedToPlayer()) {
				try {
					weapon.unequipOfPlayer();
					System.out.println(weapon + " has been unequiped !");
				} catch (EquipementException e) {
					System.err.println(e.getMessage());
				}
			} else {
				try {
					weapon.equipToPlayer();
					System.out.println(weapon + " has been equiped to player !");
				} catch (EquipementException e) {
					System.err.println(e.getMessage());
				}
			}
		} else {
			try {
				if(Game.getInstance().getCurrentState() == GAMES_STATES.FIGHTSTATE) {
					item.useInFight();
				} else {
					item.use();
				}
				System.out.println(item + " has been used !");
				if(item.isRemovedWhenUsed()) {
					item.removeFromBag();
				}
				System.out.println(item.getMessageAfterUse());
			} catch (CannotBeUsedException e) {
				System.err.println(e.getMessage());
			} catch (AlreadyUnlockedRoomException e) {
				System.err.println(e.getMessage());
			}
		}
		
	}

	@Override
	public void executeInFight(String userInput) {
		execute(userInput);
	}

	@Override
	public void printHelp() {
		System.out.println("//Use Object Command// : Type \"use + object name\" to use an object");

	}

}
