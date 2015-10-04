package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms;

import java.util.List;
import java.util.Map;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.AlreadyUnlockedRoomException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.IBagItem;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.ISpecialObject;

public interface IRoom {

	/**
	 * Function called when the player enters this room
	 * @throws CannotBeUsedException 
	 */
	public void enter() throws CannotBeUsedException;

	/**
	 * @param direction
	 * @param room2
	 * @throws IllegalArgumentException
	 */
	public void addNeighbor(Direction direction, IRoom room2) throws IllegalArgumentException;

	/**
	 * @param direction
	 * @param neighbors
	 * @throws IllegalArgumentException
	 */
	public void addNeighbors(Direction direction, List<IRoom> neighbors) throws IllegalArgumentException;

	
	/**
	 * @return
	 */
	public Map<Direction, List<IRoom>> getNeighbors();
	
	/**
	 * @param direction
	 * @return
	 */
	public List<IRoom> getNeighbors(Direction direction);

	/**
	 * @return
	 * @throws AlreadyUnlockedRoomException 
	 * @throws CannotBeUsedException 
	 */
	public boolean findAllItems() throws CannotBeUsedException, AlreadyUnlockedRoomException;
	
	/**
	 * @param item
	 */
	public void addItemToFind(IBagItem item) throws IllegalArgumentException;
	
	/**
	 * @param items
	 */
	public void addItemsToFind(IBagItem ... items) throws IllegalArgumentException;
	
	/**
	 * @return
	 */
	public List<IBagItem> getItemsToFind();

	/**
	 * @throws AlreadyUnlockedRoomException 
	 * 
	 */
	public void unlock() throws AlreadyUnlockedRoomException;

	/**
	 * @return
	 */
	public String getName();

	/**
	 * @param name
	 */
	public void setName(String name);

	/**
	 * @return
	 * @throws CannotBeUsedException 
	 */
	public String getDescription() throws CannotBeUsedException;

	/**
	 * @param description
	 */
	public void setDescription(String description);

	/**
	 * @return
	 */
	public String toString();

	/**
	 * @param visible
	 */
	public void setVisible(boolean visible);

	/**
	 * @return
	 */
	public boolean isVisible();

	/**
	 * 
	 */
	public void lock();

	/**
	 * @return
	 */
	public boolean isLocked();

	/**
	 * @return
	 */
	public ISpecialObject getSpecialObject();

	/**
	 * @param specialObject
	 */
	public void setSpecialObject(ISpecialObject specialObject);
	
	public boolean isVisited();
	public void setVisited(boolean visited);
	
	public boolean hasNeighbor(IRoom room);
	public boolean hasNeighbor(Direction direction);
	public boolean isNeighborOf(IRoom room);

}