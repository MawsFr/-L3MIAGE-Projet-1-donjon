package lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons;

import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game.GAMES_STATES;
import lille1.roussel.nezzari.coo.projet.dungeon.model.fight.Fight;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.OneUseObjects;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.FightRoom;

public class Stone extends OneUseObjects {

	public Stone(String name) {
		super(name);
	}
	
	@Override
	public void useInFight() {
		throwStone();
	}
	
	protected void throwStone() {
		if(Game.getInstance().getCurrentState() == GAMES_STATES.INFIGHTSTATE) {
			Fight currentFight = ((FightRoom) Game.getInstance().getCurrentDungeon().getCurrentRoom()).getFight();
			currentFight.damageFighter(currentFight.getMonster(), getDamage());
		}
	}

	@Override
	public String getMessageAfterUse() {
		return "You've thrown a stone on your ennemy !";
	}
	
	@Override
	public int getDamage() {
		return 5;
	}
	
	

}
