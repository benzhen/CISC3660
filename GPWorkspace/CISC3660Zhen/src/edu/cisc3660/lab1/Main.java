package edu.cisc3660.lab1;

import java.util.ArrayList;

public class Main {
	public static void main(String [] args){
		ArrayList<Card> one = new ArrayList<Card>();
		ArrayList<Card> two = new ArrayList<Card>();
		int counter1 = 0, counter2 = 0;
		String rankHolder="";
		
		// Test
		Deck deck = new Deck();
		deck.shuffle();
		
		for(int i=0; i<26; i++){
			one.add(deck.dealCard());
			two.add(deck.dealCard());
		}
		
		for(Card c:one){
			rankHolder = c.getRank().toString();
			
			switch(rankHolder){
				case "ACE":
					counter1 += 1;
					break;
				case "TWO":
					counter1 += 2;
					break;
				case "THREE":
					counter1 += 3;
					break;
				case "FOUR":
					counter1 += 4;
					break;
				case "FIVE":
					counter1 += 5;
					break;
				case "SIX":
					counter1 += 6;
					break;
				case "SEVEN":
					counter1 += 7;
					break;
				case "EIGHT":
					counter1 += 8;
					break;
				case "NINE":
					counter1 += 9;
					break;
				case "TEN":
					counter1 += 10;
					break;
				case "JACK":
					counter1 += 11;
					break;
				case "QUEEN":
					counter1 += 12;
					break;
				case "KING":
					counter1 += 13;
					break;
			}
		}
		
		for(Card c:two){
			rankHolder = c.getRank().toString();
			
			switch(rankHolder){
				case "ACE":
					counter2 += 1;
					break;
				case "TWO":
					counter2 += 2;
					break;
				case "THREE":
					counter2 += 3;
					break;
				case "FOUR":
					counter2 += 4;
					break;
				case "FIVE":
					counter2 += 5;
					break;
				case "SIX":
					counter2 += 6;
					break;
				case "SEVEN":
					counter2 += 7;
					break;
				case "EIGHT":
					counter2 += 8;
					break;
				case "NINE":
					counter2 += 9;
					break;
				case "TEN":
					counter2 += 10;
					break;
				case "JACK":
					counter2 += 11;
					break;
				case "QUEEN":
					counter2 += 12;
					break;
				case "KING":
					counter2 += 13;
					break;
			}
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
