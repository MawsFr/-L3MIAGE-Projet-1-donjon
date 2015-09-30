package lille1.roussel.nezzari.coo.projet.dungeon.model.fight;

public abstract class Monster extends AbstractFighter {

	public Monster() {
		this("Monster", 150, 100);
	}
	
	public Monster(String name, int healthPoints, int damagePoints) {
		super(name, healthPoints, damagePoints);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getDamagePoints() {
		return damagePoints;
	}
	
	public void setDamagePoints(int damagePoints) {
		this.damagePoints = damagePoints;
	}
	
}
