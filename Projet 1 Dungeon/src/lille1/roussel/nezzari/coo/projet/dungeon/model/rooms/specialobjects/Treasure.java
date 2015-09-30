package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;

public class Treasure extends SpecialObject {
	
	public Treasure(String name, IRoom associatedRoom) {
		super(name, associatedRoom);
		this.associatedRoom.setVisible(false);
		this.messageAfterUse = "You opened the Treasure !";
	}
	
	public void use() throws CannotBeUsedException {
		if(!used) {
			this.associatedRoom.setVisible(true);
			super.use();
		} else {
			//throw exception already opened treasure
		}
	}
	
	@Override
	public String getMessageAfterUse() {
		return "You got the trasure ! " + associatedRoom + " is now visible and unlocked";
	}

}
