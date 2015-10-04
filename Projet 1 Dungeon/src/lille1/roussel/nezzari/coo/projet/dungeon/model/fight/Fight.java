package lille1.roussel.nezzari.coo.projet.dungeon.model.fight;

import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game.GAMES_STATES;
import lille1.roussel.nezzari.coo.projet.dungeon.model.fight.monsters.Globulo;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Player;

public class Fight {
	
	
	protected Monster monster;
	protected Player player;
	protected boolean isFinished;
	protected IFighter winner;
	
	public Fight() {
		this(new Globulo());
	}
	
	public Fight(Monster monster) {
		this.monster = monster;
		
	}
	
	public void begin() {
		//insert command here
		this.player = Game.getInstance().getPlayer();
		Game.getInstance().setCurrentState(GAMES_STATES.INFIGHTSTATE);
		
	}
	
	public void damagePlayer() {
		monster.attack(player);
		checkFightState();
	}
	
	public void damageFighter(IFighter fighter, int damage) {
		fighter.decreaseHealthPoints(damage);
		checkFightState();
	}
	
	public void damageMonster() {
		player.attack(monster);
		checkFightState();
		
	}
	
	public void checkFightState() {
		//TODO: manage draw ?
		if(player.isDead()) {
			if(!monster.isDead()) {
				this.winner = monster;
			}
			
			end();
		}
		
		if(monster.isDead()) {
			if(!player.isDead()) {
				this.winner = player;
			}
			end();
		}
		
	}

	public void end() {
		this.isFinished = true;
	}
	
	public boolean isFinished() {
		return isFinished;
	}
	
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Monster getMonster() {
		return monster;
	}
	
	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public int executeMonsterAction() {
		int damage = monster.getDamagePoints();
		int randomDamage = (int) ((damage * 75 / 100) + (Math.random() * (damage - (damage * 75 / 100))));
		damageFighter(player, randomDamage);
		return randomDamage;
	}

	public IFighter getWinner() {
		return winner;
	}
	
	public void setWinner(IFighter winner) {
		this.winner = winner;
	}
	
	@Override
	public String toString() {
		return "Monster : " + monster + "\n" + player;
	}
	
}
