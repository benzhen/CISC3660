package edu.zhen.lab2;

import java.util.ArrayList;

import edu.zhen.lab1.Card;
import edu.zhen.lab1.Rank;
import edu.zhen.lab1.Suit;

public class Controller {
	MessagePanel msg;
	boolean playing;
	State state;
	
	ArrayList<Card> deal = new ArrayList<Card>();
	int count = 0, highest = 0;
	boolean dealerTurn = false, hasAce = false;
	String playerOutcome1, playerOutcome2, playerOutcome3, dealerOutcome;
	
	public Controller(){
	}
	
	public void init(){
		state = new State();
		totalHand(1);
		totalHand(2);
		totalHand(3);
		totalHand(0);
		//Send message to player1
		msg.outputMessage(1, "It is your turn. Your Hand: "
						+ state.getPlayer(1).getHandString()
						+ " Total: "
						+ state.getPlayer(1).getTotal()
						+ " Dealer's hand: "
						+ state.getDealer().getHandString());
		msg.outputMessage(2, Constants.WAIT_MSG);
		msg.outputMessage(3, Constants.WAIT_MSG);
		msg.outputMessage(4, "");
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
		if(c == 'p'){
			init();
		}
		
		if(c == 'k'){
			System.out.println(state.getDeck().getNumberOfCardsRemaining());
		}
		
		if(state.getTurn() == 1 && state.getPlayer(1).getTotal() > 21){
			playerOneState();
			playerTwoTurn();
			msg.repaint();
			state.nextTurn();
		}
		if(state.getTurn() == 2 && state.getPlayer(2).getTotal() > 21){
			playerTwoState();
			playerThreeTurn();
			msg.repaint();
			state.nextTurn();
		}
		if(state.getTurn() == 3 && state.getPlayer(3).getTotal() > 21){
			playerThreeTurn();
			state.nextTurn();
			dealerRun();
		}
		
		if(c == 'q' || c == 'w' || c == 'a' || c == 's' || c == 'z' || c == 'x'){
			if(c == 'q' && state.getTurn() == 1){
				deal.add(state.getDeck().dealCard());
				state.getPlayer(1).receiveCard(deal.get(count));
				addCard(1, deal.get(count));
				playerOneTurn();
				count++;
			}
			else if(c == 'w' && state.getTurn() == 1){
				state.nextTurn();
				//state.getPlayer(1).setTotal(10);
				playerOneState();
				playerTwoTurn();
			}
			else if(c == 'a' && state.getTurn() == 2){
				deal.add(state.getDeck().dealCard());
				state.getPlayer(2).receiveCard(deal.get(count));
				addCard(2, deal.get(count));
				playerTwoTurn();
				count++;
				
			}
			else if(c == 's' && state.getTurn() == 2){
				state.nextTurn();
				//state.getPlayer(2).setTotal(21);
				playerTwoState();
				playerThreeTurn();
			}
			else if(c == 'z' && state.getTurn() == 3){
				deal.add(state.getDeck().dealCard());
				state.getPlayer(3).receiveCard(deal.get(count));
				addCard(3, deal.get(count));
				playerThreeTurn();
				count++;
			}
			else if(c == 'x' && state.getTurn() == 3){
				state.nextTurn();
				//state.getPlayer(3).setTotal(20);
				playerThreeState();
				dealerRun();
				//state.getDealer().setTotal(12);
			}
			else{
				showErrorMsgPlayerKey(c);
			}
		}
		
	}
	

	public void totalHand(int player){
		for(Card c: state.getPlayer(player).getHand()){
			if(c.getRank().ordinal() +1 > 10){
				state.getPlayer(player).addTotal(10);
			}
			else{
				state.getPlayer(player).addTotal(c.getRank().ordinal() +1);
			}
			if(c.getRank().ordinal() == 0){
				hasAce = true;
			}
		}
		
		if(hasAce && state.getPlayer(player).getTotal()<21){
			if(state.getPlayer(player).getTotal()+10 <22){
				state.getPlayer(player).addTotal(10);
			}
		}
	
		hasAce = false;
	}
	
	public void addCard(int player, Card c){
		if(c.getRank().ordinal() +1 > 10){
			state.getPlayer(player).addTotal(10);
		}
		else{
			state.getPlayer(player).addTotal(c.getRank().ordinal() +1);
		}
		if(c.getRank().ordinal() == 0){
			hasAce = true;
		}
		
		
		if(hasAce && state.getPlayer(player).getTotal()<21){
			if(state.getPlayer(player).getTotal()+10 <22){
				state.getPlayer(player).addTotal(10);
			}
		}
	
		hasAce = false;
	}
	
	public void playerOneTurn(){
		msg.outputMessage(1, "It is your turn. Your Hand: "
				+ state.getPlayer(1).getHandString()
				+ " Total: "
				+ state.getPlayer(1).getTotal()
				+ " Dealer's hand: "
				+ state.getDealer().getHandString());
		msg.outputMessage(2, Constants.WAIT_MSG);
		msg.outputMessage(3, Constants.WAIT_MSG);
	}
	
