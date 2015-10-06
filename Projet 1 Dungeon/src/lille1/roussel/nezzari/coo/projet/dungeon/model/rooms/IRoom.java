package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms;

import java.util.List;
import java.util.Map;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.AlreadyUnlockedRoomException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.IBagItem;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.ISpecialObject;

public interface IRoom {

	public void enter() throws CannotBeUsedException;

	public boolean findAllItems() throws CannotBeUsedException, AlreadyUnlockedRoomException;

	public void addNeighbor(Direction direction, IRoom neighbor) throws IllegalArgumentException;

	public List<IRoom> getNeighbors(Direction direction);
	
	public List<IRoom> getVisibleNeighbors(Direction direction);
	
	public Map<Direction, List<IRoom>> getNeighbors();

	public void addItemToFind(IBagItem item) throws IllegalArgumentException;

	public void addItemsToFind(IBagItem... items) throws IllegalArgumentException;

	public void unlock() throws AlreadyUnlockedRoomException;

	public String getName();

	public void setName(String name) throws IllegalArgumentException;

	public String getDescription();

	public void setDescription(String description);

	public String toString();

	public void setVisible(boolean visible);

	public boolean isVisible();

	public void lock();

	public boolean isLocked();

	public ISpecialObject getSpecialObject();

	public void setSpecialObject(ISpecialObject specialObject);

	public boolean isVisited();

	public void setVisited(boolean visited);

	public List<IBagItem> getItemsToFind();

	public boolean hasNeighbor(IRoom room);

	public boolean hasNeighbor(Direction direction);

	public boolean isNeighborOf(IRoom room);

	public void setDungeon(Dungeon dungeon);

	public Dungeon getDungeon();

	public int getId();

	public int hashCode();

	public boolean equals(Object object);

}