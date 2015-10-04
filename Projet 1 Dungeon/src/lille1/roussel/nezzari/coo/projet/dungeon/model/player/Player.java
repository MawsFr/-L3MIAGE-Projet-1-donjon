package lille1.roussel.nezzari.coo.projet.dungeon.model.player;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.EquipementException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.fight.AbstractFighter;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Weapon;

public class Player extends AbstractFighter {
	
	protected Bag bag;
	protected Weapon rightHandWeapon;
	protected Weapon leftHandWeapon;
	
	public Player(String name) {
		this(name, 300, 100, null, null);
	}

	public Player(String name, int healthPoints, int damagePoints, Weapon rightHandWeapon, Weapon leftHandWeapon) {
		super(name, healthPoints, damagePoints);
		this.rightHandWeapon = rightHandWeapon;
		this.leftHandWeapon = leftHandWeapon;
		this.bag = new Bag();

	}

	public Bag getBag() {
		return bag;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name + "\nHP : " + healthPoints + " | ATK : " + this.damagePoints + " | Right hand Weapon : " + ((rightHandWeapon != null) ? rightHandWeapon : "No weapon") + " | Left hand weapon : " + ((leftHandWeapon != null) ? leftHandWeapon : "No weapon");
	}
	
	public Weapon getRightHandWeapon() {
		return rightHandWeapon;
	}
	
	public Weapon getLeftHandWeapon() {
		return leftHandWeapon;
	}

	public boolean canCarryNewWeapon() {
		return this.leftHandWeapon == null || this.rightHandWeapon == null;
	}
	
	protected void equipWeaponToRightHand(Weapon weapon) {
		this.rightHandWeapon = weapon;
	}
	
	protected void equipWeaponToLeftHand(Weapon weapon) {
		this.leftHandWeapon = weapon;
	}

	public void equipWeapon(Weapon weapon) throws EquipementException {
		if(!canCarryNewWeapon()) {
			throw new EquipementException("You cannot carry more weapons !");
		}
		
		if(this.rightHandWeapon == null) {
			this.rightHandWeapon = weapon;
		} else if(this.leftHandWeapon == null) {
			this.leftHandWeapon = weapon;
		}
		
		this.inscreaseDamagePoints(weapon.getDamage());
	}

	public void unequipWeapon(Weapon weapon) throws EquipementException {
		if(!isEquipedOf(weapon)) {
			throw new EquipementException("You don't carry this weapon !");
		}
		
		if(this.rightHandWeapon == weapon) {
			this.rightHandWeapon = null;
		} else if(this.leftHandWeapon == weapon) {
			this.leftHandWeapon = null;
		}
		
		this.decreaseDamagePoints(weapon.getDamage());
	}
	
	public boolean isEquipedOf(Weapon weapon) {
		return this.rightHandWeapon == weapon || this.leftHandWeapon == weapon;
	}
	
	public boolean hasWeaponsEquiped() {
		return this.rightHandWeapon != null || this.leftHandWeapon != null;
	}
	
	
	
	
}
