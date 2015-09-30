package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms;

import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game.GAMES_STATES;

public class ExitRoom extends Room {

	public ExitRoom(String name, Dungeon dungeon) {
		super(name, dungeon);
	}

	@Override
	public void enter() {
		exitDungeon();

	}

	private void exitDungeon() {
		Game.getInstance().setCurrentState(GAMES_STATES.CHANGEDUNGEONSTATE);
	}

}
