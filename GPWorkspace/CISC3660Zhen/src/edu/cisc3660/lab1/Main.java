package edu.cisc3660.lab1;

import java.util.ArrayList;

public class Main {
	public static void main(String [] args){
		ArrayList<Card> one = new ArrayList<Card>();
		ArrayList<Card> two = new ArrayList<Card>();
		int counter1 = 0, counter2 = 0;
		//String s = "", q = "";
		
		// Test
		Deck deck = new Deck();
		int deckHalf = deck.getNumberOfCardsRemaining()/2;
		deck.shuffle();
		
		for(int i=0; i<deckHalf; i++){
			one.add(deck.dealCard());
			two.add(deck.dealCard());
		}
		
		for(Card c:one){
			counter1 += c.getRank().ordinal();
		}
		
		for(Card c:two){
			counter2 += c.getRank().ordinal();
		}
		
		System.out.println("Total of Array One: " + counter1
							+ ", Total of Array Two: " + counter2);
		
		if(counter1 > counter2){
			System.out.println("Winner is Array One!!!");
		}
		else if(counter2 > counter1){
			System.out.println("Winner is Array Two!!!");
		}
		else{
			System.out.println("No Winner, it's a Tie!!!");
		}
	}

}
