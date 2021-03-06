package lille1.roussel.nezzari.coo.projet.dungeon.model;

import java.util.LinkedList;

import lille1.roussel.nezzari.coo.projet.dungeon.model.fight.Fight;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.AtkPowerUp;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.Key;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.Potion;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Axe;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Stone;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Sword;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.weapons.Weapon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.ButtonRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.Direction;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.ExitRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.FightRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.NormalRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.RestoreRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.TrapRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.TreasureRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.Button;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.Fountain;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.Treasure;

public class Database {
	protected static Dungeon dungeon1;
	protected static Dungeon dungeon2;
	                
	public static LinkedList<Dungeon> dungeons;
	
	
	public static void initDb() {
		dungeons = new LinkedList<Dungeon>();
		createDungeons();
		createRooms();
		
	}

	protected static void createDungeons() {
		dungeon1 = new Dungeon("Dungeon 1");
		dungeon2 = new Dungeon("Dungeon 2");
		
		dungeons.add(dungeon2);
		dungeons.add(dungeon1);
		
	}
	
	protected static void createRooms() {
		//////////////////////////
		//		Dungeon 1		//
		//////////////////////////
		/*
		 *           5
		 *           |
		 * 		     |
		 * 		 4-2-3-6
		 *         | |
		 *         	|
		 * 			1
		 * 
		 */
		
		Fight fight1 = new Fight();
		
		IRoom dungeon1Entrance = new NormalRoom("entrance", dungeon1);
		IRoom dungeon1room2 = new NormalRoom("intersection", dungeon1);
		IRoom dungeon1Fight1 = new FightRoom("fightRoom", dungeon1, fight1);
		IRoom dungeon1Trap1 = new TrapRoom("trap", dungeon1);
		IRoom dungeon1Exit = new ExitRoom("exit", dungeon1);
		
		Button button1 = new Button("Button", dungeon1Exit);
		IRoom dungeon1room6 = new ButtonRoom("Button room", dungeon1, button1);
		dungeon1Fight1.addNeighbor(Direction.East, dungeon1room6);
		
		dungeon1Entrance.setDescription("You are in the hall of the dungeon");
		dungeon1room2.setDescription("You are at the first intersection");
		dungeon1Fight1.setDescription("You are in the fight room");
		dungeon1Trap1.setDescription("You've fallen in the trap !");
		dungeon1Exit.setDescription("You are outside :D");
		dungeon1room6.setDescription("You are in the button room, try \"push\" command");
		
		
		dungeon1Entrance.addNeighbor(Direction.North, dungeon1room2);
		dungeon1Entrance.addNeighbor(Direction.North, dungeon1Fight1); //should set a link between room 2 and 3
		dungeon1room2.addNeighbor(Direction.East, dungeon1Fight1);
		dungeon1room2.addNeighbor(Direction.West, dungeon1Trap1);
		dungeon1Fight1.addNeighbor(Direction.North, dungeon1Exit);
		
		
		Weapon sword = new Sword("Sword of mana");
		Potion potion = new Potion("Magic Potion");
		
		dungeon1room2.addItemToFind(sword);
		dungeon1room2.addItemToFind(potion);
		
		
		dungeon1.addRoom(
				dungeon1Entrance, 
				dungeon1room2, 
				dungeon1Fight1, 
				dungeon1Trap1, 
				dungeon1Exit
				);
		
		//////////////////////////
		//		Dungeon 2		//
		//////////////////////////
		
		/*
		 			Ex---13
		 			 |    |
		 	   15---14---12---11
		 					   |
		  07---06---05---08   10
		 			 |	  |    |
		       04---02---03---09
		 		     |
		             E
		             
		   E : Entrance
		   02 : normal room
		   03 : button room that unlock room 04
		   04 : normal room containing the key for room 5
		   08 : Godamn it a trap !
		   06 : Fight against the boss to go to the trasure room 07
		   07 : treasure room that unlocks room 9
		   09 : destiny road
		   10 : continuing the destiny road
		   11 : finishing the destiny road
		   12 : a normal room
		   14 : a second normal room
		   15 : button room containing a key : The button unlocks 13, the key unlocks the exit !
		   16 : fountain room restore all HealthPoint !
		   Ex : EXIT !
		   
		 */

		IRoom dungeon2entrance = new NormalRoom("Entrance", dungeon2);
		IRoom dungeon2room2 = new NormalRoom("First intersection", dungeon2);
		
		IRoom dungeon2room4 = new NormalRoom("Key room", dungeon2);
		Button dungeon2room3Button = new Button("Button", dungeon2room4);
		IRoom dungeon2room3 = new ButtonRoom("Button room", dungeon2, dungeon2room3Button);
		
		IRoom dungeon2room5 = new NormalRoom("Second intersection", dungeon2);
		Key dungeon2room5Key = new Key("Intersection key", dungeon2room5);
		dungeon2room4.addItemToFind(dungeon2room5Key);
		
		Weapon axe1 = new Axe("Axe of dwarfs");
		Potion potion1 = new Potion("HP Potion");
		AtkPowerUp atkPowerUp1 = new AtkPowerUp("Atk+");
		Stone stone1 = new Stone("Stone");
		
		dungeon2room5.addItemsToFind(axe1, potion1, atkPowerUp1, stone1);
		
		IRoom dungeon2room6 = new FightRoom("Boss room", dungeon2, new Fight());
		IRoom dungeon2room8 = new TrapRoom("trap", dungeon2);
		IRoom dungeon2room9 = new NormalRoom("Continue Room", dungeon2);
		Treasure dungeon2room9Treasure = new Treasure("Continue Treasure", dungeon2room9);
		IRoom dungeon2room7 = new TreasureRoom("Treasure room", dungeon2, dungeon2room9Treasure);
		
		IRoom dungeon2room10 = new NormalRoom("Continue Room 2", dungeon2);
		IRoom dungeon2room11 = new NormalRoom("Continue Room 3", dungeon2);
		IRoom dungeon2room12 = new NormalRoom("Third intersection", dungeon2);
		IRoom dungeon2room13 = new NormalRoom("Forth intersection", dungeon2);
		Weapon sword2 = new Sword("Sword of Maws");
		dungeon2room13.addItemToFind(sword2);
		
		IRoom dungeon2room14 = new NormalRoom("Fifth intersection", dungeon2);
		Button dungeon2room13Button = new Button("Last button", dungeon2room13);
		IRoom dungeon2room15 = new ButtonRoom("Last button Room", dungeon2, dungeon2room13Button);
		Fountain dungeon2room16Fountain = new Fountain("Fountain of Health");
		IRoom dungeon2room16 = new RestoreRoom("Restore Room", dungeon2, dungeon2room16Fountain);
		IRoom dungeon2Exit = new ExitRoom("exit", dungeon2);
		
		Key exitKey = new Key("Exit key", dungeon2Exit);
		
		dungeon2room15.addItemToFind(exitKey);
		
		///////// DESCRIPTION //////////
		
		dungeon2entrance.setDescription("You are in the hall of the dungeon, welcome boy ! :)");
		dungeon2room2.setDescription("The first room of this dark dungeon, prepare yourself stranger ! ");
		dungeon2room3.setDescription("Use this button, if you want to continue your quest, but does it worth it ? ");
		dungeon2room4.setDescription("You see a key in this room, but why it's here ? ");
		dungeon2room3Button.setMessageAfterUse("You heard something grind on the west, you can investigate if you are ready !" );
		dungeon2room7.setDescription("The treasur room, happy for you ! If you can go out in one piece ! ");
		dungeon2room9Treasure.setMessageAfterUse("You hear a new creaky sound, like the opening of a door, on the south west.");
		dungeon2room9.setDescription("This is the destiny road, the wheel of fate is spinning !");
		dungeon2room10.setDescription("Keep running, it's the best to do here !");
		dungeon2room11.setDescription(" ");
		///////// NEIGHBORS SETTINGS /////////
		
		dungeon2entrance.addNeighbor(Direction.North, dungeon2room2);
		dungeon2room2.addNeighbor(Direction.East, dungeon2room3);
		dungeon2room2.addNeighbor(Direction.West, dungeon2room4);
		dungeon2room2.addNeighbor(Direction.North, dungeon2room5);
		
		
		dungeon2room5.addNeighbor(Direction.East, dungeon2room8);
		dungeon2room8.addNeighbor(Direction.South, dungeon2room3);
		
		dungeon2room5.addNeighbor(Direction.West, dungeon2room6);
		dungeon2room6.addNeighbor(Direction.West, dungeon2room7);
		
		dungeon2room3.addNeighbor(Direction.East, dungeon2room9);
		dungeon2room9.addNeighbor(Direction.North, dungeon2room10);
		dungeon2room10.addNeighbor(Direction.North, dungeon2room11);
		dungeon2room11.addNeighbor(Direction.West, dungeon2room12);
		
		dungeon2room12.addNeighbor(Direction.North, dungeon2room13);
		dungeon2room12.addNeighbor(Direction.West, dungeon2room14);
		dungeon2room13.addNeighbor(Direction.West, dungeon2Exit);
		dungeon2room14.addNeighbor(Direction.North, dungeon2Exit);
		
		dungeon2room14.addNeighbor(Direction.West, dungeon2room15);
		dungeon2room7.addNeighbor(Direction.North, dungeon2room16);
		dungeon2.addRoom(
				dungeon2entrance, 
				dungeon2room2, 
				dungeon2room3, 
				dungeon2room4, 
				dungeon2room5, 
				dungeon2room6, 
				dungeon2room7, 
				dungeon2room8, 
				dungeon2room9, 
				dungeon2room10, 
				dungeon2room11, 
				dungeon2room12, 
				dungeon2room13, 
				dungeon2room14, 
				dungeon2room15, 
				dungeon2room16,
				dungeon2Exit
				);
		
		
		
		
		
		
		
		
	}

}
