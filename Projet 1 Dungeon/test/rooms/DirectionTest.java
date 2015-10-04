package rooms;

import static org.junit.Assert.assertEquals;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.Direction;

import org.junit.Test;

public class DirectionTest {

	@Test
	public void getOppositeTest() {
		assertEquals(Direction.North, Direction.getOpposite(Direction.South));
		assertEquals(Direction.South, Direction.getOpposite(Direction.North));
		assertEquals(Direction.West, Direction.getOpposite(Direction.East));
		assertEquals(Direction.East, Direction.getOpposite(Direction.West));
		assertEquals(Direction.Up, Direction.getOpposite(Direction.Down));
		assertEquals(Direction.Down, Direction.getOpposite(Direction.Up));
		
		
	}
	
	@Test
	public void getDirectionTest() {
		assertEquals(Direction.North, Direction.get("north"));
		assertEquals(Direction.West, Direction.get("west"));
		assertEquals(Direction.South, Direction.get("south"));
		assertEquals(Direction.East, Direction.get("east"));
		assertEquals(Direction.Up, Direction.get("up"));
		assertEquals(Direction.Down, Direction.get("down"));
		
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void getBadDirectionTest() {
		Direction.get("hello");
	}
	
	
}
