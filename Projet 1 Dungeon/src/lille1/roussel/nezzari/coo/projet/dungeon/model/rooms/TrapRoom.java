package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game.GAMES_STATES;

public class TrapRoom extends Room {

	public TrapRoom(String name, Dungeon dungeon) {
		super(name, dungeon);
	}

	public void enter() throws CannotBeUsedException {
		super.enter();
		looseGame();
		
	}

	protected void looseGame() {
		Game.getInstance().setCurrentState(GAMES_STATES.LOSTGAMESTATE);
	};

}
