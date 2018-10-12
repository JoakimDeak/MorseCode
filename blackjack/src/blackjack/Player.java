package blackjack;

import java.util.ArrayList;

public class Player extends DeckCreator {

	private int score;
	private int dealerScore;
	private ArrayList<Integer> deck;
	
	public Player() {
		
		this.score = 0;
		this.dealerScore = 0;
		this.deck = createDeck(BlackjackMain.NUM_OF_DECKS);
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
