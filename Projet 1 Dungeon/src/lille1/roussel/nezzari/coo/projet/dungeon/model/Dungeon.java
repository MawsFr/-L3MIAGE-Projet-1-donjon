package lille1.roussel.nezzari.coo.projet.dungeon.model;

import java.util.ArrayList;
import java.util.List;

import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;

public class Dungeon {

	protected String name;
	protected List<IRoom> rooms;
	protected IRoom currentRoom;
	protected boolean isInitialized;
	
	public Dungeon(String name) {
		if(name == null) {
			throw new IllegalArgumentException("You must specify a name for your Dungeon");
		}
		
		if(name.isEmpty()) {
			throw new IllegalArgumentException("You must specify a non-empty name for your Dungeon");
		}
		
		this.name = name;
		this.rooms = new ArrayList<IRoom>();
	}
	
	public void init() throws NullPointerException {
		for(IRoom room : rooms) {
			if(room.getName().equalsIgnoreCase("entrance")) {
				room.setVisited(true);
				setCurrentRoom(room);
				isInitialized = true;
			}
		}
		
		if(!isInitialized) {
			throw new NullPointerException("There is no entrance in " + this);
		}
	}
	
	public IRoom getCurrentRoom() {
		return currentRoom;
	}
	
	public void setCurrentRoom(IRoom currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public void addRoom(IRoom room) {
		this.rooms.add(room);
	}
	
	public void addRoom(IRoom ... rooms) {
		for(IRoom room : rooms) {
			addRoom(room);
		}
	}
	
	public boolean isInitialized() {
		return isInitialized;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
