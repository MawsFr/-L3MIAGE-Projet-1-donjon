package lille1.roussel.nezzari.coo.projet.dungeon.model.rooms;

import lille1.roussel.nezzari.coo.projet.dungeon.model.Dungeon;
import lille1.roussel.nezzari.coo.projet.dungeon.model.rooms.specialobjects.Button;

public class ButtonRoom extends Room {
	
	public ButtonRoom(String name, Dungeon dungeon, Button button) {
		super(name, dungeon, button);
		button.setRoomWhereTheButtonIs(this);
	}
	
	@Override
	public void enter() {}
	
	public Button getButton() {
		return (Button) this.specialObject;
	}
	
}
