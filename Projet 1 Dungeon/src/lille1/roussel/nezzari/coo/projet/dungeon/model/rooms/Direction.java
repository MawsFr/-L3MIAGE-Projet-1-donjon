package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms;

public enum Direction {
	North("north"), South("south"), West("west"), East("east"), Up("up"), Down("down");

	protected String name;

	Direction(String name) {
		this.name = name;
	}

	public static Direction getOpposite(Direction direction) {
		switch(direction) {
		case Down: return Up;
		case East: return West;
		case North: return South;
		case South: return North;
		case Up: return Down;
		case West: return East;
		default: return direction;
		}
		
	}

	public String getName() {
		return name;
	}

	public static Direction get(String name) throws IllegalArgumentException {
		for(Direction direction : Direction.values()) {
			if(name.equals(direction.name)) {
				return direction;
			}
		}

		throw new IllegalArgumentException("This direction doesn't exist");
	}
}