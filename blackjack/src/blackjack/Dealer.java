package blackjack;

import java.util.ArrayList;

public class Dealer {

	private ArrayList<Integer> deck;
	public final static int SUITS = 4;
	public final int DECK_SIZE = 52;
	public final int STARTING_CARDS = 2;
	private int originalDeckSize;
	private int dealerWins;
	private int playerWins;

	private Player player;

	private int score;

	public Dealer() {

		deck = new ArrayList<>();
		this.score = 0;
		this.player = new Player();
		this.originalDeckSize = 0;
		
		this.dealerWins = 0;
		this.playerWins = 0;
	}

	public void createDeck(int decks) {

		for (int i = 0; i < decks * SUITS; i++) {

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
		this.originalDeckSize = deck.size();
		player.createDeck(decks);
	}

	public String getDeck() {

		String deckString = "";

		for (int card : deck) {

			deckString += card;
			deckString += "\n";
		}

		return deckString;
	}

	public void deal() {

		for (int i = 0; i < STARTING_CARDS; i++) {

			this.score += randomCard();
			player.setDealerScore(this.score);
			player.addToScore(randomCard());
		}

		if (player.goingToHit()) {

			nextCardForPlayer();
		} else {

			dealerPlay();
		}
	}

	public int randomCard() {

		int nextCardIndex = (int) (Math.random() * deck.size());
		int nextCardValue = deck.get(nextCardIndex);

		deck.remove(nextCardIndex);
		player.removeFromDeck(nextCardIndex);

		return nextCardValue;
	}

	public void nextCardForPlayer() {

		int nextCardValue = randomCard();

		player.addToScore(nextCardValue);

		if (player.getScore() > 21) {

			System.out.println("Dealer wins, player bust");
			this.dealerWins++;
			nextGame();
		} else if (player.goingToHit()) {

			nextCardForPlayer();
		} else {

			dealerPlay();
		}
	}

	public void nextCardForDealer() {

		this.score += randomCard();

		dealerPlay();
	}

	public void dealerPlay() {

		if (this.score < player.getScore()) {

			nextCardForDealer();
		} else if (this.score < 22) {

			System.out.println("Dealer wins");
			this.dealerWins++;
			nextGame();
		} else {

			System.out.println("Player wins, dealer bust");
			this.playerWins++;
			nextGame();
		}
	}

	public void nextGame() {

		if (deck.size() < (this.originalDeckSize / 2)) {

			System.out.println("Deck shuffled");
			System.out.println("player won " + this.playerWins + " times");
			System.out.println("dealer won " + this.dealerWins + " times");
		} else {

			player.setScore(0);
			this.setScore(0);

			deal();
		}
	}

	public int getScore() {

		return this.score;
	}

	public void setScore(int score) {

		this.score = score;
	}

	public int averageValue() {

		int sum = 0;

		for (int card : deck) {

			sum += card;
		}

		return (int) (sum / deck.size());
	}
}
