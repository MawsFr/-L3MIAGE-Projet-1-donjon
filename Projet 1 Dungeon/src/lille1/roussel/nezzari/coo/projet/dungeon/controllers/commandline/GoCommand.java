package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import java.util.ArrayList;
import java.util.List;

import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.AlreadyUnlockedRoomException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.InvalidCommandException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.items.IBagItem;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.Direction;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.IRoom;
import lille1.roussel.nezzari.coo.projet.dungeon.view.ConsoleView;

public class GoCommand extends Command {

	@Override
	public void execute(String userInput) throws InvalidCommandException, IllegalArgumentException {
		if(userInput == null) {
			throw new InvalidCommandException("You must specify a direction with the go command");
		}
		Direction direction = Direction.get(userInput);

		if(direction == null) {
			throw new InvalidCommandException("The direction you specified doesn't exist !");
		}

		System.out.println("Going " + userInput);
		Game game = Game.getInstance();
		List<IRoom> neighbors = game.getCurrentDungeon().getCurrentRoom().getNeighbors(direction);
		List<IRoom> visibleRooms = new ArrayList<IRoom>();
		for(IRoom room : neighbors) {
			if(room.isVisible()) {
				visibleRooms.add(room);
			}
		}

		if(visibleRooms.size() <= 0) {
			throw new IllegalArgumentException("There are no room to go in !");
		} else if(neighbors.size() == 1) {
			tryGoingToRoom(game, visibleRooms, 0);
		} else if(visibleRooms.size() > 1) {
			//throw exception select where to go
			System.out.println("Choose the room where you want to go to :");
			for(int i = 0; i < visibleRooms.size(); ++i) {
				System.out.println((i + 1) + " - " + visibleRooms.get(i));
			}

			int choice = ConsoleView.sc.nextInt(); // When two or more room are possible, throw an exception saying that there are many rooms, list them and ask to enter the command like "go north intersection" We'll the better 
			ConsoleView.sc.nextLine();
			tryGoingToRoom(game, visibleRooms, choice - 1);
		}
		
		verifiyObjectsToFind(game);

	}

	public void tryGoingToRoom(Game game, List<IRoom> visibleRooms, int choice) {
		IRoom targetRoom = visibleRooms.get(choice);
		try {
			targetRoom.enter();
			game.getCurrentDungeon().setCurrentRoom(targetRoom);
		} catch (CannotBeUsedException e) {
			System.err.println(e.getMessage());
		}
	}

	public void verifiyObjectsToFind(Game game) {
		IRoom currentRoom = Game.getInstance().getCurrentDungeon().getCurrentRoom();
		if(!currentRoom.isVisited()) {
			boolean foundObjects;
			try {
				foundObjects = currentRoom.findAllItems();
				if(foundObjects) {
					System.out.println("Objects found :");
					List<IBagItem> items = currentRoom.getItemsToFind();
					for(IBagItem item : items) {
						System.out.println(item);
					}
				} else {
					if(!currentRoom.isVisited()) {
						System.out.println("No objects in this room !");
					}
				}

				game.getCurrentDungeon().getCurrentRoom().setVisited(true);
			} catch (CannotBeUsedException e) {
				System.err.println(e.getMessage());
			} catch (AlreadyUnlockedRoomException e) {
				System.err.println(e.getMessage());
			}
			

		}
	}

	
	
	


	@Override
	public void executeInFight(String substring) throws CannotBeUsedException {
		throw new CannotBeUsedException("You can't use this command in a fight !");
	}


	@Override
	public String printHelp() {
		return "//Go Command// : Type \"go + direction\" where direction can be {north, south, west, east, up, down}";
	}


}
