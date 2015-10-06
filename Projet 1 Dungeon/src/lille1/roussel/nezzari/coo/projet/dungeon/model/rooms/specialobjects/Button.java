package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;

public class Button extends SpecialObject {
	
	protected IRoom roomWhereTheButtonIs;
	
	public Button(String name, IRoom roomToSetVisible) {
		super(name, roomToSetVisible);
		this.associatedRoom.setVisible(false);
		this.messageAfterUse = "You pushed the Button !";
	}
	
	public void use() throws CannotBeUsedException {
		if(!used) {
			this.associatedRoom.setVisible(true);
			super.use();
		} else {
			throw new CannotBeUsedException(name + " has already used  !");
		}
	}
	
	public void setRoomWhereTheButtonIs(IRoom roomWhereTheButtonIs) {
		this.roomWhereTheButtonIs = roomWhereTheButtonIs;
	}
	
	public IRoom getRoomWhereTheButtonIs() {
		return roomWhereTheButtonIs;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
