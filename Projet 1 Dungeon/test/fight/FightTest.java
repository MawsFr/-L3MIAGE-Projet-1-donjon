package fight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.EquipementException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game.GAMES_STATES;
import lille1.roussel.nezzari.coo.projet.dungeon.model.fight.Fight;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Player;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Sword;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Weapon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FightTest {

	protected Game game;
	protected Player player;
	protected Fight fight;
	protected Dungeon dungeon;
	
	
	@Before
	public void setUp() throws Exception {
		player = new Player("Maws");
		game = Game.getInstance();
		game.setPlayer(player);
		fight = new Fight();
		game.setCurrentState(GAMES_STATES.INGAMESTATE);
	}
	
	@Test
	public void gameFightStateTest() {
		fight.begin();
		assertEquals(GAMES_STATES.INFIGHTSTATE, game.getCurrentState());
	}
	
	@Test
	public void fightIsNotFinishedTest() {
		fight.begin();
		fight.checkFightState();
		assertFalse(fight.isFinished());
	}
	
	@Test
	public void playerLostFightTest() {
		fight.begin();
		fight.damageFighter(player, player.getHealthPoints());
		assertTrue(fight.isFinished());
		assertEquals(fight.getWinner(), fight.getMonster());
		assertTrue(fight.getMonster().getHealthPoints() > 0);
		assertTrue(player.isDead());
	}
	
	@Test
	public void MonsterLostFightTest() {
		fight.begin();
		fight.damageFighter(fight.getMonster(), fight.getMonster().getHealthPoints());
		assertTrue(fight.isFinished());
		assertEquals(fight.getWinner(), player);
		assertTrue(player.getHealthPoints() > 0);
		assertTrue(fight.getMonster().isDead());
	}
	
	@Test
	public void damagePlayerTest() {
		fight.begin();
		int hp = player.getHealthPoints();
		fight.damagePlayer();
		assertEquals(player.getHealthPoints(), hp - fight.getMonster().getDamagePoints());
	}
	
	@Test
	public void damageMonsterTest() {
		fight.begin();
		int hp = fight.getMonster().getHealthPoints();
		fight.damageMonster();
		assertEquals(fight.getMonster().getHealthPoints(), hp - player.getDamagePoints());
	}
	
	@Test
	public void damageMonsterWithWeaponsTest() throws EquipementException {
		fight.begin();
		Weapon sword = new Sword("Sword of mana");
		Weapon axe = new Sword("Axe of dwarf");
		player.equipWeapon(sword);
		player.equipWeapon(axe);
		int hp = fight.getMonster().getHealthPoints();
		fight.damageMonster();
		player.unequipWeapon(axe);
		player.unequipWeapon(sword);
		assertEquals(fight.getMonster().getHealthPoints(), hp - player.getDamagePoints() - sword.getDamage() - axe.getDamage());
	}
	
	@Test
	public void executeMonsterActionTest() {
		fight.begin();
		int hp = player.getHealthPoints();
		int randomDamage = fight.executeMonsterAction();
		assertEquals(player.getHealthPoints(), hp - randomDamage);
		
	}
	
	
	

	@After
	public void tearDown() throws Exception {
	}


}
