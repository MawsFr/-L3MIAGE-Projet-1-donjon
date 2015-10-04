package lille1.roussel.nezzari.coo.projet.dungeon.view;

import java.util.Scanner;

import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.CommandLine;
import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.CommandLine.Commands;
import lille1.roussel.nezzari.coo.projet.dungeon.controllers.commandline.fight.AttackCommand;
import lille1.roussel.nezzari.coo.projet.dungeon.exceptions.InvalidCommandException;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Database;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game;
import lille1.roussel.nezzari.coo.projet.dungeon.model.Game.GAMES_STATES;
import lille1.roussel.nezzari.coo.projet.dungeon.model.fight.Fight;
import lille1.roussel.nezzari.coo.projet.dungeon.model.player.Player;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.FightRoom;

public class ConsoleView {

	protected CommandLine commandLine;
	protected Game game;
	public static Scanner sc;

	public ConsoleView() {
		this.game = Game.getInstance();
		game.setCurrentState(GAMES_STATES.NAMEINPUTSTATE);
		this.commandLine = new CommandLine();

	}

	public void run() {
		sc = new Scanner(System.in);
		String userInput;
		do {
			switch(game.getCurrentState()) {
			case NAMEINPUTSTATE:
				System.out.println("Hello there :D Please enter your name :");
				System.out.println();
				userInput = sc.nextLine();
				game.setPlayer(new Player(userInput));
				System.out.println("Welcome " + userInput + " :D");

				game.setCurrentState(GAMES_STATES.INGAMESTATE);
				try {
					game.setCurrentDungeon(Database.dungeons.get(0));
					game.getCurrentDungeon().init();
				} catch(NullPointerException e) {
					System.err.println(e.getMessage());
				}
				break;
			case CHANGEDUNGEONSTATE:
				Dungeon currentDungeon = Game.getInstance().getCurrentDungeon();
				System.out.println("You finished " + currentDungeon + ", CONGRATULATIONS :D");
				try{
					Dungeon nextDungeon = Database.dungeons.get(Database.dungeons.indexOf(Game.getInstance().getCurrentDungeon()) + 1);
					Game.getInstance().setCurrentDungeon(nextDungeon);
					game.setCurrentState(GAMES_STATES.INGAMESTATE);
				} catch (IndexOutOfBoundsException e) {
					game.setCurrentState(GAMES_STATES.WONGAMESTATE);
				}
				break;
			case WONGAMESTATE:
				System.out.println("You've won the game, congratulations :D");
				game.setFinished(true);
				break;
			case LOSTGAMESTATE:
				System.out.println("You've lost the game, my bad :'(");
				game.setFinished(true);
				break;
			case INFIGHTSTATE:
				Fight currentFight = ((FightRoom) game.getCurrentDungeon().getCurrentRoom()).getFight();
				System.out.println("A fight begins between " + game.getPlayer().getName() + " and " + currentFight.getMonster() +"!");
				((AttackCommand) commandLine.getCommand(Commands.ATTACK)).setCurrentFight(currentFight);
				do {
					System.out.println(currentFight);
					System.out.println("What to do ? (Attack | Use object)");
					userInput = sc.nextLine();
					try {
						commandLine.interpretCommand(userInput); //TODO : May be return a boolean value to know if player has done a valuable action
					} catch (InvalidCommandException e) {
						System.err.println(e.getMessage());
					} 

					if(!currentFight.isFinished()) {
						System.out.println(currentFight.getMonster() + " attacks !");
						currentFight.executeMonsterAction();
					} else {
						if(currentFight.getMonster().isDead()) {
							System.out.println(currentFight.getMonster() + " is dead !");
						} else if(currentFight.getPlayer().isDead()) {
							System.out.println(currentFight.getPlayer().getName() + "... you are dead !");
						} else {
							System.out.println("You are both dead !");
						}
					}
				} while(!currentFight.isFinished());

				System.out.println("The fight is finished !");
				if(currentFight.getWinner() == null) {
					System.out.println("It was a Draw !");
					game.setCurrentState(GAMES_STATES.LOSTGAMESTATE);
				} else if(currentFight.getWinner() == game.getPlayer()) {
					System.out.println("You won the fight !");
					game.setCurrentState(GAMES_STATES.INGAMESTATE);
				} else if(currentFight.getWinner() == currentFight.getMonster()) {
					System.out.println("The monster has won !");
					game.setCurrentState(GAMES_STATES.LOSTGAMESTATE);
				}
				((AttackCommand) commandLine.getCommand(Commands.ATTACK)).setCurrentFight(null);
				//may be add game state fightwonstate and fightloststate
				break;
			case INGAMESTATE:
				System.out.println("");
				System.out.println(game);
				commandLine.showPrompt();
				userInput = sc.nextLine();
				try {
					commandLine.interpretCommand(userInput);
				} catch (InvalidCommandException e) {
					System.err.println(e.getMessage());
				}
				break;
				
				default:
					break;

			}


		} while(!game.isFinished());

		System.out.println("Thanks for playing :)");

		sc.close();

	}

	public void clearScreen() {
		try {
			if(System.getProperty("os.name" ).startsWith("Windows" ))
				Runtime.getRuntime().exec("cls" );
			else
				Runtime.getRuntime().exec("clear" );
		} catch(Exception excpt) {
			for(int i=0; i < 100; i++)
				System.out.println();
		}

	}

	public static void main(String[] args) {
		Database.initDb();
		new ConsoleView().run();
	}

}
