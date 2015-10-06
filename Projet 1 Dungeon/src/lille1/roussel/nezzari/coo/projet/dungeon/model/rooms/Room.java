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
	
	protected static int nbRooms = 0;

	protected int id;
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
		id = nbRooms;
		nbRooms ++;
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
	@Override
	public void enter() throws CannotBeUsedException {
		if(this.locked) {
			throw new CannotBeUsedException("You need a key or to press a button to unlock " + this.getName() + " and access it !");
		}
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#findAllItems()
	 */
	@Override
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

	@Override
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

	@Override
	public List<IRoom> getNeighbors(Direction direction) {
		return this.neighbors.get(direction);

	}
	
	@Override
	public List<IRoom> getVisibleNeighbors(Direction direction) {
		List<IRoom> visibleRooms = new ArrayList<IRoom>();
			List<IRoom> rooms = neighbors.get(direction);
			for(IRoom room : rooms) {
				if(room.isVisible()) {
					visibleRooms.add(room);
				}
			}
		
		return visibleRooms;
		
	}

	@Override
	public Map<Direction, List<IRoom>> getNeighbors() {
		return this.neighbors;
	}


	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#addItemToFind(lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.IBagItem)
	 */
	@Override
	public void addItemToFind(IBagItem item) throws IllegalArgumentException{
		if(item == null) {
			throw new IllegalArgumentException("You must specify an item to add !");
		}
		this.itemsToFind.add(item);
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#addItemsToFind(lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.IBagItem)
	 */
	@Override
	public void addItemsToFind(IBagItem... items) throws IllegalArgumentException{
		for(IBagItem item : items) {
			addItemToFind(item);
		}
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#unlock()
	 */
	@Override
	public void unlock() throws AlreadyUnlockedRoomException {
		if(!this.locked) {
			throw new AlreadyUnlockedRoomException("This room is already unlocked !");
		}

		this.locked = false;
	}


	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#setName(java.lang.String)
	 */
	@Override
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
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}


	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#toString()
	 */
	@Override
	public String toString() {
		String s = "";
		//return this.name;
		s += "\nName : " + getName() + "\n";
		s += "\nNeighbours :\n";
		
		for(Direction direction : Direction.values()) {
			s += "\n" + direction + " : " + getVisibleNeighbors(direction).size() + " room(s)";
		}
		
		s += "\n"; 
		
		return s;
		
		/*if(this.hasNeighbor(Direction.North)) {
			int nbNorthNeighbors = this.neighbors.get(Direction.North).size();
			if(this.hasNeighbor(Direction.East) && this.hasNeighbor(Direction.West)) {
				s += "\n .-----[" + nbNorthNeighbors + "]------.";
				
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
			int nbSouthNeighbors = this.neighbors.get(Direction.South).size();
			if(this.hasNeighbor(Direction.East) && this.hasNeighbor(Direction.West)) {
				s += "\n .-----[" + nbSouthNeighbors + "]------.";
				
			} else {
				if(this.hasNeighbor(Direction.West)) {
					s += ".";
				} else {
					s += ".-";
				}
				s += "-----[" + nbSouthNeighbors + "]-----";
				if(this.hasNeighbor(Direction.East)) {
					s += ".";
				} else {
					s += "-.";
				}
			}
		} else {
			
			s += "\n!_______________!";
					 
		} */

	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#isVisible()
	 */
	@Override
	public boolean isVisible() {
		return visible;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#lock()
	 */
	@Override
	public void lock() {
		this.locked = true;

	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#isLocked()
	 */
	@Override
	public boolean isLocked() {
		return locked;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#getSpecialObject()
	 */
	@Override
	public ISpecialObject getSpecialObject() {
		return specialObject;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#setSpecialObject(lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.ISpecialObject)
	 */
	@Override
	public void setSpecialObject(ISpecialObject specialObject) {
		this.specialObject = specialObject;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#isVisited()
	 */
	@Override
	public boolean isVisited() {
		return visited;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#setVisited(boolean)
	 */
	@Override
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#getItemsToFind()
	 */
	@Override
	public List<IBagItem> getItemsToFind() {
		return itemsToFind;
	}

	@Override
	public boolean hasNeighbor(IRoom room) {
		for(Direction direction : neighbors.keySet()) {
			if(neighbors.get(direction).contains(room)) {
				return true;
			}
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#hasNeighbor(lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.Direction)
	 */
	@Override
	public boolean hasNeighbor(Direction direction) {
		return !this.neighbors.get(direction).isEmpty();
	}

	@Override
	public boolean isNeighborOf(IRoom room) {
		return room.hasNeighbor(this);
	}


	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#setDungeon(lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon)
	 */
	@Override
	public void setDungeon(Dungeon dungeon) {
		if(dungeon == null) {
			throw new IllegalArgumentException("You must pass a non-null dungeon");
		}
		this.dungeon = dungeon;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#getDungeon()
	 */
	@Override
	public Dungeon getDungeon() {
		return dungeon;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#getId()
	 */
	@Override
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!(object instanceof Room)) {
			return false;
		}
		
		Room other = (Room) object;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	
}
