package lille1.roussel.nezzari.coo.projet.dungeon.model.player.items;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.AlreadyUnlockedRoomException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;

public class Key extends BagItem {
	
	protected IRoom associatedRoom;

	public Key(String name, IRoom associatedRoom) {
		super(name);
		this.associatedRoom = associatedRoom;
		this.associatedRoom.lock();
		usableInFight = false;
		removedWhenUsed = true;
	}

	public void use() throws CannotBeUsedException, AlreadyUnlockedRoomException {
		if(!Game.getInstance().getCurrentDungeon().getCurrentRoom().isNeighborOf(associatedRoom)) {
			throw new CannotBeUsedException("You need to be in front of the corresponding door to open it with this" + this.name);
		} else {
			associatedRoom.unlock();
		}
		
	}
	
	public String getMessageAfterUse() {
		return associatedRoom.getName() + " is unlocked";
	}
	
}
