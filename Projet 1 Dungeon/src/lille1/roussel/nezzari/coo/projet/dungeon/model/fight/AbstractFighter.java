package lille1.roussel.nezzari.coo.projet.dungeon.model.fight;

public abstract class AbstractFighter implements IFighter {
	
	protected String name;
	protected int healthPoints;
	protected int damagePoints;
	
	public final int MAX_HEALTH_POINTS;
	
	public AbstractFighter(String name, int healthPoints, int damagePoints) {
		this.name = name;
		this.healthPoints = healthPoints;
		this.damagePoints = damagePoints;
		this.MAX_HEALTH_POINTS = healthPoints;
	}

	public void attack(IFighter fighter) {
		fighter.decreaseHealthPoints(damagePoints);
		
	}
	
	public boolean isDead() {
		return healthPoints <= 0;
	}
	
	public void increaseHealthPoints(int hp) {
		this.healthPoints += hp;
	}
	
	public void decreaseHealthPoints(int hp) {
		this.healthPoints -= hp;
		
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}
	
	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	
	public int getDamagePoints() {
		return damagePoints;
	}
	
	public void setDamagePoints(int damagePoints) {
		this.damagePoints = damagePoints;
	}
	
	public void decreaseDamagePoints(int by) {
		this.damagePoints -= by;
	}
	public void inscreaseDamagePoints(int by) {
		this.damagePoints += by;
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name + " | HP : " + healthPoints;
	}

}
