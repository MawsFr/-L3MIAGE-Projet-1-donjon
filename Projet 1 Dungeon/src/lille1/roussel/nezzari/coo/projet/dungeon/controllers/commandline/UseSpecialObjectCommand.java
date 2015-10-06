package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.ISpecialObject;

public abstract class UseSpecialObjectCommand extends Command {

	@Override
	public void execute(String substring) throws CannotBeUsedException {
		IRoom currentRoom = Game.getInstance().getCurrentDungeon().getCurrentRoom();
		ISpecialObject specialObject = currentRoom.getSpecialObject();
		if(specialObject != null) { //unessecary
			if(!specialObject.isUsed()){
				specialObject.use();
				System.out.println(specialObject.getMessageAfterUse());
			} else {
				System.err.println(specialObject + " has already been used !");
			}
		} else {
			throw new CannotBeUsedException();
		}

	}

	@Override
	public void executeInFight(String substring) throws CannotBeUsedException {
		System.err.println("This command cannot be used here !");
	}
	@Override
	public abstract String printHelp();
	
}
