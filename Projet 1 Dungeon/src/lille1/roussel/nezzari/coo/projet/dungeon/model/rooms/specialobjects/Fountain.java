package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;

public class Fountain extends SpecialObject {

	public Fountain(String name) {
		super(name, null);
		messageAfterUse = "You feel restored full of power !!!";
	}
	
	@Override
	public void use() throws CannotBeUsedException {
		Game.getInstance().getPlayer().setHealthPoints(Game.getInstance().getPlayer().MAX_HEALTH_POINTS);
	}
	
	
	

}
