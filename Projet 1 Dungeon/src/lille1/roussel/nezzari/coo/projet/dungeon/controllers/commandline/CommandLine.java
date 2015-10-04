package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;

import java.util.HashMap;
import java.util.Map;

import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.fight.AttackCommand;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.CannotBeUsedException;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.InvalidCommandException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game.GAMES_STATES;

public class CommandLine {

	public enum Commands {
		//TODO: Add attribute Command to store the associated command ?
		ME, GO, USE, DESCRIBE, PUSH /*button*/, OPEN /*treasure*/, ATTACK, HELP, EQUIP, UNEQUIP, DRINK;

		public static Commands get(String command) {
			for(Commands c : Commands.values()) {
				if(c.name().equalsIgnoreCase(command)) {
					return c;
				}

			}

			return null;
		}

	}

	protected Map<Commands, Command> commands;

	public CommandLine() {
		this.commands = new HashMap<Commands, Command>();
		this.commands.put(Commands.ME, new PlayerStatsCommand());
		this.commands.put(Commands.GO, new GoCommand());
		this.commands.put(Commands.USE, new UseObjectCommand());
		this.commands.put(Commands.DESCRIBE, new DescribeCommand());
		this.commands.put(Commands.PUSH, new PushButtonCommand());
		this.commands.put(Commands.OPEN, new OpenTreasureCommand());
		this.commands.put(Commands.ATTACK, new AttackCommand());
		this.commands.put(Commands.HELP, new HelpCommand(this));
		this.commands.put(Commands.EQUIP, new EquipCommand());
		this.commands.put(Commands.UNEQUIP, new UnequipCommand());
		this.commands.put(Commands.DRINK, new DrinkCommand());
		
		

	}

	public void showPrompt() {
		System.out.println("What do you want to do ? (type \"help\" for available commands)");

	}

	public void showFightCommands() {

	}

	public void interpretCommand(String userInput) throws InvalidCommandException{
		/* go + direction
		 * use + object name (in game and battle)
		 * describe
		 * push button
		 * open treasure
		 * attack
		 * 
		 */

		String[] splittedCommand = userInput.split(" ", 2);
		String command = splittedCommand[0];
		Commands c = Commands.get(command);


		if(c != null) {
			try{
			if(Game.getInstance().getCurrentState() != GAMES_STATES.INFIGHTSTATE) { //if we're not in a fight
				if(splittedCommand.length == 1) { //if there are no arguments
					commands.get(c).execute(splittedCommand[0]);
				} else {
					commands.get(c).execute(splittedCommand[1]);
				}
			} else {
				if(splittedCommand.length == 1) { //if there are no arguments
					commands.get(c).executeInFight(splittedCommand[0]);
				} else {
					commands.get(c).executeInFight(splittedCommand[1]);
				}
			}
			} catch (CannotBeUsedException e) {
				System.err.println(e.getMessage());
			}
		} else {
			throw new InvalidCommandException("This command doesn't exist !");
		}
	}
	
	public Command getCommand(Commands command) {
		return this.commands.get(command);
	}
	
	public Map<Commands, Command> getCommands() {
		return commands;
	}

}
