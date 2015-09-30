package lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons;

public class Sword extends Weapon {

	public Sword(String name) {
		super(name);
	}

	@Override
	public int getDamage() {
		return 30;
	}

}
