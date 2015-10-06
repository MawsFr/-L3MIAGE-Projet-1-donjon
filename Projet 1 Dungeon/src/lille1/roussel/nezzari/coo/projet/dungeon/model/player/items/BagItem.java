package lille1.roussel.nezzari.coo.projet.dungeon.model.player.items;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.AlreadyUnlockedRoomException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;

public abstract class BagItem implements IBagItem {

	protected String name;
	protected boolean useWhenFound;
	protected boolean usableInFight;
	protected boolean removedWhenUsed;
	
	public BagItem(String name) {
		this.name = name;
		this.usableInFight = true;
		this.removedWhenUsed = true;
		
	}
	
	public void addToBag() {
		Game.getInstance().getPlayer().getBag().addItem(this);
	}
	
	public void removeFromBag() {
		Game.getInstance().getPlayer().getBag().removeItem(this);
	}
	
	public void use() throws CannotBeUsedException, AlreadyUnlockedRoomException {
		throw new CannotBeUsedException(this.name + " can't be used here !");
		
	}
	
	public void useInFight() throws CannotBeUsedException {
		throw new CannotBeUsedException(this.name + " can't be used in a fight !");
	}
	
	public boolean usedWhenFound() {
		return useWhenFound;
	}
	
	public boolean isUsableInFight() {
		return usableInFight;
	}
	
	public boolean isRemovedWhenUsed() {
		return removedWhenUsed;
	}
	
	public boolean isWeapon() {
		return false;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public void setUseWhenFound(boolean useWhenFound) {
		this.useWhenFound = useWhenFound;
	}
	
	public String getMessageAfterUse() {
		return "";
	}
	
}
