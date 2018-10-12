package blackjack;

public class BlackjackMain {
	
	public static final int NUM_OF_DECKS = 2;
	public static final int SUITS = 4;
	
	public BlackjackMain() {
		
		
	}
	
	public void run() {
		
		Dealer dealer = new Dealer();
		
		dealer.createDealerDeck(NUM_OF_DECKS);
		dealer.deal();
	}
	
	public static void main(String[] args) {
		
		BlackjackMain main = new BlackjackMain();
		main.run();
	}
}
