package lille1.roussel.nezzari.coo.projet.dungeon.model.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.IBagItem;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Weapon;

public class Bag {
	
	//may add weight points to carry item
	protected List<IBagItem> items;
	
	public Bag() {
		this.items = new ArrayList<IBagItem>();
	}
	
	public List<IBagItem> getItems() {
		return items;
	}
	
	public void addItem(IBagItem item) {
		this.items.add(item);
	}
	
	public void addAllItems(Collection<IBagItem> items) {
		this.items.addAll(items);
	}
	
	public void removeItem(IBagItem item) {
		if(this.items.contains(item)) {
			if(item.isRemovedWhenUsed()) {
				this.items.remove(item);
			}
		}
	}
	
	public void removeAllItems() {
		this.items.clear();
	}
	
	public boolean isEmpty() {
		return this.items.isEmpty();
	}

	public List<Weapon> getWeapons() {
		List<Weapon> weapons = new ArrayList<Weapon>();
		for(IBagItem item : items) {
			if(item.isWeapon()) {
				weapons.add((Weapon) item);
			}
		}
		
		return weapons;
	}
	
	
	@Override
	public String toString() {
		String s = "";
		
		List<Weapon> weapons = getWeapons();
		List<IBagItem> items = getItems();
		items.removeAll(weapons);
		
		if(items.isEmpty()) {
			s += "You don't have normal objects.";
		} else {
			s += "#####\tNORMAL ITEMS\t#####";
			for(int i = 0; i < items.size(); ++i) {
				s += "- " + items.get(i);
			}
			
		}
		
		s += "\n";

		if(weapons.isEmpty()) {
			s += "You don't have any weapons.";
		} else {
			s += "#####\tWEAPONS\t#####";

			for(int i = 0; i < weapons.size(); ++i) {
				s += "- " + weapons.get(i) + ((weapons.get(i).isEquipedToPlayer()) ? " (equiped)" : "");
			}
		}

		s += "\n";
		
		return s;
	}
}
