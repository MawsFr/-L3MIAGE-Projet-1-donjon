package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;

public class Teleporter extends SpecialObject {

	public Teleporter(String name, IRoom associatedRoom) {
		super(name, associatedRoom);
		messageAfterUse = "You have been teleported to " + associatedRoom;
	}
	
	@Override
	public void use() throws CannotBeUsedException {
		if(associatedRoom.isLocked()) {
			throw new CannotBeUsedException("you cannot be teleported to " + associatedRoom + ", you have to unlock it first !");
		}
		
		if(!associatedRoom.isVisible()) {
			throw new CannotBeUsedException("You cannot use this teleporter yet !");
		}
		
		Game.getInstance().getCurrentDungeon().setCurrentRoom(associatedRoom);
	}
	
	

}
