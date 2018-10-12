package blackjack;

import java.util.ArrayList;

public class Player {

	private int score;
	private int dealerScore;
	private ArrayList<Integer> deck;
	
	public Player() {
		
		this.score = 0;
		this.dealerScore = 0;
		this.deck = new ArrayList<>();
	}
	
	public void createDeck(int decks) {

		for (int i = 0; i < decks * Dealer.SUITS; i++) {

			deck.add(2);
			deck.add(3);
			deck.add(4);
			deck.add(5);
			deck.add(6);
			deck.add(7);
			deck.add(8);
			deck.add(9);
			deck.add(10);
			// jack, king, and queen have same value
			for (int x = 0; x < 3; x++) {

				deck.add(10);
			}
			deck.add(11);
		}
	}
	
	public void addToScore(int amount) {
		
		this.score += amount;
	}
	
	public int getScore() {
		
		return this.score;
	}
	
	public void setScore(int score) {
		
		this.score = score;
	}
	
	public int getDealerScore() {
		
		return this.dealerScore;
	}
	
	public void setDealerScore(int dealerScore) {
		
		this.dealerScore = dealerScore;
	}
	
	public boolean goingToHit() {
		
		if(this.score < this.dealerScore) {
			
			return true;
		} else if(this.score < 11) {
			
			return true;
		} else if(this.score < 21 - deckAverage()) {
			
			return true;
		} else {
			
			return false;
		}
	}
	
	public int deckAverage() {
		
		int sum = 0;
		
		for(int card : deck) {
			
			sum += card;
		}
		
		return (int) (sum / deck.size());
	}
	
	public void removeFromDeck(int card) {
		
		deck.remove(card);
	}
}
