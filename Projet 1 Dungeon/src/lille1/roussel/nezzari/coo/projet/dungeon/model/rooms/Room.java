package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.AlreadyUnlockedRoomException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.IBagItem;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.ISpecialObject;

public abstract class Room implements IRoom {

	protected Dungeon dungeon;
	protected String name;
	protected String description;
	protected boolean visible;
	protected boolean locked;
	protected Map<Direction, List<IRoom>> neighbors;
	protected List<IBagItem> itemsToFind;
	protected ISpecialObject specialObject; //An object call when the user describes this room. It can unlock another
	protected boolean visited;


	public Room(String name, Dungeon dungeon) {
		this(name, dungeon, true, false, null);
	}

	public Room(String name, Dungeon dungeon, ISpecialObject specialObject) {
		this(name, dungeon, true, false, specialObject);
	}

	public Room(String name, Dungeon dungeon, boolean visible, boolean locked, ISpecialObject specialObject) {
		setName(name);
		setDungeon(dungeon);
		this.itemsToFind = new ArrayList<IBagItem>();
		this.neighbors = new HashMap<Direction, List<IRoom>>();
		this.neighbors.put(Direction.North, new ArrayList<IRoom>());
		this.neighbors.put(Direction.East, new ArrayList<IRoom>());
		this.neighbors.put(Direction.South, new ArrayList<IRoom>());
		this.neighbors.put(Direction.West, new ArrayList<IRoom>());
		this.neighbors.put(Direction.Up, new ArrayList<IRoom>());
		this.neighbors.put(Direction.Down, new ArrayList<IRoom>());
		this.visible = visible;
		this.locked = locked;
		this.specialObject = specialObject;
	}



	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#enter()
	 */
	public abstract void enter() throws CannotBeUsedException;

	public boolean findAllItems() throws CannotBeUsedException, AlreadyUnlockedRoomException {
		if(itemsToFind.size() <= 0) {
			return false;
		}

		for(IBagItem item : this.itemsToFind) {
			item.addToBag();
			if(item.usedWhenFound()) {
				item.use();
				if(item.isRemovedWhenUsed()) {
					item.removeFromBag();
				}
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#addNeighbor(lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.Direction, lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.Room)
	 */
	public void addNeighbor(Direction direction, IRoom neighbor) throws IllegalArgumentException {
		if(neighbor == null) {
			throw new IllegalArgumentException("You must specify a neighbor to add !");
		}

		if(neighbor == this) {
			throw new IllegalArgumentException("A room cannot be its neighbor !");
		}

		if(!this.neighbors.get(direction).contains(neighbor)) 
			this.neighbors.get(direction).add(neighbor);
		if(!neighbor.getNeighbors().get(Direction.getOpposite(direction)).contains(this))
			neighbor.getNeighbors().get(Direction.getOpposite(direction)).add(this); //add this to neighbor in the opposite direction
	}


	public void addNeighbors(Direction direction, List<IRoom> neighbors) throws IllegalArgumentException {
		for(IRoom room : neighbors) {
			addNeighbor(direction, room);
		}
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#getNeighbors(lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.Direction)
	 */
	public List<IRoom> getNeighbors(Direction direction) {
		return this.neighbors.get(direction);

	}

	public Map<Direction, List<IRoom>> getNeighbors() {
		return this.neighbors;
	}


	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#addItemToFind(lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.IBagItem)
	 */
	public void addItemToFind(IBagItem item) throws IllegalArgumentException{
		if(item == null) {
			throw new IllegalArgumentException("You must specify an item to add !");
		}
		this.itemsToFind.add(item);
	}

	public void addItemsToFind(IBagItem... items) throws IllegalArgumentException{
		for(IBagItem item : items) {
			addItemToFind(item);
		}
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#unclock()
	 */
	public void unlock() throws AlreadyUnlockedRoomException {
		if(!this.locked) {
			throw new AlreadyUnlockedRoomException("This room is already unlocked !");
		}

		this.locked = false;
	}


	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#setName(java.lang.String)
	 */
	public void setName(String name) throws IllegalArgumentException {
		if(name == null) {
			throw new IllegalArgumentException("You must specify a name !");
		}

		if(name.isEmpty()) {
			throw new IllegalArgumentException("You must specify a non-empty name !");
		}

		this.name = name;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#getDescription()
	 */
	public String getDescription() throws CannotBeUsedException {
		return description;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#toString()
	 */
	@Override
	public String toString() {
		String s = "";
		//		return this.name;
		
		
		if(this.hasNeighbor(Direction.North)) {
			int nbNorthNeighbors = this.neighbors.get(Direction.North).size();
			if(this.hasNeighbor(Direction.East) && this.hasNeighbor(Direction.West)) {
				s += "\n------[" + nbNorthNeighbors + "]------.";
				
			} else {
				if(this.hasNeighbor(Direction.West)) {
					s += ".";
				} else {
					s += ".-";
				}
				s += "-----[" + nbNorthNeighbors + "]-----";
				if(this.hasNeighbor(Direction.East)) {
					s += ".";
				} else {
					s += "-.";
				}
			}
		} else {
			s += "\n .-------------.";
		}

		if(this.neighbors.get(Direction.West).size() == 0) {
			s += "\n|\t\t|";
			s += "\n|\t\t|";
			s += "\n|\tYou";
		} else {
			s += "\n |\t\t|";
			s += "\n |\t\t|";
			s += "\n[" + this.neighbors.get(Direction.West).size() + "]\tYou";
		}
		
		if(this.neighbors.get(Direction.East).size() == 0) {
			s += "\t|";
			s += "\n|\t\t|";
			s += "\n|\t\t|";
		} else {
			s += "\t[" + this.neighbors.get(Direction.East).size() + "]";
			s += "\n |\t\t|";
			s += "\n |\t\t|";
		}
		
		
		
		if(this.hasNeighbor(Direction.South)) {
			int nbNorthNeighbors = this.neighbors.get(Direction.South).size();
			if(this.hasNeighbor(Direction.East) && this.hasNeighbor(Direction.West)) {
				s += "\n";
			} else {
				if(this.hasNeighbor(Direction.West)) {
					s += ".";
				} else {
					s += ".-";
				}
				s += "-----[" + nbNorthNeighbors + "]-----";
				if(this.hasNeighbor(Direction.East)) {
					s += ".";
				} else {
					s += "-.";
				}
			}
		} else {
			s += "\n .-------------.";
		}
		

		return s;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#isVisible()
	 */
	public boolean isVisible() {
		return visible;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#lock()
	 */
	public void lock() {
		this.locked = true;

	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#isLocked()
	 */
	public boolean isLocked() {
		return locked;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#getSpecialObject()
	 */
	public ISpecialObject getSpecialObject() {
		return specialObject;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#setSpecialObject(lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.ISpecialObject)
	 */
	public void setSpecialObject(ISpecialObject specialObject) {
		this.specialObject = specialObject;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public List<IBagItem> getItemsToFind() {
		return itemsToFind;
	}

	public boolean hasNeighbor(IRoom room) {
		for(Direction direction : neighbors.keySet()) {
			if(neighbors.get(direction).contains(room)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean hasNeighbor(Direction direction) {
		return !this.neighbors.get(direction).isEmpty();
	}

	public boolean isNeighborOf(IRoom room) {
		return room.hasNeighbor(this);
	}


	public void setDungeon(Dungeon dungeon) {
		if(dungeon == null) {
			throw new IllegalArgumentException("You must pass a non-null dungeon");
		}
		this.dungeon = dungeon;
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

}
