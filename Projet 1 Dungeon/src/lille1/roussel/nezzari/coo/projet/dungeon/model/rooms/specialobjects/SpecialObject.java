package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;

public abstract class SpecialObject implements ISpecialObject {
	
	protected String name;
	protected IRoom associatedRoom;
	protected boolean used;
	protected String messageAfterUse;
	
	public SpecialObject() {
	}
	
	public SpecialObject(String name, IRoom associatedRoom) {
		this.associatedRoom = associatedRoom;
	}
	
	public void use() throws CannotBeUsedException {
		this.used = true;
	}
	
	public boolean isUsed() {
		return used;
	}

	public IRoom getAssociatedRoom() {
		return this.associatedRoom;
	}
	
	public void setAssociatedRoom(IRoom room) {
		this.associatedRoom = room;
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMessageAfterUse() {
		return messageAfterUse;
	}
	
	public void setMessageAfterUse(String messageAfterUse) {
		this.messageAfterUse = messageAfterUse;
	}
	
	
	

}
