package lille1.roussel.nezzari.coo.projet.dungeon.model.player.items;

import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Player;

public class Potion extends BagItem {

	public Potion(String name) {
		super(name);
	}

	public void use() {
		restorePlayerEnergy();
	}

	protected void restorePlayerEnergy() {
		Player player = Game.getInstance().getPlayer();
		player.setHealthPoints(player.MAX_HEALTH_POINTS);
	}
	
	
	
	public void useInFight() {
		restorePlayerEnergy();
		
	}
	
	public String getMessageAfterUse() {
		return "Your Healthpoints have been restored !";
	}
	

}
