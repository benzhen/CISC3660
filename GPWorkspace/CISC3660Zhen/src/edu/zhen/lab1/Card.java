package edu.zhen.lab1;

public class Card {
	private Suit suit;
	private Rank rank;
	
	public Card(Suit suit, Rank rank){
		this.suit = suit;
		this.rank = rank;
	}
	
	public Card(){}

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
	
	public static boolean compare(Card c1, Card c2){
		if(c1.rank == c2.rank){
			if(c1.suit == c2.suit){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean compareRank(Card c1, Card c2){
		if(c1.rank == c2.rank){
			return true;
		}
		
		return false;
	}
	
	public static boolean compareSuit(Card c1, Card c2){
		if(c1.suit == c2.suit){
			return true;
		}
		
		return false;
	}
	
	public void overrideCard(Card c){
		this.suit = c.getSuit();
		this.rank = c.getRank();
	}

}
