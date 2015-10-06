package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.fight.Fight;

public class FightRoom extends Room {

	protected Fight fight;

	public FightRoom(String name, Dungeon dungeon, Fight fight) {
		super(name, dungeon);
		this.fight = fight;
	}	

	@Override
	public void enter() throws CannotBeUsedException {
		super.enter();
		if(!fight.isFinished()) {
			fight.begin();
		}
		
	}

	public Fight getFight() {
		return fight;
	}

	public void setFight(Fight fight) {
		this.fight = fight;
	}


}
