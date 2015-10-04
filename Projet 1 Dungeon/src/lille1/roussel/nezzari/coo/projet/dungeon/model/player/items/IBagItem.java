package lille1.roussel.nezzari.coo.projet.dungeon.model.player.items;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.AlreadyUnlockedRoomException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;

public interface IBagItem {
	
	public void addToBag();
	public void use() throws CannotBeUsedException, AlreadyUnlockedRoomException; //don't forget to remove from bag
	public void useInFight() throws CannotBeUsedException;
	public boolean usedWhenFound();
	public boolean isUsableInFight();
	public boolean isRemovedWhenUsed();
	public boolean isWeapon();
	public void removeFromBag();
	public String getMessageAfterUse();

}
