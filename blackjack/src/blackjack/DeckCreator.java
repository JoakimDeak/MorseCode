package blackjack;

import java.util.ArrayList;

public abstract class DeckCreator {
	
	private ArrayList<Integer> deck;

	public DeckCreator() {
		
		this.deck = new ArrayList<>();
	}
	
	public ArrayList<Integer> createDeck(int decks){
		
		for (int i = 0; i < decks * BlackjackMain.SUITS; i++) {

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
		return deck;
	}
}