	public void playerTwoTurn(){
		msg.outputMessage(1, "Your Hand: "
				+ state.getPlayer(1).getHandString()
				+ " Total: "
				+ state.getPlayer(1).getTotal());
		msg.outputMessage(2, "It is your turn. Your Hand: "
				+ state.getPlayer(2).getHandString()
				+ " Total: "
				+ state.getPlayer(2).getTotal()
				+ " Dealer's hand: "
				+ state.getDealer().getHandString());
		msg.outputMessage(3, Constants.WAIT_MSG);
	}
	
	public void playerThreeTurn(){
		msg.outputMessage(1, "Your Hand: "
				+ state.getPlayer(1).getHandString()
				+ " Total: "
				+ state.getPlayer(1).getTotal());
		msg.outputMessage(2, "Your Hand: "
				+ state.getPlayer(2).getHandString()
				+ " Total: "
				+ state.getPlayer(2).getTotal());
		msg.outputMessage(3, "It is your turn. Your Hand: "
				+ state.getPlayer(3).getHandString()
				+ " Total: "
				+ state.getPlayer(3).getTotal()
				+ " Dealer's hand: "
				+ state.getDealer().getHandString());
	}
	
	public void dealerTurn(String p1, String p2, String p3, String dealer){
		msg.outputMessage(1, "Your Hand: "
				+ state.getPlayer(1).getHandString()
				+ " Total: "
				+ state.getPlayer(1).getTotal()
				+ " Results: " + p1);
		msg.outputMessage(2, "Your Hand: "
				+ state.getPlayer(2).getHandString()
				+ " Total: "
				+ state.getPlayer(2).getTotal()
				+ " Results: " + p2);
		msg.outputMessage(3, "It is your turn. Your Hand: "
				+ state.getPlayer(3).getHandString()
				+ " Total: "
				+ state.getPlayer(3).getTotal()
				+ " Results: " + p3);
		msg.outputMessage(4, "Dealer's hand: " 
				+ state.getDealer().getHandString()
				+ "Total: "
				+ state.getPlayer(0).getTotal()
				+ " Results: " + dealer
				+ "     " + "Press 'p' to play again!");
	}
	
	public void playerOneState(){
		if(state.getPlayer(1).getTotal() <= 21){
			highest = state.getPlayer(1).getTotal();
		}
		playerOutcome1 = Constants.WON;
		if(state.getPlayer(1).getTotal() == 21){
			playerOutcome1 = Constants.BLACKJACK;
		}
	}
	
	public void playerTwoState(){
		if(state.getPlayer(2).getTotal() > highest && highest != 21 && state.getPlayer(2).getTotal() < 21){
			highest = state.getPlayer(2).getTotal();
			playerOutcome1 = Constants.LOST;
			playerOutcome2 = Constants.WON;
		}
		else{
			playerOutcome2 = Constants.LOST;
		}
		if(state.getPlayer(2).getTotal() == 21 && state.getPlayer(1).getTotal() != 21){
			playerOutcome1 = Constants.LOST;
			playerOutcome2 = Constants.BLACKJACK;
			highest = 21;
		}
		else if(state.getPlayer(2).getTotal() == 21){
			playerOutcome2 = Constants.BLACKJACK;
			highest = 21;
		}
	}
	
	public void playerThreeState(){
		if(state.getPlayer(3).getTotal() > highest && highest != 21 && state.getPlayer(3).getTotal() < 21){
			highest = state.getPlayer(3).getTotal();
			playerOutcome1 = Constants.LOST;
			playerOutcome2 = Constants.LOST;
			playerOutcome3 = Constants.WON;
		}
		else{
			playerOutcome3 = Constants.LOST;
		}
		
		if(state.getPlayer(3).getTotal() == 21 && state.getPlayer(2).getTotal() != 21){
			
			playerOutcome2 = Constants.LOST;
			playerOutcome3 = Constants.BLACKJACK;
			highest = 21;
		}
		else if(state.getPlayer(3).getTotal() == 21){
			playerOutcome3 = Constants.BLACKJACK;
			highest = 21;
		}
	}
	
	public void dealerRun(){
		while(state.getDealer().getTotal() <= highest && state.getDealer().getTotal() < 21){
			deal.add(state.getDeck().dealCard());
			state.getDealer().receiveCard(deal.get(count));
			addCard(0, deal.get(count));
			count++;
		}
		
		if(state.getDealer().getTotal() < 21 && state.getDealer().getTotal() > highest){
			playerOutcome1 = Constants.LOST;
			playerOutcome2 = Constants.LOST;
			playerOutcome3 = Constants.LOST;
			dealerOutcome = Constants.WON;
		}
		else if(state.getDealer().getTotal() == 21 && highest != 21){
			playerOutcome1 = Constants.LOST;
			playerOutcome2 = Constants.LOST;
			playerOutcome3 = Constants.LOST;
			dealerOutcome = Constants.BLACKJACK;
		}
		else if(state.getDealer().getTotal() == 21){
			dealerOutcome = Constants.BLACKJACK;
		}
		else{
			dealerOutcome = Constants.LOST;
		}
		
		dealerTurn(playerOutcome1, playerOutcome2, playerOutcome3, dealerOutcome);
	}
}
