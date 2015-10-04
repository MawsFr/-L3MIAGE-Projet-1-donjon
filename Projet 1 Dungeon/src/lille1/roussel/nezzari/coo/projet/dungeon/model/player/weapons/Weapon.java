package lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.EquipementException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Player;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.BagItem;

public abstract class Weapon extends BagItem {

	protected int damage;
	
	public Weapon(String name) {
		super(name);
		removedWhenUsed = false;
	}

	public void equipToPlayer() throws EquipementException {
		Player player = Game.getInstance().getPlayer();
		player.equipWeapon(this);
		
	}
	
	public void unequipOfPlayer() throws EquipementException {
		Player player = Game.getInstance().getPlayer();
		player.unequipWeapon(this);
	}

	public void useInFight() {
		//may remove this
	}
	
	@Override
	public boolean isWeapon() {
		return true;
	}
	
	public boolean isEquipedToPlayer() {
		Player player = Game.getInstance().getPlayer();
		return player.getLeftHandWeapon() == this || player.getRightHandWeapon() == this;
	}
	
	
	public abstract int getDamage();
	
	
	

}
