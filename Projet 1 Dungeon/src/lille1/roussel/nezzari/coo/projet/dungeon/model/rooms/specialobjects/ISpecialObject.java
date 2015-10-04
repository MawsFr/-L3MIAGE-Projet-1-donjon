package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;

/**
 * @author Maws
 *
 */
public interface ISpecialObject {

	
	/**
	 * @return
	 */
	public IRoom getAssociatedRoom();
	
	/**
	 * @param room
	 */
	public void setAssociatedRoom(IRoom room);
	
	/**
	 * @throws CannotBeUsedException 
	 * 
	 */
	public void use() throws CannotBeUsedException;
	
	/**
	 * @return
	 */
	public boolean isUsed();
	
	/**
	 * @return
	 */
	public String getName();
	
	/**
	 * @param name
	 */
	public void setName(String name);
	
	public String getMessageAfterUse();
	
	public void setMessageAfterUse(String messageAfterUse);
	
}
