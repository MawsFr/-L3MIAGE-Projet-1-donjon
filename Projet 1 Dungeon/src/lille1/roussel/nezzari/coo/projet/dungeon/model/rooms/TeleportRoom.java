package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.Teleporter;

public class TeleportRoom extends Room {
	
	protected Teleporter teleporter;
	
	public TeleportRoom(String name, Dungeon dungeon) {
		this(name, dungeon, null);
	}
	
	public TeleportRoom(String name, Dungeon dungeon, Teleporter teleporter) {
		super(name, dungeon, teleporter);
	}

	@Override
	public void enter() throws CannotBeUsedException {
		teleporter.use();
	}

}
