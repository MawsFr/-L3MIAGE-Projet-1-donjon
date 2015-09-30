package lille1.roussel.nezzari.coo.projet.dungeon.model.player.items;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.fight.IFighter;

public class AtkPowerUp extends OneUseObjects {

	public AtkPowerUp(String name) {
		super(name);
	}
	
	@Override
	public void use() throws CannotBeUsedException {
		((IFighter) Game.getInstance().getPlayer()).inscreaseDamagePoints(this.getDamage());
	}
	
	@Override
	public void useInFight() {
		((IFighter) Game.getInstance().getPlayer()).inscreaseDamagePoints(this.getDamage());
	}
	
	@Override
	public String getMessageAfterUse() {
		return "Your attack has been powered up !";
	}
	
	@Override
	public int getDamage() {
		return 10;
	}
	
}
