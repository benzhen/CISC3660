package edu.zhen.lab2;

import java.util.ArrayList;

import edu.zhen.lab1.Card;

public class Player {
	private ArrayList<Card> hand;
	
	public Player(){
		hand = new ArrayList<Card>();
	}
	
	public void receiveCard(Card c){
		hand.add(c);
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public String getHandString(){
		String handStr="";
		for(Card c: hand){
			handStr += c.getRank() + " ";
		}
		return handStr;
	}

}
