package lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline;


public class HelpCommand extends Command {

	protected CommandLine commandLine;

	public HelpCommand(CommandLine commandLine) {
		this.commandLine = commandLine;
	}

	@Override
	public void execute(String substring) {
		for(Command command : commandLine.getCommands().values()) {
			command.printHelp();
		}

	}
	
	@Override
	public void executeInFight(String substring) {
		
	}

	@Override
	public void printHelp() {
		// TODO Auto-generated method stub

	}	
}
