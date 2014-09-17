package edu.zhen.lab2;

import edu.zhen.lab1.Deck;;

public class State {
	private byte turn; // 1 - 2 - 3
	private Player [] players; // players[0] is the dealer
	private Deck deck;
	
	public State(){
		init();
	}
	
	public void init(){ // Inital State
		turn = 1;
		deck = new Deck();
		deck.initFullDeck();
		deck.shuffle();
		players = new Player[4]; // dealer + 3 players
		players[0] = new Player();
		players[0].receiveCard(deck.dealCard());
		//Give 2 cards to each player
		for(int i=1; i<3; i++){
			players[i] = new Player();
			players[i].receiveCard(deck.dealCard());
			players[i].receiveCard(deck.dealCard());
		}
	}
	
	public Player getPlayer(int playerNum){
		return players[playerNum];
	}
	
	//We could just use getPlayer but this method improves readability
	public Player getDealer(){
		return players[0];
	}
	
	public Deck getDeck(){ return deck; }
	public byte getTurn(){ return turn; }
	public void nextTurn(){ turn++; }
}
