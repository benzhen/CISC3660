package edu.zhen.lab2;

public class Controller {
	MessagePanel msg;
	boolean playing;
	State state;
	
	public Controller(){
	}
	
	public void init(){
		state = new State();
		//Send message to player1
		msg.outputMessage(1, "It is your turn. Your Hand: "
						+ state.getPlayer(1).getHandString()
						+ " Dealer's hand: "
						+ state.getDealer().getHandString());
		msg.outputMessage(2, Constants.WAIT_MSG);
		msg.outputMessage(3, Constants.WAIT_MSG);
	}
	
	public void clientRequest(char c){
		if(playing){
			//Decide based on c
			changeState(c);
			return;
		}
		init();
		playing = true;
	}
	
	public void setMessenger(MessagePanel message){
		msg = message;
		msg.showMenu();
	}
	
	private void showErrorMsgPlayerKey(char c){
		int player = -1;
		if(c == 'q' || c == 'w'){
			player = 1;
		}
		else if(c == 'a' || c == 's'){
			player = 2;
		}
		else if(c == 'z' || c == 'x'){
			player = 3;
		}
		msg.outputMessage(player, Constants.WRONG_TURN_MSG);
	}
	
	private void changeState(char c){
		// Your assignment #2
	}
}
