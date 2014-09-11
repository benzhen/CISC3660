package edu.cisc3660.lab1;

public class Card {
	private Suit suit;
	private Rank rank;
	
	public Card(Suit suit, Rank rank){
		this.suit = suit;
		this.rank = rank;
	}
	
	public Suit getSuit(){
		return suit;
	}
	
	public void setSuit(Suit suit){
		this.suit = suit;
	}
	
	public Rank getRank(){
		return rank;
	}
	
	public void setRank(Rank rank){
		this.rank = rank;
	}

}
