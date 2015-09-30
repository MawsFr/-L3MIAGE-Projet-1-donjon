package lille1.roussel.nezzari.coo.projet.dungeon.model.fight;

public interface IFighter {

	public void attack(IFighter fighter);
	public void increaseHealthPoints(int hp);
	public void decreaseHealthPoints(int hp);
	public boolean isDead();
	public int getHealthPoints();
	public void setHealthPoints(int healthPoints);
	public int getDamagePoints();
	public void setDamagePoints(int damagePoints);
	public void inscreaseDamagePoints(int by);
	public void decreaseDamagePoints(int by);
	
	
	
	
	
}
