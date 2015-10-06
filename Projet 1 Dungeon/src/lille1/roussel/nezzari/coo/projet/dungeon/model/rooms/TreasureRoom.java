package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms;

import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.Treasure;

public class TreasureRoom extends Room {

	public TreasureRoom(String name, Dungeon dungeon) {
		this(name, dungeon, null);
	}
	
	public TreasureRoom(String name, Dungeon dungeon, Treasure treasure) {
		super(name, dungeon, treasure);
	}
	
	public Treasure getTreasure() {
		return (Treasure) this.specialObject;
	}

}
