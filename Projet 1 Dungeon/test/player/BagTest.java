package player;

import static org.junit.Assert.*;

import java.util.List;

import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Bag;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.IBagItem;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.Potion;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Lance;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Sword;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Weapon;

import org.junit.Before;
import org.junit.Test;

public class BagTest {

	protected Bag bag;
	private Potion potion1, potion2;
	private Sword sword;
	private Lance lance;

	@Before
	public void setUp() throws Exception {
		bag = new Bag();
		potion1 = new Potion("Potion");
		potion2 = new Potion("Potion1");
		sword = new Sword("Sword");
		lance = new Lance("Lance");

		bag.addItem(potion1);
		bag.addItem(potion2);
		bag.addItem(sword);
		bag.addItem(lance);
	}

	@Test
	public void testgetWeapons() {

		List<Weapon> weapons = bag.getWeapons();
		assertTrue(weapons.contains(sword));
		assertTrue(weapons.contains(lance));

		bag.getItems().removeAll(weapons);

		assertTrue(bag.getItems().contains(potion1));
		assertTrue(bag.getItems().contains(potion2));

	}

//	@Test
//	public void toStringEmptyBagTest() {
//		bag.removeAllItems();
//		assertEquals("You don't have normal object.\nYou don't have any weapons.", bag);
//
//	}
//
//
//	@Test
//	public void toStringTest() {
//		String s = "";
//
//		List<Weapon> weapons = bag.getWeapons();
//		List<IBagItem> items = bag.getItems();
//		items.removeAll(weapons);
//
//		s += "#####\tNORMAL ITEMS\t#####";
//		for(int i = 0; i < items.size(); ++i) {
//			s += "- " + items.get(i);
//		}
//
//		s += "\n";
//
//		s += "#####\tWEAPONS\t#####";
//
//		for(int i = 0; i < weapons.size(); ++i) {
//			s += "- " + weapons.get(i) + ((weapons.get(i).isEquipedToPlayer()) ? " (equiped)" : "");
//		}
//		
//		assertEquals(s, bag);
//	}




}
