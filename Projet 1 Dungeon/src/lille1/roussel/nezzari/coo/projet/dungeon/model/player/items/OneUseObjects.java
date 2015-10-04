package lille1.roussel.nezzari.coo.projet.dungeon.model.player.items;

import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Weapon;

public abstract class OneUseObjects extends Weapon {
	
	public OneUseObjects(String name) {
		super(name);
		removedWhenUsed = true;
	}


	@Override
	public boolean isWeapon() {
		return false;
	}
	

}
