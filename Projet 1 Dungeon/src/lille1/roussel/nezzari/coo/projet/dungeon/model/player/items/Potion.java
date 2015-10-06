package lille1.roussel.nezzari.coo.projet.dungeon.model.player.items;

import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.fight.AbstractFighter;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Player;

public class Potion extends BagItem {
	
	protected int healthPointsToRestore;
	
	public Potion(String name) {
		this(name, -1);
	}

	public Potion(String name, int healthPointsToRestore) {
		super(name);
		this.healthPointsToRestore = healthPointsToRestore;
	}
	
	public void use() {
		restorePlayerEnergy();
	}

	protected void restorePlayerEnergy() {
		if(healthPointsToRestore == -1) {
			Game.getInstance().getPlayer().setHealthPoints(Game.getInstance().getPlayer().MAX_HEALTH_POINTS);
		} else {
			Game.getInstance().getPlayer().setHealthPoints(healthPointsToRestore);
		}
	}
	
	
	
	public void useInFight() {
		restorePlayerEnergy();
		
	}
	
	public String getMessageAfterUse() {
		return "Your Healthpoints have been restored !";
	}
	
	public int getHealthPointsToRestore() {
		return healthPointsToRestore;
	}
	
	public void setHealthPointsToRestore(int healthPointsToRestore) {
		this.healthPointsToRestore = healthPointsToRestore;
	}
	

}
