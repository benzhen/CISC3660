package edu.cisc3660.lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
	// You could use a LinkedList instead of an ArrayList.
	// It depends on the operations that you want to perform on the list
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	public Deck(){
		for(Rank r: Rank.values()){
			for(Suit s: Suit.values()){
				deck.add(new Card(s,r));
			}
		}
	}
	
	public List<Card> getDeck(){
		return Collections.unmodifiableList(deck);
	}
	
	public Card removeCardDeck(int index){
		return deck.remove(index);
	}
	
	public void addOneCard(Card c){
		deck.add(c); // Appends the new card to the end of the book
	}
	
	//Remove one card of the same rank and suit
	public void removeAParticularCard(Card c){
		for(int i=deck.size()-1; i>=0; i--){
			if(deck.get(i).getRank() == c.getRank() && deck.get(i).getSuit() == c.getSuit()){
				deck.remove(i);
				break;
			}
		}
	}
	
	public void removeAllCardsOfRank(Rank r){
		for(int i=deck.size()-1; i>=0; i--){
			if(deck.get(i).getRank() == r){
				deck.remove(i);
			}
		}
	}
	
	@Override
	public String toString(){
		//Student, think about a more efficient way of doing this:
		String s = "";
		for(Card c:deck){
			s = s + c.getSuit().toString() + " " + c.getRank().toString() + "\n";
		}
		return s;
	}
	
	public ArrayList<Card> getOrderedCards(){
		deck.clear();
		
		for(Rank r: Rank.values()){
			for(Suit s: Suit.values()){
				deck.add(new Card(s,r));
			}
		}
		
		return deck;
	}
	
	public int getNumberOfCardsRemaining(){
		return deck.size();
	}
	
	public Card dealCard(){
		Random rand = new Random();
		int n = rand.nextInt(deck.size());
		
		Card deal = new Card(deck.get(n).getSuit(), deck.get(n).getRank());
		removeCardDeck(n);
		addOneCard(deal);
		
		return deal;
	}
	
	public void shuffle(){
		Random rand = new Random();
		
		int n, numShuffle = rand.nextInt(80) + 30;
		
		
		
		for(int i=0; i<numShuffle; i++){
			n = rand.nextInt(deck.size());
			
			Card deal = new Card(deck.get(n).getSuit(), deck.get(n).getRank());
			removeCardDeck(n);
			addOneCard(deal);
		}
		
	}

}
