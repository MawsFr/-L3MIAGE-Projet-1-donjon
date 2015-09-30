package lille1.roussel.nezzari.coo.projet.dungeon.model;

import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Player;


public class Game {
	
	public enum GAMES_STATES {
		NAMEINPUTSTATE, PLAYSTATE, FIGHTSTATE, WONGAMESTATE, LOSTGAMESTATE, CHANGEDUNGEONSTATE;
	}
	
	protected static Game instance;
	
	protected Dungeon currentDungeon;
	protected Player player;
	protected boolean isFinished;
	
	protected GAMES_STATES currentState;
	
	private Game() {}
	
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}
		
		return instance;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GAMES_STATES getCurrentState() {
		return currentState;
	}

	public void setCurrentState(GAMES_STATES currentState) {
		this.currentState = currentState;
	}
	
	public Dungeon getCurrentDungeon() {
		return currentDungeon;
	}
	
	public void setCurrentDungeon(Dungeon currentDungeon) throws NullPointerException {
		this.currentDungeon = currentDungeon;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	public boolean isFinished() {
		return isFinished;
	}
	
	@Override
	public String toString() {
		return "Current dungeon : " + currentDungeon + "\nCurrent room : " + currentDungeon.currentRoom;
	}
	
	

}
